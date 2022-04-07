package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.core.Blockchain;

import java.security.KeyPair;
import java.util.Random;

import static de.cofinpro.blockchain.config.BlockchainConfig.MAX_CLIENT_PAUSE_MILLISECONDS;

/**
 * Abstract base class for ClientTasks implementing runnable. The runnable is performed in the chat clients thread pool.
 * Creates random digitally signed objects in random time intervals configurable in the BlockchainConfig.
 * Calls the concrete client via its abstract method "performClientTask()".
 */
public abstract class ClientTask implements Runnable {

    protected static final Random RANDOM = new Random();

    protected final Blockchain blockchain;
    protected final String name;
    protected final KeyPair keyPair;

    ClientTask(Blockchain blockchain, String name, KeyPair keyPair) {
        this.blockchain = blockchain;
        this.name = name;
        this.keyPair = keyPair;
    }

    /**
     * Loop to create and sign random client objects in random time intervals both configured via BlockchainConfig.
     * Simultaneously listening to interrupt - probably superfluous since thread mostly sleeps.
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(RANDOM.nextInt(MAX_CLIENT_PAUSE_MILLISECONDS));
                performClientTask();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract void performClientTask();
}
