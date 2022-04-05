package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.security.Cryptographic;

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

    /**
     * computation intensive step of the block creation, where the hash is calculated until it satisfies
     * sufficient leading zeros - method is also called from DataBlock subclasses without overriding and uses the
     * toStringCashed() method of the data carrying Block object in this case. :-)
     * That way, the hash creation includes the block's data.
     * @param block block to enhance
     * @throws NoSuchAlgorithmException if SHA256 not implemented
     */
    @Override
    protected void setHashRelatedFields(Block block) throws NoSuchAlgorithmException {
        String hash;
        do {
            block.setMagicNumber(random.nextInt(Integer.MAX_VALUE));
            hash = Cryptographic.applySha256(block.toString());
        } while (!hash.startsWith(leadingZeroString) && !Thread.interrupted());
        block.setHash(hash);
        block.setElapsedTimeInSeconds((new Date().getTime() - block.getTimestamp()) / 1000);
        block.setLeadingHashZeros(leadingZeroString.length());
    }
}