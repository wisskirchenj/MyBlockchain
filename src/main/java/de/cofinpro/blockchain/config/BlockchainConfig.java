package de.cofinpro.blockchain.config;

/**
 * class that collects configuration constants etc..
 */
public class BlockchainConfig {

    private BlockchainConfig() {
        // no instantiation
    }

    public static final int BLOCKCHAIN_LENGTH = 15;
    public static final String SERIALIZE_PATH = "./src/main/resources/data/blockchain.ser";
}
