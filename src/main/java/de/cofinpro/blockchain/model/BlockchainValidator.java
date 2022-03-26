package de.cofinpro.blockchain.model;

import java.util.Iterator;

public class BlockchainValidator {

    private final int leadingHashZeros;

    public BlockchainValidator(int leadingHashZeros) {
        this.leadingHashZeros = leadingHashZeros;
    }

    /**
     * validates this blockchain by checking the previousHash field matches with the hash of the next block.
     * The method starts with last block and moves to the beginning of the chain.
     * @throws InvalidBlockchainException extends RuntimeException if blockchain is invalid.
     */
    public void validate(Blockchain blockchain) throws InvalidBlockchainException {
        if (blockchain.isEmpty()) {
            return;
        }
        performBlockTypeValidation(blockchain);
        performHashValidation(blockchain);
    }

    private void performBlockTypeValidation(Blockchain blockchain) throws InvalidBlockchainException {
        Iterator<Block> iterator = blockchain.iterator();
        String leadingZeros = "0".repeat(leadingHashZeros);
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if ((block instanceof SimpleBlock && leadingHashZeros > 0)
                    || !block.getHash().startsWith(leadingZeros)) {
                throw new InvalidBlockchainException("Block type does not match requested type!");
            }
        }
    }

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
}
