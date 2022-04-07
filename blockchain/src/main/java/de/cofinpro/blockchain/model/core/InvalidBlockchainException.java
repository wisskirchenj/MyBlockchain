package de.cofinpro.blockchain.model.core;

/**
 * whenever an invalid blockchain is detected (e.g. a deserialized blockchain is invalid),
 * a runtime exception is thrown.
 */
public class InvalidBlockchainException extends RuntimeException {

    public InvalidBlockchainException(String message) {
        super(message);
    }
}
