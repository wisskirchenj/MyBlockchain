package de.cofinpro.blockchain.security;

import de.cofinpro.blockchain.model.signed.SignedMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class RSASignerAndValidatorTest {

    private static final String privateKeyPath = "./src/test/resources/data/test_rsa";
    private static final String publicKeyPath = "./src/test/resources/data/test_rsa.pub";

    private KeyPair testKeyPair;
    private KeyPair otherTestKeyPair;

    @BeforeEach
    void createKeys() throws Exception {
        RSAGenerator rsaGenerator = new RSAGenerator(1024);
        rsaGenerator.createKeys(privateKeyPath, publicKeyPath);
        testKeyPair = rsaGenerator.getKeyPair();
        rsaGenerator.createKeys(privateKeyPath, publicKeyPath);
        otherTestKeyPair = rsaGenerator.getKeyPair();
    }

    @Test
    void whenSigned_isValidWithCorrectPublicKey() {
        String message = "some message";
        byte[] signature = RSASignerAndValidator.sign(message, testKeyPair.getPrivate());
        assertNotNull(signature);
        assertTrue(RSASignerAndValidator.isValid(
                new SignedMessage(message, 1, testKeyPair.getPublic(), signature)));
    }

    @Test
    void whenSigned_IsInvalidWithWrongPublicKey() {
        String message = "some message";
        byte[] signature = RSASignerAndValidator.sign(message, otherTestKeyPair.getPrivate());
        assertNotNull(signature);
        assertFalse(RSASignerAndValidator.isValid(
                new SignedMessage(message, 1, testKeyPair.getPublic(), signature)));
        assertTrue(RSASignerAndValidator.isValid(
                new SignedMessage(message, 1, otherTestKeyPair.getPublic(), signature)));
    }
}