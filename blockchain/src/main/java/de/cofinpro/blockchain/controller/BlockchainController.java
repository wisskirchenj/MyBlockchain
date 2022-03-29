package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.config.BlockchainConfig;
import de.cofinpro.blockchain.model.Block;
import de.cofinpro.blockchain.model.Blockchain;
import de.cofinpro.blockchain.model.InvalidBlockchainException;
import de.cofinpro.blockchain.model.MagicBlockFactory;
import de.cofinpro.blockchain.view.PrinterUI;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Application logic class, that contains the run() method started by Main.
 * Presently it creates and validates a blockchain of given length.
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
        try {
            blockchain = Blockchain.getSerializer().deserialize();
            blockchain.resetIfInvalid();
            continueGeneration(blockchain.size());
            printer.print(blockchain);
        } catch (InvalidBlockchainException e) {
            printer.error("%s%n%s".formatted(e.getMessage(), Arrays.toString(e.getStackTrace())));
        }
    }

    /**
     * this method starts all the computational block creation work. This is done asynchronously by use of a
     * miner thread pool. The thread pool call invokeAny lets all miners run with the same competing task of
     * generating the next block and the fastest wins:-)
     * @param createdBlocks size of the blockchain at invocation time (> 0 iff deserialized blockchain loaded)
     */
    private void continueGeneration(int createdBlocks) {
        int leadingHashZeros = 0;
        ExecutorService miners = Executors.newFixedThreadPool(BlockchainConfig.MINER_COUNT);
        while (createdBlocks < BlockchainConfig.BLOCKCHAIN_LENGTH) {
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
     * @param id id of the block to generate
     * @param leadingHashZeros requested leading zeros for the hash of new block
     * @return List of mine tasks to create new block
     */
    private List<Callable<Block>> getMineTasks(int id, int leadingHashZeros) {
        String previousHash = blockchain.isEmpty() ? "0" : blockchain.getLast().getHash();
        MineTask mineTask = new MineTask(new MagicBlockFactory(leadingHashZeros), id, previousHash);
        return Collections.nCopies(BlockchainConfig.MINER_COUNT, mineTask);
    }
}
