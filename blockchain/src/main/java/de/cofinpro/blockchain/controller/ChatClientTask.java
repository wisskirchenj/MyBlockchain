package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.Blockchain;
import static de.cofinpro.blockchain.config.BlockchainConfig.*;

import java.util.Random;

/**
 * Runnable implementation, that is performed in the chat clients thread pool.
 * Creates random messages in random time intervals both configured in the BlockchainConfig.
 */
public class ChatClientTask implements Runnable {

    private static final Random RANDOM = new Random();

    private final Blockchain blockchain;
    private final String name;

    public ChatClientTask(Blockchain blockchain, String threadName) {
        this.blockchain = blockchain;
        this.name = threadName;
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
                blockchain.sendChatMessage(name + ": " +
                        CHAT_MESSAGES.get(RANDOM.nextInt(CHAT_MESSAGES.size()) + 1));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
