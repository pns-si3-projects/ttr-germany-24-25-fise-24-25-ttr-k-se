package fr.cotedazur.univ.polytech.teamK;


import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.game.StatsAnalyse;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import fr.cotedazur.univ.polytech.teamK.game.loggers.GamesStatisticsLogger;
import fr.cotedazur.univ.polytech.teamK.game.loggers.LoggerControlCenter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws WrongPlayerException, CsvValidationException, IOException {
        GameEngine gameEngine = new GameEngine("Reich");
        List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine), new MidBot("RAMMER", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        GamesStatisticsLogger statisticsLogger = new GamesStatisticsLogger(gameEngine);

        for (Bot bot : bots) {
            GameView gameView = new GameView(gameEngine, bot);
            bot.setGameView(gameView);
        }

        LoggerControlCenter loggerControlCenter = new LoggerControlCenter(statisticsLogger, bots.get(0).logger);

        loggerControlCenter.showInfoAndFineAndFiner(); // or loggerControlCenter.showOnlyInfo();

        gameEngine.startGame();
        for (Bot bot : bots) {
            bot.logger.logGameDetails();
        }

        gameEngine.startGame();
        for (Bot bot : bots) {
            bot.logger.logGameDetails();
        }

        gameEngine.startGame();
        for (Bot bot : bots) {
            bot.logger.logGameDetails();
        }
        gameEngine.logGameStatistics();
    }
}
