package de.cofinpro.blockchain.model;

import java.io.Serializable;

/**
 * Data object, that represents one block unit, of which the blockchain is assembled.
 */
public interface Block extends Serializable {

    /**
     * default implementation for all block types, as every blockchain must satisfy this hash link rule,
     * that the block's previousHash field matches the hash field of the previous block
     * @param previousBlock the previous block to check hash against
     * @return the message to output
     */
    default boolean hashMatchesPrevious(Block previousBlock) {
        return getPreviousHash().equals(previousBlock.getHash());
    }

    String getHash();

    void setHash(String hash);

    String getPreviousHash();

    long getElapsedTimeInSeconds();

    int getLeadingHashZeros();
}
