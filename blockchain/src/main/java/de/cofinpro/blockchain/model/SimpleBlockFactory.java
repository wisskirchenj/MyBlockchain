package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.controller.Cryptographic;

import java.security.NoSuchAlgorithmException;

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
