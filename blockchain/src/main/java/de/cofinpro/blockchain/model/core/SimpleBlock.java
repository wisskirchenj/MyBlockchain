package de.cofinpro.blockchain.model.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

/**
 * Data object, that represents one block unit of type SimpleBlock, of which the blockchain is assembled.
 * @deprecated
 */
@Deprecated(since = "25.03.22")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleBlock implements Block {
    @Serial
    private static final long serialVersionUID = 21L;

    private long id;
    private long timestamp;
    private String previousHash;
    private String hash = "";
    private int minerId = 0;

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

    /**
     * simple blocks have no leading zeros
     * @return always 0
     */
    @Override
    public int getLeadingHashZeros() {
        return 0;
    }

    @Override
    public void setElapsedTimeInSeconds(long elapsedTimeInSeconds) {
        //
    }

    @Override
    public void setLeadingHashZeros(int leadingHashZeros) {
        //
    }

    @Override
    public void setMagicNumber(int magicNumber) {
        //
    }

    /**
     * not implemented for SimpleBlock
     * @return always 0
     */
    @Override
    public long getElapsedTimeInSeconds() {
        return 0;
    }
}

