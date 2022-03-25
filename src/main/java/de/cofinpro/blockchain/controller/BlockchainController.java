package de.cofinpro.blockchain.controller;

import de.cofinpro.blockchain.model.*;
import de.cofinpro.blockchain.view.PrinterUI;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Application logic class, that contains the run() method started by Main.
 * Presently it creates and validates a blockchain of given length.
 */
public class BlockchainController {

    // not final so they can be mocked...
    private PrinterUI printer = new PrinterUI();

    /**
     * entry point invoked by Main after creation of this controller.
     */
    public void run(int blockchainLength) {
        try {
            Blockchain blockchain = Blockchain.getSerializer().deserialize();
            blockchain.continueGeneration(blockchainLength);
            printer.print(blockchain);
        } catch (NoSuchAlgorithmException e) {
            printer.error("SHA256 algorithm not implemented! ");
        } catch (InvalidBlockchainException e) {
            printer.error("%s%n%s".formatted(e.getMessage(), Arrays.toString(e.getStackTrace())));
        }
    }
}
