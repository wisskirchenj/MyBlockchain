package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.controller.Cryptographic;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * our Blockchain class extends from (i.e. is a) LinkedList to be able to iterate it in both directions.
 * it just offers convenient Methods to add a block and get a reverse iterator. Further it can provide a string
 * to print its content.
 */
@Slf4j
public class Blockchain extends LinkedList<Block> {

    @Serial
    private static final long serialVersionUID = 10L;

    private static final BlockchainSerializer serializer = new BlockchainSerializer();

    /**
     * getter for private class serializer - we don't want someone to replace it.
     */
    public static BlockchainSerializer getSerializer() {
        return serializer;
    }

    /**
     * Central method that performs the generation of this blockchain. If the blockchain
     * already contains blocks, the generation adds to the end until the given
     * amount of blocks is reached.
     * It uses BlockFactory to create a block with next free id.
     * Then the hash for this block is calculated, set and the block is added to the Blockchain.
     * @throws NoSuchAlgorithmException in case computer has no SHA256 installed
     */
    public void continueGeneration(int blockchainLength) throws NoSuchAlgorithmException {
        resetIfInvalid();
        int currentBlockchainLength = size();
        BlockFactory.setIdOffset(currentBlockchainLength);
        String previousHash = currentBlockchainLength == 0 ? "0" : getLast().getHash();

        for (int i = currentBlockchainLength; i < blockchainLength; i++) {
            Block newBlock = computeAndAddBlock(previousHash);
            serializer.serialize(this);
            previousHash = newBlock.getHash();
        }
        validate();
    }

    private void resetIfInvalid() {
        try {
            validate();
        } catch (InvalidBlockchainException exception) {
            log.warn("Invalid blockchain deserialized!\nStart generating from scratch.");
            clear();
        }
    }

    /**
     * validates this blockchain by checking the previousHash field matches with the hash of the next block.
     * The method starts with last block and moves to the beginning of the chain.
     * @throws InvalidBlockchainException extends RuntimeException if blockchain is invalid.
     */
    private void validate() throws InvalidBlockchainException {
        if (isEmpty()) {
            return;
        }
        Iterator<Block> iterator = descendingIterator();
        Block block = iterator.next();
        while (iterator.hasNext()) {
            Block previousBlock = iterator.next();
            if (!block.hashMatchesPrevious(previousBlock)) {
                throw new InvalidBlockchainException("Invalid blockchain!");
            }
            block = previousBlock;
        }
    }

    private Block computeAndAddBlock(String previousHash) throws NoSuchAlgorithmException {
        Block block = BlockFactory.getNextBlock(previousHash);
        block.setHash(Cryptographic.applySha256(block.toString()));
        add(block);
        return block;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        forEach(builder::append);
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
