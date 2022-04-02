package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.controller.Cryptographic;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

/**
 * Concrete block factory (by Factory method pattern), that can create one block type - the MagicBlock.
 */
public class MagicBlockFactory extends BlockFactory {

    private final String leadingZeroString;
    private final Random random = new Random();

    public MagicBlockFactory(int leadingHashZeros) {
        this.leadingZeroString = "0".repeat(leadingHashZeros);
    }

    @Override
    protected Block newBlockInstance(int id, String previousHash) {
        return new MagicBlock(id, previousHash);
    }

    @Override
    protected void setHashRelatedFields(Block block) throws NoSuchAlgorithmException {
        if (!(block instanceof MagicBlock magicBlock)) {
            return;
        }
        String hash;
        do {
            magicBlock.setMagicNumber(random.nextInt(Integer.MAX_VALUE));
            hash = Cryptographic.applySha256(magicBlock.toString());
        } while (!hash.startsWith(leadingZeroString) && !Thread.interrupted());
        magicBlock.setHash(hash);
        magicBlock.setElapsedTimeInSeconds((new Date().getTime() - magicBlock.getTimestamp()) / 1000);
        magicBlock.setLeadingHashZeros(leadingZeroString.length());
    }
}