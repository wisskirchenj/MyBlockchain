package de.cofinpro.blockchain.model;

/**
 * thread-safe block factory, that creates blocks with ascending id's.
 * It does not wait for the hash-creations - so this can be decoupled (if needed..).
 */
public class BlockFactory {

    private static volatile int createdCount = 0;

    private BlockFactory() {
        // no instantiation
    }

    /**
     * the factory method that provides the next block
     * @param previousHash previousHash, that the created block gets set.
     * @return created block
     */
    public static Block getNextBlock(String previousHash) {
        return new SimpleBlock(incrementBlockCounter(), previousHash);
    }

    private static synchronized int incrementBlockCounter() {
       return ++createdCount;
    }

    public static void setIdOffset(int currentBlockchainLength) {
        createdCount = currentBlockchainLength;
    }
}
