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
    private static final long serialVersionUID = 30L;

    private long id;
    private long timestamp;
    private long elapsedTime;
    private int magicNumber;
    private String previousHash;
    private String hash = "";

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
        return ("Block:%nId: %d%nTimestamp: %d%nMagic number: %d%nHash of the previous block:%n%s%n" +
                "Hash of the block:%n%s%nBlock was generating for %d seconds%n%n")
                .formatted(id, timestamp, magicNumber, previousHash, hash, elapsedTime);
    }
}
