package de.cofinpro.blockchain.model;

import java.io.Serializable;

/**
 * Interface for block objects, that represents one block unit, of which the blockchain is assembled.
 */
public interface Block extends Serializable {

    /**
     * @return SHA256 cryptographic hash of this block
     */
    String getHash();

    /**
     * @return hash link to previous block in blockchain
     */
    String getPreviousHash();

    /**
     * default implementation for all block types, as every blockchain must satisfy this hash link rule,
     * that the block's previousHash field matches the hash field of the previous block
     * @param previousBlock the previous block to check hash against
     * @return the message to output
     */
    default boolean hashMatchesPrevious(Block previousBlock) {
        return getPreviousHash().equals(previousBlock.getHash());
    }

    /**
     * @return timestamp of instantiation of this block object
     */
    long getTimestamp();

    /**
     * @return creation time of the block as millisecond difference between instantiation time
     *          and successful hash computation on block data
     */
    long getElapsedTimeInSeconds();
    /**
     * @return amount of required leading zeros in the hash of this block, which makes out the
     *          computational complexity and thus the complexity to forge the block from outside
     */
    int getLeadingHashZeros();

    /**
     * @param minerId creator id of the miner who produced this block
     */
    void setMinerId(int minerId);

    void setHash(String hash);

    void setElapsedTimeInSeconds(long elapsedTimeInSeconds);

    void setLeadingHashZeros(int leadingHashZeros);

    void setMagicNumber(int magicNumber);
}
