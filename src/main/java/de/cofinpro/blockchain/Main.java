package de.cofinpro.blockchain;

import de.cofinpro.blockchain.config.BlockchainConfig;
import de.cofinpro.blockchain.controller.BlockchainController;

public class Main {

    public static void main(String[] args) {
        new BlockchainController().run(BlockchainConfig.BLOCKCHAIN_LENGTH);
    }
}
