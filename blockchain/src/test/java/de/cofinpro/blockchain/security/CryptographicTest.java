package de.cofinpro.blockchain.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CryptographicTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "short", "long\nlongnnbbbbbbbbbbbbbbbbbbbbb"})
    void applySha256_isHexAnd256bits(String message) throws Exception {
        String hash = Cryptographic.applySha256(message);
        assertTrue(hash.matches("[0-9a-f]+"));
        assertEquals(256 / 4, hash.length());
    }

    @Test
    void applySha256_isDeterministic() throws Exception {
        String message = "some random message !";
        String hash1 = Cryptographic.applySha256(message);
        String hash2 = Cryptographic.applySha256(message);
        String hash3 = Cryptographic.applySha256(message);
        assertEquals(hash1, hash2);
        assertEquals(hash3, hash2);
    }

}