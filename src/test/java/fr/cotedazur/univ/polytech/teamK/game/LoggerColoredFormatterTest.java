package fr.cotedazur.univ.polytech.teamK.game;

import org.junit.jupiter.api.Test;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.*;

class LoggerColoredFormatterTest {

    private final LoggerColoredFormatter formatter = new LoggerColoredFormatter();

    @Test
    void testSevereLogFormatting() {
        LogRecord record = new LogRecord(Level.SEVERE, "Critical error!");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.RED), "SEVERE log should be red");
        assertTrue(formatted.contains("Critical error!"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testWarningLogFormatting() {
        LogRecord record = new LogRecord(Level.WARNING, "Potential issue detected");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.YELLOW), "WARNING log should be yellow");
        assertTrue(formatted.contains("Potential issue detected"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testInfoLogFormatting() {
        LogRecord record = new LogRecord(Level.INFO, "System is running");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.PURPLE), "INFO log should be purple");
        assertTrue(formatted.contains("System is running"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testConfigLogFormatting() {
        LogRecord record = new LogRecord(Level.CONFIG, "Config loaded");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.CYAN), "CONFIG log should be cyan");
        assertTrue(formatted.contains("Config loaded"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testFineLogFormatting() {
        LogRecord record = new LogRecord(Level.FINE, "Detailed debug info");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.BLUE), "FINE log should be blue");
        assertTrue(formatted.contains("Detailed debug info"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testFinerLogFormatting() {
        LogRecord record = new LogRecord(Level.FINER, "More detailed debug info");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.GREEN), "FINER log should be green");
        assertTrue(formatted.contains("More detailed debug info"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testFinestLogFormatting() {
        LogRecord record = new LogRecord(Level.FINEST, "Most detailed debug info");
        String formatted = formatter.format(record);
        assertTrue(formatted.contains(LoggerColoredFormatter.WHITE), "FINEST log should be white");
        assertTrue(formatted.contains("Most detailed debug info"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }

    @Test
    void testUnknownLogLevelDefaultsToReset() {
        LogRecord record = new LogRecord(Level.ALL, "Unknown log level");
        String formatted = formatter.format(record);
        assertFalse(formatted.contains(LoggerColoredFormatter.RED));
        assertFalse(formatted.contains(LoggerColoredFormatter.YELLOW));
        assertFalse(formatted.contains(LoggerColoredFormatter.PURPLE));
        assertFalse(formatted.contains(LoggerColoredFormatter.CYAN));
        assertFalse(formatted.contains(LoggerColoredFormatter.BLUE));
        assertFalse(formatted.contains(LoggerColoredFormatter.GREEN));
        assertFalse(formatted.contains(LoggerColoredFormatter.WHITE));
        assertTrue(formatted.contains("Unknown log level"));
        assertTrue(formatted.endsWith(LoggerColoredFormatter.RESET + "\n"));
    }
}
