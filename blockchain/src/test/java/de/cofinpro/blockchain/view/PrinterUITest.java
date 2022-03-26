package de.cofinpro.blockchain.view;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PrinterUITest {

    PrinterUI printerUI;

    @BeforeEach
    void setup() {
        printerUI = new PrinterUI();
    }

    @Test
    void log4jActiveAndErrorTest() {
        Object loggerPath = org.apache.logging.log4j.Logger.class
                .getResource("/org/apache/logging/log4j/Logger.class");
        assertNotNull(loggerPath);
        printerUI.error(loggerPath.toString());
        Object appenderPath = org.apache.logging.log4j.Logger.class
                .getResource("/org/apache/logging/log4j/core/Appender.class");
        assertNotNull(appenderPath);
        printerUI.error(appenderPath.toString());
    }

    @Test
    void log4j2xmlInCPAndPrintTest() {
        Object resourcePath = org.apache.logging.log4j.Logger.class.getResource("/log4j2.xml");
        assertNotNull(resourcePath);
        printerUI.print(resourcePath);
    }


    @Test
    void logLevelSetToInfoTest() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Level rootLogLevel = ctx.getConfiguration().getLoggerConfig(LogManager.ROOT_LOGGER_NAME).getLevel();
        printerUI.print("Root - Loglevel Slf4J is : " + rootLogLevel);
        assertEquals("INFO", rootLogLevel.toString());
    }
}
