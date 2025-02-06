package fr.cotedazur.univ.polytech.teamK.game;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class StatsAnalyseTest {
    GameEngine gameEngine;
    GamesStatisticsLogger statisticsLogger;
    LoggerDetailed detailed;
    LoggerControlCenter logger;

    void setUp () throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("stats/gameStats.csv",false));
        gameEngine = new GameEngine("Reich");
        statisticsLogger = new GamesStatisticsLogger(gameEngine);
        detailed = new LoggerDetailed(gameEngine);
        logger = new LoggerControlCenter(statisticsLogger, detailed);
        List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
    }

    @Test
    void testAnalyse () throws CsvValidationException, IOException, WrongPlayerException {
        setUp();
        gameEngine.startGame();
        CSVReader reader = new CSVReader(new FileReader("stats/gameStats.csv"));
        String [] nextLine  = reader.readNext();
        ArrayList<String []> file = new ArrayList<>();
        String winnerName = gameEngine.getHighestScoreAndWinner().getKey().getName();
        while ((nextLine = reader.readNext()) != null) {
            assertEquals(1,parseInt(nextLine[1]));
            if(Objects.equals( "1",nextLine[2])){
                assertEquals(winnerName, nextLine[0]);
                assertEquals("100", nextLine[3]);
            }
            else {
                assertEquals("0",nextLine[2]);
                assertEquals("0", nextLine[3]);
            }
        }
        reader.close();
        setUp();
        gameEngine.startGame();
        CSVReader reader2 = new CSVReader(new FileReader("stats/gameStats.csv"));
        nextLine = reader2.readNext();
        while ((nextLine = reader.readNext()) != null) {
            assertEquals(2,parseInt(nextLine[1]));
            if(winnerName == gameEngine.getHighestScoreAndWinner().getKey().getName()){
                if(Objects.equals( "2",nextLine[2])){
                    assertEquals(winnerName, nextLine[0]);
                    assertEquals("100", nextLine[3]);
                }
                else {
                    assertEquals("0",nextLine[2]);
                    assertEquals("0", nextLine[3]);
                }
            } else {
                assertEquals("1", nextLine[2]);
                assertEquals("50", nextLine[3]);
            }
        }
        reader2.close();
    }
}