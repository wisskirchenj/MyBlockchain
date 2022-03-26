package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.controller.Cryptographic;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

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
            hash = Cryptographic.applySha256(block.toString());
        } while (!hash.startsWith(leadingZeroString));
        magicBlock.setHash(hash);
        magicBlock.setElapsedTime((new Date().getTime() - magicBlock.getTimestamp()) / 1000);
    }
}