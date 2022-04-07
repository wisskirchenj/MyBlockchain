package de.cofinpro.blockchain.model.core;

import de.cofinpro.blockchain.config.BlockchainConfig;
import de.cofinpro.blockchain.model.signed.SignedMessage;
import de.cofinpro.blockchain.model.signed.Signable;
import de.cofinpro.blockchain.model.signed.SignedDataBlock;
import de.cofinpro.blockchain.model.signed.SignedTransaction;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;

/**
 * our Blockchain class extends from (i.e. is a) LinkedList to be able to iterate it in both directions.
 * the blockchain uses a static serializer (which it offers to the controller for deserialization) and
 * a static BlockchainValidator, that comprises all internal validation logic.
 * Beside chaining and validating blocks, the blockchain class regulates the computation time for the
 * next block by adapting a complexity in terms of leadingHashZeros for the upcoming block.
 *
 * Further it keeps a concurrent message queue for signable (from stage 5 on) data, which are stored as block data
 * and offers methods to accept and poll data.
 * (would be better to single data storage handling out of this class, but it's strictly specified by hyperskill...)
 */
@Slf4j
public class Blockchain extends LinkedList<Block> {

    @Serial
    private static final long serialVersionUID = 14L;

    private static final BlockchainValidator VALIDATOR = new BlockchainValidator();
    private static final BlockchainSerializer SERIALIZER = new BlockchainSerializer();

    /**
     * getter for private class serializer - we don't want someone to replace it.
     */
    public static BlockchainSerializer getSerializer() {
        return SERIALIZER;
    }

    private final AtomicInteger messageIdCount = new AtomicInteger(0);
    private final Map<String, Integer> ledger = new ConcurrentHashMap<>();

    private transient Queue<Signable> clientDataQueue = null;
    private transient int currentLeadingHashZeros = 0;

    /**
     * entry point to store non data carrying blocks to a chain - currently not used.
     * @param newBlock the received block
     * @return false if block invalid (it is not added in that case), true else
     */
    public boolean addBlock(Block newBlock) {
        if (!VALIDATOR.isBlockValid(newBlock, currentLeadingHashZeros,
                isEmpty() ? null : getLast())) {
            return false;
        }
        add(newBlock);
        SERIALIZER.serialize(this);
        return true;
    }


    /**
     * validates and adds a new block (as typically received by a miner). Also, a serialization of the
     * extended chain is done to avoid data loss.
     * @param newBlock the received block
     * @return false if block invalid (it is not added in that case), true else
     */
    public boolean addDataBlock(SignedDataBlock newBlock) {
        if (!VALIDATOR.isBlockValid(newBlock, currentLeadingHashZeros,
                isEmpty() ? null : getLast())) {
            return false;
        }
        if (BLOCKCHAIN_MODE == Mode.TRANSACTIONS) {
            addToLedger(newBlock.getMinerId());
        }
        add(newBlock);
        SERIALIZER.serialize(this);
        return true;
    }

    private synchronized void addToLedger(int minerId) {
        String miner = "miner%d".formatted(minerId);
        ledger.put(miner, ledger.getOrDefault(miner, 0) + BLOCK_REWARD);
    }

    /**
     * before continuing generation, this method is called to check, if the deserializes blockchain state
     * matches the validation rules.
     */
    public void resetIfInvalid() {
        try {
            VALIDATOR.validateChain(this);
        } catch (InvalidBlockchainException exception) {
            log.warn("Invalid blockchain deserialized!\nStart generating from scratch.");
            clear();
        }
    }

    /**
     * Adapts the requested leading zeros for a new block creation according to the computation time of the last block.
     * Purpose is to stabilize computation times to some wanted computation time range (as set in the BlockchainConfig).
     * @return the adapted number of leading zeros
     */
    public int adaptLeadingHashZeros() {
        if (getLast().getElapsedTimeInSeconds() < BlockchainConfig.BLOCK_MIN_CREATION_SECONDS) {
            ++currentLeadingHashZeros;
        } else if (getLast().getElapsedTimeInSeconds() > BlockchainConfig.BLOCK_MAX_CREATION_SECONDS) {
            --currentLeadingHashZeros;
        }
        return currentLeadingHashZeros;
    }

