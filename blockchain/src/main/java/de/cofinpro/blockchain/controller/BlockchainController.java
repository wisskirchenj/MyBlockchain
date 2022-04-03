package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.*;
import de.cofinpro.blockchain.view.PrinterUI;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;

/**
 * Application logic class, that contains the run() method started by Main.
 * It deserializes (if file exists) a previously created blockchain and uses a
 * thread pool of miners to continue its generation.
 * Asynchronously a further thread pool of chat clients is started, that continuously generate
 * new chat messages, which they send to the blockchain to store as block data.
 */
@Slf4j
public class BlockchainController {

    // not final so they can be mocked...
    private PrinterUI printer = new PrinterUI();
    private Blockchain blockchain;

    /**
     * entry point invoked by Main after creation of this controller.
     */
    public void run() {
        ExecutorService chatClients = Executors.newFixedThreadPool(CHAT_CLIENT_COUNT);
        try {
            blockchain = Blockchain.getSerializer().deserialize();
            blockchain.resetIfInvalid();
            startChatClients(chatClients);
            continueGeneration(blockchain.size());
            printer.print(blockchain);
        } catch (InvalidBlockchainException e) {
            printer.error("%s%n%s".formatted(e.getMessage(), Arrays.toString(e.getStackTrace())));
        }
        chatClients.shutdownNow();
    }

    /**
     * start all chat clients before the blockchain generation starts. They will produce chat messages
     * during all subsequent program run. The chat clients thread pool is stopped at the end of the run() method
     * calling this method.
     * @param chatClients the thread pool where the ChatClientTask's are submitted to
     */
    private void startChatClients(ExecutorService chatClients) {
        for (int i = 0; i < CHAT_CLIENT_COUNT; i++) {
            chatClients.submit(new ChatClientTask(blockchain, CLIENTS.get(i)));
        }
    }

    /**
     * this method starts all the computational block creation work. This is done asynchronously by use of a
     * miner thread pool. The thread pool call invokeAny lets all miners run with the same competing task of
     * generating the next block and the fastest wins:-)
     * The block generation time is balanced by the blockchain in adapting the requested leading hash zeros,
     * which determine the computational complexity.
     * This parameter is requested by the chain in the loop and provided to the Miner's tasks.
     * @param createdBlocks size of the blockchain at invocation time (> 0 iff deserialized blockchain loaded)
     */
    private void continueGeneration(int createdBlocks) {
        int leadingHashZeros = 0;
        ExecutorService miners = Executors.newFixedThreadPool(MINER_COUNT);
        while (createdBlocks < BLOCKCHAIN_LENGTH) {
            try {
                if (!blockchain.addBlock(miners.invokeAny(getMineTasks(++createdBlocks, leadingHashZeros)))) {
                    throw new InvalidBlockchainException("Invalid block received by miner !");
                }
            } catch (Exception e) {
                log.error("Exception while blockchain creation: " + e.getMessage());
                miners.shutdownNow();
                Thread.currentThread().interrupt();
            }
            leadingHashZeros = blockchain.adaptLeadingHashZeros();
        }
        miners.shutdownNow();
    }

    /**
     * creates a MineTask list (Callable<Block>) with as many copies as threads in the pool.
     * All miners get the same task, that has the computational difficulty as leadingHashZeros
     * as parameter. In addition, the mine task includes all chat messages available at this time
     * which are requested by the blockchain. They are stored as block data.
     * @param id id of the block to generate
     * @param leadingHashZeros requested leading zeros for the hash of new block
     * @return List of mine tasks for creating a new block
     */
    private List<Callable<Block>> getMineTasks(int id, int leadingHashZeros) {
        String previousHash = blockchain.isEmpty() ? "0" : blockchain.getLast().getHash();
        MineTask mineTask = new MineTask(new ChatDataBlockFactory(leadingHashZeros, blockchain.pollChat()),
                id, previousHash);
        return Collections.nCopies(MINER_COUNT, mineTask);
    }
}
