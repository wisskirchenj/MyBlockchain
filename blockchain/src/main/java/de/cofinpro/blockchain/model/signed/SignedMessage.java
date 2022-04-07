package de.cofinpro.blockchain.model.signed;

import de.cofinpro.blockchain.model.signed.Signable;
import lombok.Getter;

import java.io.Serial;
import java.security.PublicKey;

/**
 * Immutable Data object for storage as data block in the blockchain. It represents an authenticated (i.e digitally
 * signed) message, which is accessible as string field, as well as the public key for receiver verification and
 * the signed message. Further a unique id is provided - to prevent copying the message again to the blockchain.
 */
@Getter
public class SignedMessage implements Signable {

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

    /**
     * String representation is just the message string (it contains the is already) and signed
     * bytes plus key are kept out
     * @return string representation of Signed Message
     */
    @Override
    public String toString() {
        return message;
    }
}
