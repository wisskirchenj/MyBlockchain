package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.Block;
import de.cofinpro.blockchain.model.ChatDataBlockFactory;

import java.util.concurrent.Callable;

/**
 * Callable implementation, that is started in the thread pool (ExecutorService implementation).
 * A Callable<Block> is a functional interface with method signature 'Block call();'.
 */
public class MineTask implements Callable<Block> {

    private final int id;
    private final String previousHash;
    private final ChatDataBlockFactory blockFactory;

    public MineTask(ChatDataBlockFactory blockFactory, int id, String previousHash) {
        this.blockFactory = blockFactory;
        this.id = id;
        this.previousHash = previousHash;
    }

    /**
     * Computes a new block with chat data stored, or throws an exception if unable to do so.
     * @return computed block
     * @throws Exception if unable to compute a result
     */
    @Override
    public Block call() throws Exception {
        Block block = blockFactory.createBlock(id, previousHash);
        String threadName = Thread.currentThread().getName();
        block.setMinerId(Integer.parseInt(threadName.substring(threadName.lastIndexOf('-') + 1)));
        return block;
    }
}
