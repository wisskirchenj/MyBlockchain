package de.cofinpro.blockchain.security;

import de.cofinpro.blockchain.model.signed.Signable;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Helper class with static methods to sign a message with a private key and verify a received signature
 * with the public key.
 */
public class RSASignerAndValidator {

    private RSASignerAndValidator() {
        // prevent instances
    }

    /**
     * static signing utility method
     * @param message message to sign
     * @param privateKey the private key to use
     * @return the signature as byte array
     */
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

    /**
     * method to verify a signed message by a receiver regarding authenticity,
     * The Signable object contains everything to do that (message that was signed - given by toString(),
     * public key and signature).
     * @param signable message to sign
     * @return the verification result.
     */
    public static boolean isValid(Signable signable) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(signable.getPublicKey());
            signature.update(signable.toString().getBytes());
            return signature.verify(signable.getSigned());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new BlockChainSecurityException("Exception signing chat message: " + e.getMessage());
        }
    }
}
