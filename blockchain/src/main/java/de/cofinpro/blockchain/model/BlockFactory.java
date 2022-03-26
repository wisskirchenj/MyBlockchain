package de.cofinpro.blockchain.model;

import java.security.NoSuchAlgorithmException;

/**
 * Abstract base class for the implementation of the "factory method" creation pattern.
 * Our abstract block factory provides the basic creation workflow for a block.
 * Typically, it is invoked by y a Blockchain.
 */
public abstract class BlockFactory {

    /**
     * the factory method that creates a new block block
     * @param id the id of the block to create
     * @param previousHash previousHash the hash link to the previous created block in a blockchain.
     * @return created block
     */
    public Block createBlock(int id, String previousHash) throws NoSuchAlgorithmException {
        Block block = newBlockInstance(id, previousHash);
        setHashRelatedFields(block);
        return block;
    }

    protected abstract Block newBlockInstance(int id, String previousHash);

    protected abstract void setHashRelatedFields(Block block) throws NoSuchAlgorithmException;
}
