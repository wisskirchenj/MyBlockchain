package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.config.BlockchainConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * our Blockchain class extends from (i.e. is a) LinkedList to be able to iterate it in both directions.
 * the blockchain uses a static serializer (which it offers to the controller for deserialization) and
 * a static BlockchainValidator, that comprises all internal validation logic.
 * Beside chaining and validating blocks, the blockchain class regulates the computation time for the
 * next block by adapting a complexity in terms of leadingHashZeros for the upcoming block.
 *
 * Further it keeps a concurrent message queue for chat messages, which are stored as block data
 * and offers methods to accept and poll data.
 * (would be better to single messages out of this class, but it's strictly specified by hyperskill...)
 */
@Slf4j
public class Blockchain extends LinkedList<Block> {

    @Serial
    private static final long serialVersionUID = 11L;

    private static final BlockchainValidator VALIDATOR = new BlockchainValidator();
    private static final BlockchainSerializer SERIALIZER = new BlockchainSerializer();

    /**
     * getter for private class serializer - we don't want someone to replace it.
     */
    public static BlockchainSerializer getSerializer() {
        return SERIALIZER;
    }

    private transient Queue<String> chatMessageQueue = null;
    private transient int currentLeadingHashZeros = 0;

    /**
     * validates and adds a new block (as typically received by a miner). Also, a serialization of the
     * extended chain is done to avoid data loss.
     * @param newBlock the received block
     * @return false if block invalid (it is not added in that case), true else
     */
    public boolean addBlock(Block newBlock) {
        if (!VALIDATOR.isNewBlockValid(this, newBlock, currentLeadingHashZeros)) {
            return false;
        }
        add(newBlock);
        SERIALIZER.serialize(this);
        return true;
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
     * message post access point, which is invoked by chat client threads. The queue is concurrent
     * and the invoking frequency is moderate...
     * However, it is safer to synchronize - esp. with pollChat below, which is called by the Controller-thread
     * who drains the queue.
     * @param message new chat message to offer to the concurrent message queue.
     */
    public synchronized void sendChatMessage(String message) {
        if (chatMessageQueue != null) {
            chatMessageQueue.offer(message);
        }
    }

    /**
     * BlockchainController-thread (main program) calls this method, that drains the message queue and
     * provides this data to miners for storing as block data into the next block.
     * As we want no messages in the first block of the chain and chat clients may already run,
     * the queue is created here on first call and synchronization prevents race condition with sender threads.
     * @return the chat messages as list
     */
    public synchronized List<String> pollChat() {
        List<String> chatData = new ArrayList<>();
        if (chatMessageQueue == null) {
            chatMessageQueue = new ConcurrentLinkedQueue<>();
        }
        while (!chatMessageQueue.isEmpty()) {
            chatData.add(chatMessageQueue.poll());
        }
        return chatData;
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
