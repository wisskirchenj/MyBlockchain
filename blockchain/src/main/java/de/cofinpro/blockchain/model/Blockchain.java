package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.config.BlockchainConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * our Blockchain class extends from (i.e. is a) LinkedList to be able to iterate it in both directions.
 * it just offers convenient Methods to add a block and get a reverse iterator. Further it can provide a string
 * to print its content.
 */
@Slf4j
public class Blockchain extends LinkedList<Block> {

    @Serial
    private static final long serialVersionUID = 10L;

    private static final BlockchainSerializer serializer = new BlockchainSerializer();

    /**
     * getter for private class serializer - we don't want someone to replace it.
     */
    public static BlockchainSerializer getSerializer() {
        return serializer;
    }

    private transient BlockFactory blockFactory;
    private transient BlockchainValidator validator;

    /**
     * Central method that performs the generation of this blockchain. If the blockchain
     * already contains blocks, the generation adds to the end until the given
     * amount of blocks is reached.
     * It uses BlockFactory to create a block with next free id.
     * Then the hash for this block is calculated, set and the block is added to the Blockchain.
     * @throws NoSuchAlgorithmException in case computer has no SHA256 installed
     */
    public void continueGeneration(int leadingHashZeros) throws NoSuchAlgorithmException {
        initBlockChain(leadingHashZeros);
        resetIfInvalid(validator);
        int currentBlockchainLength = size();
        String previousHash = currentBlockchainLength == 0 ? "0" : getLast().getHash();

        for (int i = currentBlockchainLength; i < BlockchainConfig.BLOCKCHAIN_LENGTH; i++) {
            Block newBlock = computeAndAddBlock(previousHash);
            serializer.serialize(this);
            previousHash = newBlock.getHash();
        }
        validator.validate(this);
    }

    private Block computeAndAddBlock(String previousHash) throws NoSuchAlgorithmException {
        Block block = blockFactory.createBlock(size() + 1, previousHash);
        add(block);
        return block;
    }

    /**
     * before continuing generation, this method is called to check, if the deserializes blockchain state
     * matches the validation rules.
     */
    private void resetIfInvalid(BlockchainValidator validator) {
        try {
            validator.validate(this);
        } catch (InvalidBlockchainException exception) {
            log.warn("Invalid blockchain deserialized!\nStart generating from scratch.");
            clear();
        }
    }

    /**
     * depending on user input, the blockchain sets the adequate concrete BlockFactory.
     * SimpleBlock-creation is used if no leading zeros are requested, MagicBlock-creation is needed otherwise.
     * The # of leading zeros is also stored as (transient) member for validation purpose.
     * @param leadingHashZeros the requested leading zeros for block hashes
     */
    private void initBlockChain(int leadingHashZeros) {
        blockFactory = leadingHashZeros == 0
                ? new SimpleBlockFactory() : new MagicBlockFactory(leadingHashZeros);
        validator = new BlockchainValidator(leadingHashZeros);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        forEach(builder::append);
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
