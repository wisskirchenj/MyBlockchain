package de.cofinpro.blockchain.model;

import java.io.Serializable;

/**
 * Data object, that represents one block unit, of which the blockchain is assembled.
 */

public interface Block extends Serializable {

    /**
     * message output as desired by project specification (stage 1).
     * @return the message to output
     */
    default boolean hashMatchesPrevious(Block previousBlock) {
        return getPreviousHash().equals(previousBlock.getHash());
    }

    String getHash();

    void setHash(String hash);

    String getPreviousHash();
}
