package de.cofinpro.blockchain.view;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * simple UI-class, that cares about prompting the user for input and returning it to application controllers.
 * not used in stages 3 and 4
 */
@Slf4j
public class ScannerUI {

    private final Scanner stdinScanner = new Scanner(System.in);

    public int promptForZeros() {
        log.info("Enter how many zeros the hash must start with: ");
        return stdinScanner.nextInt();
    }
}
