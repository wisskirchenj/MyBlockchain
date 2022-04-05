package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.Blockchain;
import de.cofinpro.blockchain.model.SignedMessage;
import de.cofinpro.blockchain.security.RSASignerAndValidator;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;

import java.security.KeyPair;
import java.util.Random;

/**
 * Runnable implementation, that is performed in the chat clients thread pool.
 * Creates random messages in random time intervals both configured in the BlockchainConfig.
 */
public class ChatClientTask implements Runnable {

    private static final Random RANDOM = new Random();

    private final Blockchain blockchain;
    private final String name;
    private final KeyPair keyPair;

    public ChatClientTask(Blockchain blockchain, String threadName, KeyPair keyPair) {
        this.blockchain = blockchain;
        this.name = threadName;
        this.keyPair = keyPair;
    }

    /**
     * Loop to create random messages in random time intervals both configured via BlockchainConfig.
     * Simultaneously listening to interrupt - probably superfluous since thread mostly sleeps.
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(RANDOM.nextInt(MAX_CHAT_PAUSE_MILLISECONDS));
                int messageId = blockchain.getMessageId();
                String message = String.format("%s [Id %d]: %s", name, messageId,
                        CHAT_MESSAGES.get(RANDOM.nextInt(CHAT_MESSAGES.size()) + 1));
                SignedMessage signed = new SignedMessage(message, messageId, keyPair.getPublic(),
                        RSASignerAndValidator.sign(message, keyPair.getPrivate()));
                blockchain.sendChatMessage(signed);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
