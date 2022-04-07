package de.cofinpro.blockchain.model.signed;

import lombok.Getter;

import java.io.Serial;
import java.security.PublicKey;

/**
 * Signed transaction, that can be stored and verified (using public key and digital signature)
 * as block data list element.
 */
@Getter
public class SignedTransaction implements Signable {
    @Serial
    private static final long serialVersionUID = 60L;

    private final String sender;
    private final int amount;
    private final String receiver;
    private final PublicKey publicKey;

    private byte[] signed;

    public SignedTransaction(String sender, int amount, String receiver, PublicKey publicKey) {
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;
        this.publicKey = publicKey;
    }

    /**
     * set the signature. Not part of the constructor here, as the toString() method is signed after
     * instantiation.
     * @param signed the digital signature
     */
    public void setSigned(byte[] signed) {
        this.signed = signed;
    }

    /**
     * String representation is just the transaction text who sents which amount to whom as print out.
     * @return string representation of SignedTransaction
     */
    @Override
    public String toString() {
        return "%s sent %d VC to %s".formatted(sender, amount, receiver);
    }
}