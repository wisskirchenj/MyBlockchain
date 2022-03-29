package de.cofinpro.blockchain.model;

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
    private static final long serialVersionUID = 34L;


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
     * message output as desired by project specification (stage 2).
     * @return the message to output
     */
    @Override
    public String toString() {
        return ("Block:%nCreated by miner # %d%nId: %d%nTimestamp: %d%nMagic number: %d%nHash of the previous block:%n%s%n" +
                "Hash of the block:%n%s%nBlock was generating for %d seconds%n")
                .formatted(minerId, id, timestamp, magicNumber, previousHash, hash, elapsedTimeInSeconds);
    }
}
