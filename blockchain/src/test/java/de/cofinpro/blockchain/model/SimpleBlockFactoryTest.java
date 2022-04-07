package de.cofinpro.blockchain.model;

import de.cofinpro.blockchain.model.core.Block;
import de.cofinpro.blockchain.model.core.SimpleBlock;
import de.cofinpro.blockchain.model.core.SimpleBlockFactory;
import de.cofinpro.blockchain.security.Cryptographic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBlockFactoryTest {

    private SimpleBlockFactory blockFactory;

    @BeforeEach
    void setUp() {
        blockFactory = new SimpleBlockFactory();
    }


    @Test
    void creationTest() throws NoSuchAlgorithmException {
        Block block = blockFactory.createBlock(3, "some hash");
        assertNotNull(block);
        assertEquals("some hash", block.getPreviousHash());
        assertEquals(3, ((SimpleBlock) block).getId());
    }

    @Test
    void setHashRelatedFieldsTest() throws NoSuchAlgorithmException {
        Block block = blockFactory.createBlock(5, "previous hash");
        assertNotNull(block);
        assertEquals("previous hash", block.getPreviousHash());
        assertEquals(5, ((SimpleBlock) block).getId());
        String hash = block.getHash();
        block.setHash("");
        String expectedHash = Cryptographic.applySha256(block.toString());
        assertEquals(expectedHash, hash);
    }
}