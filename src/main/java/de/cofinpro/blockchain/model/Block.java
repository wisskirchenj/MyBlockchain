package de.cofinpro.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data object, that represents one block unit, of which the blockchain is assembled.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Block {
    private long id;
    private long timestamp;
    private String previousHash;
    private String hash = "";

    public Block(long id, String previousHash) {
        this.id = id;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
    }

    /**
     * message output as desired by project specification (stage 1).
     * @return the message to output
     */
    @Override
    public String toString() {
        return "Block:%nId: %d%nTimestamp: %d%nHash of the previous block:%n%s%nHash of the block:%n%s%n%n"
                .formatted(id, timestamp, previousHash, hash);
    }
}
