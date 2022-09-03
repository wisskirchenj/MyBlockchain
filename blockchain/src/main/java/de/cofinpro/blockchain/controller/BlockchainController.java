package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.core.*;
import de.cofinpro.blockchain.model.signed.DataBlockFactory;
import de.cofinpro.blockchain.model.signed.SignedDataBlock;
import de.cofinpro.blockchain.security.RSAGenerator;
import de.cofinpro.blockchain.view.PrinterUI;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static de.cofinpro.blockchain.config.BlockchainConfig.*;
import static de.cofinpro.blockchain.config.BlockchainConfig.BlockchainMode.CHAT;

/**
 * Application logic class, that contains the run() method started by Main.
 * It deserializes (if file exists) a previously created blockchain and uses a
 * thread pool of miners to continue its generation.
 * Asynchronously a further thread pool of chat clients is started, that continuously generate
 * new chat messages, which they send to the blockchain to store as block data.
 */
@Slf4j
public class BlockchainController {

    private final PrinterUI printer = new PrinterUI();
    private Blockchain blockchain;
    private ExecutorService clients;

    /**
     * entry point invoked by Main after creation of this controller.
     */
    public void run() {
        clients = Executors.newFixedThreadPool(CLIENT_COUNT);
        try {
            blockchain = Blockchain.getSerializer().deserialize();
            blockchain.resetIfInvalid();
            startClients(clients);
            continueGeneration(blockchain.size());
            printer.print(blockchain);
        } catch (InvalidBlockchainException exception) {
            errorExit("Invalid blockchain detected: ", exception);
        }
        clients.shutdownNow();
    }

    /**
     * start all chat clients before the blockchain generation starts. They will produce and digitally sign
     * chat messages during all subsequent program run. The chat clients thread pool is stopped at the end
     * of the run() method, that calls this method.
     * @param clients the thread pool where the ChatClientTask's are submitted to
     */
    private void startClients(ExecutorService clients) {
        List <KeyPair> keys = generateKeyPairs();
        IntStream.range(0, CLIENT_COUNT)
                .forEach(i -> clients.submit(BLOCKCHAIN_MODE == CHAT
                        ? new ChatClientTask(blockchain, CLIENTS.get(i), keys.get(i))
                        : new TransactionClientTask(blockchain, CLIENTS.get(i), keys.get(i))
                        ));
    }

    /**
     * generate the RSA key pairs for all chat users.
     * @return list of key pairs
     */
    private List<KeyPair> generateKeyPairs() {
        List <KeyPair> keyList = new ArrayList<>(CLIENT_COUNT);
        try {
            RSAGenerator keyGenerator = new RSAGenerator(RSA_KEY_LENGTH);
            for (int i = 0; i < CLIENT_COUNT; i++) {
                keyGenerator.createKeys(KEY_PAIRS_PATH_PREFIX + CLIENTS.get(i) + PUBLIC_KEY_SUFFIX,
                        KEY_PAIRS_PATH_PREFIX + CLIENTS.get(i) + PRIVATE_KEY_SUFFIX);
                keyList.add(keyGenerator.getKeyPair());
                log.info("Private/Public keypair generated for client " + CLIENTS.get(i));
            }
        } catch (NoSuchAlgorithmException | IOException exception) {
            errorExit("Error RSA-encoding key pair:", exception);
        }
        return keyList;
    }

    /**
     * this method starts all the computational block creation work. This is done asynchronously by use of a
     * miner thread pool. The thread pool call invokeAny lets all miners run with the same competing task of
     * generating the next block and the fastest wins:-)
     * The block generation time is balanced by the blockchain in adapting the requested leading hash zeros,
     * which determine the computational complexity.
     * This parameter is requested by the chain in the loop and provided to the Miner's tasks.
     * @param createdBlocks size of the blockchain at invocation time (> 0 iff deserialized blockchain loaded)
     */
    private void continueGeneration(int createdBlocks) {
        int leadingHashZeros = 0;
        ExecutorService miners = Executors.newFixedThreadPool(MINER_COUNT);
        while (createdBlocks < BLOCKCHAIN_LENGTH) {
            try {
                if (!blockchain.addDataBlock(miners.invokeAny(getMineTasks(++createdBlocks, leadingHashZeros)))) {
                    throw new InvalidBlockchainException("Invalid block received by miner !");
                }
            } catch (Exception exception) {
                miners.shutdownNow();
                errorExit("Exception while blockchain creation: ", exception);
                Thread.currentThread().interrupt(); // to soothe Sonar...
            }
            leadingHashZeros = blockchain.adaptLeadingHashZeros();
        }
        miners.shutdownNow();
    }

    /**
     * creates a MineTask list (Callable<Block>) with as many copies as threads in the pool.
     * All miners get the same task, that has the computational difficulty as leadingHashZeros
     * as parameter. In addition, the mine task includes all chat messages available at this time
     * which are requested by the blockchain. They are stored as block data.
     * @param id id of the block to generate
     * @param leadingHashZeros requested leading zeros for the hash of new block
     * @return List of mine tasks for creating a new block
     */
    private List<Callable<SignedDataBlock>> getMineTasks(int id, int leadingHashZeros) {
        String previousHash = blockchain.isEmpty() ? "0" : blockchain.getLast().getHash();
        MineTask mineTask = new MineTask(new DataBlockFactory<>(leadingHashZeros, blockchain.pollData()),
                id, previousHash);
        return Collections.nCopies(MINER_COUNT, mineTask);
    }

    /**
     * shut down chat, print error message and stack trace and leave.
     * @param message error message
     * @param exception exception that brought us here
     */
    private void errorExit(String message, Exception exception) {
        clients.shutdownNow();
        printer.error(message);
        exception.printStackTrace();
        System.exit(1);
    }
}
