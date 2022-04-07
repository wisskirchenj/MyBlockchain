package de.cofinpro.blockchain.model.magic;

import de.cofinpro.blockchain.model.core.Block;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

/**
 * Data object, that represents one block unit of type MagicBlock, of which a blockchain is assembled.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MagicBlock implements Block {
    @Serial
    private static final long serialVersionUID = 35L;

    private long id;
    private long timestamp;
    private long elapsedTimeInSeconds;
    private int magicNumber;
    private String previousHash;
    private String hash = "";
    private int leadingHashZeros;
    private int minerId = 0;

    public MagicBlock(int id, String previousHash) {
        this.id = id;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
    }

    /**
     * String representation output as desired by project specification (stage 2 ff.).
     * string formatting has been split into two protected methods for usage in subclasses.
     * @return the message to output
     */
    @Override
    public String toString() {
        return getBlockStateString() + getGeneratingString();
    }

    public String getBlockStateString() {
        return ("Block:%nCreated by miner # %d%nId: %d%nTimestamp: %d%nMagic number: %d%n" +
                "Hash of the previous block:%n%s%nHash of the block:%n%s%n")
                .formatted(minerId, id, timestamp, magicNumber, previousHash, hash);
    }

    public String getGeneratingString() {
        return "Block was generating for %d seconds%n".formatted(elapsedTimeInSeconds);
    }
}