    /**
     * provides the chat client with a unique ascending message id (Atomic integer).
     * @return the id incremented counter
     */
    public int getMessageId() {
        return messageIdCount.incrementAndGet();
    }

    /**
     * message receive access point, which is invoked by chat client threads. The queue is concurrent
     * and the invoking frequency is moderate...
     * The blockchain validates all incoming signed chat messages - for their signature authenticity as well
     * as for the validity of the message id given.
     * However, it is safer to synchronize - esp. with pollChat below, which is called by the Controller-thread
     * who drains the queue.
     * @param signedMessage new chat message to offer to the concurrent message queue.
     */
    public synchronized void offerChatMessage(SignedMessage signedMessage) {
        if (clientDataQueue != null) {
            if (VALIDATOR.isDataValid(this, signedMessage, (SignedDataBlock) getLast())) {
                clientDataQueue.offer(signedMessage);
            } else {
                log.warn("Invalid digital message <%s> received ".formatted(signedMessage.toString()));
            }
        }
    }

    /**
     * BlockchainController-thread (main program) calls this method, that drains the message queue and
     * provides this data to miners for storing as block data into the next block.
     * As we want no messages in the first block of the chain and chat clients may already run,
     * the queue is created here on first call and synchronization prevents race condition with sender threads.
     * @return the chat messages as list
     */
    public synchronized List<Signable> pollData() {
        List<Signable> chatData = new ArrayList<>();
        if (clientDataQueue == null) {
            clientDataQueue = new ConcurrentLinkedQueue<>();
        }
        while (!clientDataQueue.isEmpty()) {
            chatData.add(clientDataQueue.poll());
        }
        return chatData;
    }

    /**
     * getter for the blockchain's internal ledger, in case the blockchain "runs on transaction mode"
     * @return the ledger as <name , balance> HashMap
     */
    public Map<String, Integer> getLedger() {
        return ledger;
    }

    /**
     * transaction receive access point, which is invoked by chat client threads. The ledger hash map is concurrent
     * and the invoking frequency is moderate...
     * The blockchain validates all incoming signed transactions - for their signature authenticity as well
     * as for the sender's balance situation allowing the transaction.
     * Synchronization on the ledger is needed, as we don't want several client threads to check and update the ledger
     * simultaneously...
     * @param transaction new transaction to offer to the concurrent ledger map.
     */
    public synchronized void offerTransaction(SignedTransaction transaction) {
        if (clientDataQueue == null) {
            clientDataQueue = new ConcurrentLinkedQueue<>();
        }
        if (VALIDATOR.isDataValid(this, transaction, (SignedDataBlock) getLast())) {
            clientDataQueue.offer(transaction);
            ledger.put(transaction.getSender(), ledger.get(transaction.getSender()) - transaction.getAmount());
            ledger.put(transaction.getReceiver(),
                    ledger.getOrDefault(transaction.getReceiver(), 0) + transaction.getAmount());
        } else {
            log.warn("Invalid digital transaction <%s> received ".formatted(transaction.toString()));
        }
    }

    /**
     * provide the desired program output as specified - used by the logger in PrinterUI
     * @return string representation of the blockchain
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(get(i));
            builder.append(getChangeOfZeroText(get(i).getLeadingHashZeros(),
                    i < size() - 1 ? get(i + 1).getLeadingHashZeros() : currentLeadingHashZeros));
        }
        return builder.toString();
    }

    /**
     * Helper Method for toString() to satisfy specified logger output.
     */
    private String getChangeOfZeroText(int leadingZeros, int nextLeadingZeros) {
        if (leadingZeros > nextLeadingZeros) {
            return "N was decreased by %d%n%n".formatted(leadingZeros - nextLeadingZeros);
        } else if (leadingZeros < nextLeadingZeros) {
            return "N was increased to %d%n%n".formatted(nextLeadingZeros);
        }
        return "N stays the same\n\n";
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}