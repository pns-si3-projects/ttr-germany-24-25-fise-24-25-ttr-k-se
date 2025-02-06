package fr.cotedazur.univ.polytech.teamK;

import static org.mockito.Mockito.*;

import fr.cotedazur.univ.polytech.teamK.game.*;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private Main main;
    private GameEngine mockGameEngine;
    private GamesStatisticsLogger mockStatisticsLogger;
    private LoggerDetailed mockDetailed;
    private LoggerControlCenter mockLogger;

    @BeforeEach
    public void setUp() {
        mockGameEngine = mock(GameEngine.class);
        mockStatisticsLogger = mock(GamesStatisticsLogger.class);
        mockDetailed = mock(LoggerDetailed.class);
        mockLogger = mock(LoggerControlCenter.class);

        main = new Main();
        main.gameEngine = mockGameEngine;
        main.statisticsLogger = mockStatisticsLogger;
        main.detailed = mockDetailed;
        main.logger = mockLogger;
    }

    @Test
    public void testRun2Thousands() throws CsvValidationException, IOException, WrongPlayerException, WrongPlayerException {
        main.thousands = true;
        main.run();
        verify(main, times(2)).runThousand(mockGameEngine);
        verify(mockStatisticsLogger).logGameStatistics();
    }

    @Test
    public void testRunDemo() throws CsvValidationException, IOException, WrongPlayerException {
        main.demo = true;
        main.run();
        verify(mockGameEngine).startGame();
        verify(mockLogger).showInfoAndFineAndFiner();
    }

    @Test
    public void testInitialisation() {
        main.initialise();
        assertNotNull(main.gameEngine);
        assertNotNull(main.statisticsLogger);
        assertNotNull(main.detailed);
        assertNotNull(main.logger);
    }

    @Test
    public void testRunThousand() throws CsvValidationException, IOException, WrongPlayerException {
        main.runThousand(mockGameEngine);
        verify(mockGameEngine, times(1000)).startGame();
        verify(mockStatisticsLogger).logGameStatistics();
    }
}
