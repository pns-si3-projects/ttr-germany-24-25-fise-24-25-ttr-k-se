package fr.cotedazur.univ.polytech.teamK.game;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggerColoredFormatter extends Formatter {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    /**
     * A custom log formatter that adds ANSI color codes to log messages
     * based on what we want to be displayed in the console.
     * @param record the log record to be formatted.
     */
    @Override
    public String format(LogRecord record) {
        String color;
        switch (record.getLevel().getName()) {
            case "SEVERE":
                color = RED;
                break;
            case "WARNING":
                color = YELLOW;
                break;
            case "INFO":
                color = PURPLE;
                break;
            case "CONFIG":
                color = CYAN;
                break;
            case "FINE":
                color = BLUE;
                break;
            case "FINER":
                color = GREEN;
                break;
            case "FINEST":
                color = WHITE;
                break;
            default:
                color = RESET;
                break;
        }
        return color + formatMessage(record) + RESET + "\n";
    }
}