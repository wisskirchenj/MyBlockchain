package de.cofinpro.blockchain.model.core;

import java.security.NoSuchAlgorithmException;

/**
 * Abstract base class for the implementation of the "factory method" creation pattern.
 * Our abstract block factory provides the basic creation workflow for a block.
 * Typically, it is invoked by a Miner thread.
 */
public abstract class BlockFactory {

    /**
     * the factory method that creates a new block
     * @param id the id of the block to create
     * @param previousHash previousHash the hash link to the previous created block in a blockchain.
     * @return created block
     */
    public Block createBlock(int id, String previousHash) throws NoSuchAlgorithmException {
        Block block = newBlockInstance(id, previousHash);
        setHashRelatedFields(block);
        return block;
    }

    /**
     * instantiation step of the block creation. ID, hash link and creation timestamp are set.
     * @param id block id
     * @param previousHash hash link to previous in chain
     * @return new block instance
     */
    protected abstract Block newBlockInstance(int id, String previousHash);

    /**
     * creation step where the computational work is done - block data storage
     * and hash requirements for leading zeros
     * @param block block to enhance
     * @throws NoSuchAlgorithmException if SHA256 implementation not available
     */
    protected abstract void setHashRelatedFields(Block block) throws NoSuchAlgorithmException;
}
