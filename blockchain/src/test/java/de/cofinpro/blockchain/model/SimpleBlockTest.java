package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.model.core.SimpleBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class SimpleBlockTest {

    SimpleBlock simpleBlock;

    @BeforeEach
    void setup() {
        simpleBlock = new SimpleBlock(1, "previousHash");
    }

    @Test
    void testToString() {
        simpleBlock.setHash("hash");
        long timestamp = new Date().getTime();
        simpleBlock.setTimestamp(timestamp);
        Assertions.assertEquals(String.format("Block:%nId: %d%nTimestamp: %d%nHash of the previous block:%n%s%nHash of the block:%n%s%n%n",
                1, timestamp, "previousHash", "hash"), simpleBlock.toString());
    }

    @Test
    void setTimestamp() {
        long timestamp = new Date().getTime();
        simpleBlock.setTimestamp(timestamp);
        Assertions.assertEquals(timestamp, simpleBlock.getTimestamp());
    }

    @Test
    void setHash() {
        String hash = "hash";
        simpleBlock.setHash(hash);
        Assertions.assertEquals(hash, simpleBlock.getHash());
    }
}