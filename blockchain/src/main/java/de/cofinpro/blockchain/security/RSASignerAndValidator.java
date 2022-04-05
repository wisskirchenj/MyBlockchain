package de.cofinpro.blockchain.security;

import de.cofinpro.blockchain.model.SignedMessage;

import java.security.*;

/**
 * Helper class with static methods to sign a message with a private key and verify a received signature
 * with the public key.
 */
public class RSASignerAndValidator {

    private RSASignerAndValidator() {
        // prevent instances
    }

    public static byte[] sign(String message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes());
            return signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new BlockChainSecurityException("Exception signing chat message: " + e.getMessage());
        }
    }

    public static boolean isValid(SignedMessage signedMessage) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(signedMessage.getPublicKey());
            signature.update(signedMessage.getMessage().getBytes());
            return signature.verify(signedMessage.getSigned());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new BlockChainSecurityException("Exception signing chat message: " + e.getMessage());
        }
    }
}
