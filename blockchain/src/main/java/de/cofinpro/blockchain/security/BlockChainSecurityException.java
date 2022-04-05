package de.cofinpro.blockchain.security;

/**
 * dedicated exception thrown in case of a negative validation of a message signature
 */
public class BlockChainSecurityException extends RuntimeException {
    public BlockChainSecurityException(String message) {
        super(message);
    }
}
