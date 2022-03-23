package de.cofinpro.blockchain.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class containing static functions for cryptographic use - as a cryptographic
 * SHA-256 hash-function
 */
public class Cryptographic {

    private Cryptographic() {
        // no instantiation possible
    }

    /**
     * Applies Sha256-algorithm to a string and returns a hash.
     * @param input the string, where the algorithm is applied to
     * @return the cryptographic hash determined
     * @throws NoSuchAlgorithmException if the computer running program does not support SHA256
     */
    public static String applySha256(String input) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        /* Applies sha256 to our input */
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte elem: hash) {
            String hex = Integer.toHexString(0xff & elem);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
