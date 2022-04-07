package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.core.Blockchain;
import de.cofinpro.blockchain.model.signed.SignedMessage;
import de.cofinpro.blockchain.security.RSASignerAndValidator;

import java.security.KeyPair;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;

/**
 * Runnable implementation, that is performed in the chat clients thread pool.
 * Creates random digitally signed messages in random time intervals both configured in the BlockchainConfig.
 */
public class ChatClientTask extends ClientTask {

    public ChatClientTask(Blockchain blockchain, String name, KeyPair keyPair) {
        super(blockchain, name, keyPair);
    }

    /**
     * the chat client task is to send a random message digitally signed to the blockchain, which in
     * turn provides a unique message id.
     */
    @Override
    protected void performClientTask() {
        int messageId = blockchain.getMessageId();
        String message = String.format("%s [Id %d]: %s", name, messageId,
                CHAT_MESSAGES.get(RANDOM.nextInt(CHAT_MESSAGES.size())));
        SignedMessage signed = new SignedMessage(message, messageId, keyPair.getPublic(),
                RSASignerAndValidator.sign(message, keyPair.getPrivate()));
        blockchain.offerChatMessage(signed);
    }
}
