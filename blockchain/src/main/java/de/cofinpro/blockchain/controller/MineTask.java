package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.signed.DataBlockFactory;
import de.cofinpro.blockchain.model.signed.Signable;
import de.cofinpro.blockchain.model.signed.SignedDataBlock;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Callable implementation, that is started in the thread pool (ExecutorService implementation).
 * A Callable<Block> is a functional interface with method signature 'Block call();'.
 */
public class MineTask implements Callable<SignedDataBlock> {

    private final int id;
    private final String previousHash;
    private final DataBlockFactory<List<Signable>> blockFactory;

    public MineTask(DataBlockFactory<List<Signable>> blockFactory, int id, String previousHash) {
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
    public SignedDataBlock call() throws Exception {
        return blockFactory.createBlock(id, previousHash);
    }
}
