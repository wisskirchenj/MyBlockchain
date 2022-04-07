package de.cofinpro.blockchain.model.core;

import de.cofinpro.blockchain.security.Cryptographic;

import java.security.NoSuchAlgorithmException;

/**
 * Data object, that represents one block unit of type SimpleBlock, of which the blockchain is assembled.
 * @deprecated
 */
@Deprecated(forRemoval = true) // was only used in stage 1
public class SimpleBlockFactory extends BlockFactory {

    @Override
    protected Block newBlockInstance(int id, String previousHash) {
        return new SimpleBlock(id, previousHash);
    }

    @Override
    protected void setHashRelatedFields(Block block) throws NoSuchAlgorithmException {
        block.setHash(Cryptographic.applySha256(block.toString()));
    }
}
