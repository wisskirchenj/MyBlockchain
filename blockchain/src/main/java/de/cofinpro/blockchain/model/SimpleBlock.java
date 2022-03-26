package de.cofinpro.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

/**
 * Data object, that represents one block unit of type SimpleBlock, of which the blockchain is assembled.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleBlock implements Block {
    @Serial
    private static final long serialVersionUID = 20L;

    private long id;
    private long timestamp;
    private String previousHash;
    private String hash = "";

    public SimpleBlock(long id, String previousHash) {
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

