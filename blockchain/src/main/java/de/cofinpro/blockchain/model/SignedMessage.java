package de.cofinpro.blockchain.model;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.security.PublicKey;

/**
 * Immutable Data object for storage as data block in the blockchain. It represents an authenticated (i.e digitally
 * signed) message, which is accessible as string field, as well as the public key for receiver verification and
 * the signed message. Further a unique id is provided - to prevent copying the message again to the blockchain.
 */
@Getter
public class SignedMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 50L;

    private final String message;
    private final int id;
    private final PublicKey publicKey;
    private final byte[] signed;

    public SignedMessage(String message, int id, PublicKey publicKey, byte[] signed) {
        this.message = message;
        this.id = id;
        this.publicKey = publicKey;
        this.signed = signed;
    }

    @Override
    public String toString() {
        return message;
    }
}
