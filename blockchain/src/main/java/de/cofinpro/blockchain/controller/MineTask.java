package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.Block;
import de.cofinpro.blockchain.model.BlockFactory;
import de.cofinpro.blockchain.model.MagicBlock;

import java.util.concurrent.Callable;

/**
 * Callable implementation, that is started in the thread pool (ExecutorService implementation).
 * A Callable<Block> is a functional interface with method signature 'Block call();'.
 */
public class MineTask implements Callable<Block> {

    private final int id;
    private final String previousHash;
    private final BlockFactory blockFactory;

    public MineTask(BlockFactory blockFactory, int id,  String previousHash) {
        this.blockFactory = blockFactory;
        this.id = id;
        this.previousHash = previousHash;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Block call() throws Exception {
        Block block = blockFactory.createBlock(id, previousHash);
        String threadName = Thread.currentThread().getName();
        ((MagicBlock) block).setMinerId(Integer.parseInt(threadName.substring(threadName.lastIndexOf('-') + 1)));
        return block;
    }
}
