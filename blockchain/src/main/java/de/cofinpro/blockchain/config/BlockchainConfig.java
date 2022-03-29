package de.cofinpro.blockchain.config;

/**
 * class that collects configuration constants etc..
 */
public class BlockchainConfig {

    private BlockchainConfig() {
        // no instantiation
    }

    public static final int BLOCKCHAIN_LENGTH = 8;
    public static final String SERIALIZE_PATH = "./blockchain/src/main/resources/data/blockchain.ser";
    public static final int MINER_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int BLOCK_MIN_CREATION_SECONDS = 5;
    public static final int BLOCK_MAX_CREATION_SECONDS = 20;
}
