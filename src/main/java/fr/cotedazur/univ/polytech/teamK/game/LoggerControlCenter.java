package fr.cotedazur.univ.polytech.teamK.game;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerControlCenter {
    private GamesStatisticsLogger statisticsLogger;
    private LoggerDetailed detailedLogger;

    /**
     * Constructs a LoggerControlCenter with the specified loggers.
     *
     * @param statisticsLogger the GamesStatisticsLogger instance
     * @param detailedLogger the DetailedLogger instance
     */
    public LoggerControlCenter(GamesStatisticsLogger statisticsLogger, LoggerDetailed detailedLogger) {
        this.statisticsLogger = statisticsLogger;
        this.detailedLogger = detailedLogger;
    }

    /**
     * Sets the logger level to show only INFO messages.
     */
    public void showOnlyInfo() {
        setLoggerLevel(Level.INFO);
    }

    /**
     * Sets the logger level to show INFO, FINE, and FINER messages.
     */
    public void showInfoAndFineToFinest() {
        setLoggerLevel(Level.FINEST);
    }

    /**
     * Sets the logger level for both loggers.
     *
     * @param level the logging level to be set
     */
    private void setLoggerLevel(Level level) {
        Logger[] loggers = {Logger.getLogger(GamesStatisticsLogger.class.getName()), Logger.getLogger(LoggerDetailed.class.getName())};
        for (Logger logger : loggers) {
            logger.setLevel(level);
            for (Handler handler : logger.getHandlers()) {
                handler.setLevel(level);
            }
        }
    }
}
