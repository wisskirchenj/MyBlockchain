package de.cofinpro.blockchain.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;


class RSAGeneratorTest {

    private static final String privateKeyPath = "./src/test/resources/data/test_rsa";
    private static final String publicKeyPath = "./src/test/resources/data/test_rsa.pub";

    @ParameterizedTest
    @ValueSource(ints = {512, 1024, 2048})
    void createKeysWithDifferentLengths(int keyLength) throws Exception {
        RSAGenerator rsaGenerator = new RSAGenerator(keyLength);
        assertNotNull(rsaGenerator);
        rsaGenerator.createKeys(privateKeyPath, publicKeyPath);
        KeyPair keyPair = rsaGenerator.getKeyPair();
        assertNotNull(keyPair);
        assertTrue(keyPair.getPrivate().toString().contains("private"));
        assertTrue(keyPair.getPublic().toString().contains("public"));
        assertTrue(keyPair.getPrivate().toString().contains("%d bits".formatted(keyLength)));
        assertTrue(keyPair.getPublic().toString().contains("%d bits".formatted(keyLength)));
        assertTrue(new File(privateKeyPath).exists());
        assertTrue(new File(publicKeyPath).exists());
    }
}