package de.cofinpro.blockchain.model;

import java.util.Iterator;

/**
 * validator class, that knows how to validate a complete blockchain - method validateChain(...) as well
 * as to validate a new block before addition to the chain - method isNewBlocValid(...)
 */
public class BlockchainValidator {

    /**
     * validates the whole blockchain regarding hash validation as well as leading zero validation.
     * @param blockchain the chain to validate
     * @throws InvalidBlockchainException extends RuntimeException if blockchain is invalid.
     */
    public void validateChain(Blockchain blockchain) throws InvalidBlockchainException {
        if (blockchain.isEmpty()) {
            return;
        }
        performHashValidation(blockchain);
        performLeadingZerosValidation(blockchain);
    }

    /**
     * performs hash validation on this blockchain by checking, that the previousHash field matches
     * the hash of the next block.
     * The method starts with last block and moves to the beginning of the chain.
     * @param blockchain the chain to validate
     * @throws InvalidBlockchainException extends RuntimeException if blockchain is invalid.
     */
    private void performHashValidation(Blockchain blockchain) throws InvalidBlockchainException {
        Iterator<Block> iterator = blockchain.descendingIterator();
        Block block = iterator.next();
        while (iterator.hasNext()) {
            Block previousBlock = iterator.next();
            if (!block.hashMatchesPrevious(previousBlock)) {
                throw new InvalidBlockchainException("Invalid blockchain!");
            }
                    block = previousBlock;
        }
    }
    /**
     * method, that validates, if the block has at least as many leading hash zeros as required (block field)
     * @param blockchain the chain to validate
     * @throws InvalidBlockchainException extends RuntimeException if blockchain is invalid.
     */
    private void performLeadingZerosValidation(Blockchain blockchain) throws InvalidBlockchainException {
        for (Block block : blockchain) {
            if (!block.getHash().startsWith("0".repeat(block.getLeadingHashZeros()))) {
                throw new InvalidBlockchainException("Block hash has not the requested leading zeros!");
            }
        }
    }

    /**
     * validates a new block - is called by blockchain before adding this block to the chain.
     * @param blockchain the blockchain
     * @param newBlock the new block to validate
     * @param leadingHashZeros the requested leading hash zeros
     * @return validation result
     */
    public boolean isNewBlockValid(Blockchain blockchain, Block newBlock, int leadingHashZeros) {
        boolean valid = newBlock.getHash().startsWith("0".repeat(leadingHashZeros));
        if (!blockchain.isEmpty()) {
            valid &= newBlock.hashMatchesPrevious(blockchain.getLast());
        }
        return valid;
    }
}
