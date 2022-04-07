package de.cofinpro.blockchain.model.signed;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * implementing classes provide a data object together with a digital signature, that is applied to the object's
 * toString() representation, and a public ckey for receiver verification.
 */
public interface Signable extends Serializable {

    /**
     * the public key, that the receiver uses for verification of authenticity
     * @return public key
     */
    PublicKey getPublicKey();

    /**
     * getter for digital signature
     * @return digital signature as byte[]
     */
    byte[] getSigned();

    /**
     * provide the object's message, as it is digitally signed by SHA / RSA
     * @return the string representation of the implementing class as it gets signed.
     */
    String toString();
}
