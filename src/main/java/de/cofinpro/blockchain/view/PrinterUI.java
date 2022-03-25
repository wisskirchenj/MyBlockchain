package de.cofinpro.blockchain.view;

import lombok.extern.slf4j.Slf4j;

/**
 * wrapper class for logging output - uses SLF4J.
 */
@Slf4j
public class PrinterUI {

    public void print(Object blockchain) {
        log.info(blockchain.toString());
    }

    public void error(String errorMessage) {
        log.error(errorMessage);
    }
}
