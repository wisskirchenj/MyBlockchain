package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.Block;
import de.cofinpro.blockchain.model.BlockFactory;
import de.cofinpro.blockchain.model.Blockchain;
import de.cofinpro.blockchain.model.BlockchainSerializer;
import de.cofinpro.blockchain.view.PrinterUI;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * Application logic class, that contains the run() method started by Main.
 * Presently it creates and validates a blockchain of given length.
 */
public class BlockchainController {

    private final int blockchainLength;
    // not final so they can be mocked...
    private PrinterUI printer = new PrinterUI();
    private Blockchain blockchain;
    private BlockchainSerializer serializer = new BlockchainSerializer();

    /**
     * Constructor
     * @param blockchainLength amount of blocks to create.
     */
    public BlockchainController(int blockchainLength) {
        this.blockchainLength = blockchainLength;
    }

    /**
     * Generation of a Blockchain, uses BlockFactory to create a block with next free id.
     * Then the hash for this block is calculated, set and the block is added to the Blockchain.
     * @throws NoSuchAlgorithmException in case computer has no SHA256 installed
     */
    private void generateBlockchain() throws NoSuchAlgorithmException {
        String previousHash = "0";
        for (int i = 0; i < blockchainLength; i++) {
            Block newBlock = BlockFactory.getNextBlock(previousHash);
            String hash = Cryptographic.applySha256(newBlock.toString());
            newBlock.setHash(hash);
            blockchain.addBlock(newBlock);
            serializer.serialize(blockchain);
            previousHash = hash;
        }
    }

    /**
     * entry point invoked by Main after creation of this controller.
     */
    public void run() {
        blockchain = serializer.deserialize();
        if (!blockchain.isEmpty()) {
            validateBlockchain();
            printer.printBlockchain(blockchain); //TODO for test now - later remove next 3 lines
            System.out.println("Blocks were deserialized!");
            return;
        }
        try {
            generateBlockchain();
        } catch (NoSuchAlgorithmException e) {
            printer.error("SHA256 algorithm not implemented! ");
            return;
        }
        if (validateBlockchain()) {
            printer.printBlockchain(blockchain);
        } else {
            printer.error("Invalid blockchain!");
        }
    }

    /**
     * validates the blockchain field by checking the previousHash field matches with the hash of the next block.
     * The method starts with last block and moves to the beginning of the chain.
     * @return true if blockchain is valid, false else
     */
    private boolean validateBlockchain() {
        Iterator<Block> iterator = blockchain.getBackwardsIterator();
        if (!iterator.hasNext()) {
            return false; //empty blockchain
        }
        Block block = iterator.next();
        while (iterator.hasNext()) {
            Block previousBlock = iterator.next();
            if (!block.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
            block = previousBlock;
        }
        return true;
    }
}
