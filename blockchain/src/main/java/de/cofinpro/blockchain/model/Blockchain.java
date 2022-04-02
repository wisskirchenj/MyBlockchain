package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.config.BlockchainConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.LinkedList;

/**
 * our Blockchain class extends from (i.e. is a) LinkedList to be able to iterate it in both directions.
 * it just offers convenient Methods to add a block and get a reverse iterator. Further it can provide a string
 * to print its content.
 */
@Slf4j
public class Blockchain extends LinkedList<Block> {

    @Serial
    private static final long serialVersionUID = 11L;

    private static final BlockchainValidator VALIDATOR = new BlockchainValidator();
    private static final BlockchainSerializer SERIALIZER = new BlockchainSerializer();

    /**
     * getter for private class serializer - we don't want someone to replace it.
     */
    public static BlockchainSerializer getSerializer() {
        return SERIALIZER;
    }

    private transient volatile int currentLeadingHashZeros = 0;

    /**
     * validates and adds a new block (as typically received by a miner). Also, a serialization of the
     * extended chain is done to avoid data loss. The method and the adaptLeadingHashZeros below are synchronized
     * to avoid mismatched validations. (adding must be left before adapting the field)
     * @param newBlock the received block
     * @return false if block invalid (it is not added in that case), true else
     */
    public synchronized boolean addBlock(Block newBlock) {
        if (!VALIDATOR.isNewBlockValid(this, newBlock, currentLeadingHashZeros)) {
            return false;
        }
        add(newBlock);
        SERIALIZER.serialize(this);
        return true;
    }

    /**
     * before continuing generation, this method is called to check, if the deserializes blockchain state
     * matches the validation rules.
     */
    public void resetIfInvalid() {
        try {
            VALIDATOR.validateChain(this);
        } catch (InvalidBlockchainException exception) {
            log.warn("Invalid blockchain deserialized!\nStart generating from scratch.");
            clear();
        }
    }

    /**
     * Adapts the requested leading zeros for a new block creation according to the computation time of the last block.
     * Purpose is to stabilize computation times to some wanted computation time range (as set in the BlockchainConfig).
     * This method and the addBlock above are synchronized
     * to avoid mismatched validations. (adding must be left before adapting the field)
     * @return the adapted number of leading zeros
     */
    public synchronized int adaptLeadingHashZeros() {
        if (getLast().getElapsedTimeInSeconds() < BlockchainConfig.BLOCK_MIN_CREATION_SECONDS) {
            ++currentLeadingHashZeros;
        } else if (getLast().getElapsedTimeInSeconds() > BlockchainConfig.BLOCK_MAX_CREATION_SECONDS) {
            --currentLeadingHashZeros;
        }
        return currentLeadingHashZeros;
    }

    /**
     * Helper Method for toString() to satisfy specified logger output.
     */
    private String getChangeOfZeroText(int leadingZeros, int nextLeadingZeros) {
        if (leadingZeros > nextLeadingZeros) {
            return "N was decreased by 1\n\n";
        } else if (leadingZeros < nextLeadingZeros) {
            return "N was increased to %d%n%n".formatted(nextLeadingZeros);
        }
        return "N stays the same\n\n";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(get(i));
            builder.append(getChangeOfZeroText(get(i).getLeadingHashZeros(),
                    i < size() - 1 ? get(i + 1).getLeadingHashZeros() : currentLeadingHashZeros));
        }
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
