package fr.cotedazur.univ.polytech.teamK.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class LoggerControlCenterTest {

    @Mock
    private GamesStatisticsLogger statisticsLogger;

    @Mock
    private LoggerDetailed detailedLogger;

    private LoggerControlCenter loggerControlCenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loggerControlCenter = new LoggerControlCenter(statisticsLogger, detailedLogger);
    }

    @Test
    void testShowOnlyInfo() {
        Logger statsLogger = Logger.getLogger(GamesStatisticsLogger.class.getName());
        Logger detailLogger = Logger.getLogger(LoggerDetailed.class.getName());
        Handler handler1 = mock(Handler.class);
        Handler handler2 = mock(Handler.class);
        statsLogger.addHandler(handler1);
        detailLogger.addHandler(handler2);

        loggerControlCenter.showOnlyInfo();

        assert statsLogger.getLevel().equals(Level.INFO);
        assert detailLogger.getLevel().equals(Level.INFO);
        verify(handler1).setLevel(Level.INFO);
        verify(handler2).setLevel(Level.INFO);
    }

    @Test
    void testShowInfoAndFineToFinest() {
        Logger statsLogger = Logger.getLogger(GamesStatisticsLogger.class.getName());
        Logger detailLogger = Logger.getLogger(LoggerDetailed.class.getName());
        Handler handler1 = mock(Handler.class);
        Handler handler2 = mock(Handler.class);
        statsLogger.addHandler(handler1);
        detailLogger.addHandler(handler2);

        loggerControlCenter.showInfoAndFineToFinest();

        assert statsLogger.getLevel().equals(Level.FINEST);
        assert detailLogger.getLevel().equals(Level.FINEST);
        verify(handler1).setLevel(Level.FINEST);
        verify(handler2).setLevel(Level.FINEST);
    }
}
