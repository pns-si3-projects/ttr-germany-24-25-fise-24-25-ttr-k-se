package fr.cotedazur.univ.polytech.teamK;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;

import fr.cotedazur.univ.polytech.teamK.game.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;




public class Main {
    @Parameter(names = "--2thousands", description = "Lancer 1000 parties d'un bot1 contre un bot2, et 1000 parties d'un bot1 contre un bot1")
    boolean thousands;

    @Parameter(names = "--demo", description = "Partie classique avec tous les logs pour la soutenance")
    boolean demo;

    public static void main(String[] args) throws WrongPlayerException, CsvValidationException, IOException {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws WrongPlayerException, CsvValidationException, IOException {
        if (thousands) {
            runThousands();
        }
        if (demo) {
            runDemo();
        }
    }

    public void runThousands() throws WrongPlayerException, CsvValidationException, IOException {
        GameEngine gameEngine = new GameEngine("Reich");
        GamesStatisticsLogger statisticsLogger = new GamesStatisticsLogger(gameEngine);
        LoggerControlCenter loggerControlCenter = new LoggerControlCenter(statisticsLogger, null);
        loggerControlCenter.showOnlyInfo();

        int compteur = 0;
        while (compteur < 1000) {
            List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine));
            gameEngine.addBotsToPlayerMap(bots);
            gameEngine.startGame();
            compteur++;
        }
        gameEngine.logGameStatistics();
        compteur = 0;
        while (compteur < 1000) {
            List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine));
            gameEngine.addBotsToPlayerMap(bots);
            gameEngine.startGame();
            compteur++;
        }
        gameEngine.logGameStatistics();
    }

    public void runDemo() throws WrongPlayerException, CsvValidationException, IOException {
        GameEngine gameEngine = new GameEngine("Reich");
        List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);

        // Initialize DetailedLogger for each bot
        for (Bot bot : bots) {
            GameView gameView = new GameView(gameEngine, bot);
            bot.setGameView(gameView);
        }

        DetailedLogger detailedLogger = bots.get(0).logger; // Assuming all bots use the same logger configuration
        LoggerControlCenter loggerControlCenter = new LoggerControlCenter(null, detailedLogger);
        loggerControlCenter.showInfoAndFineAndFiner();

        gameEngine.startGame();
        for (Bot bot : bots) {
            bot.logger.logGameDetails();
        }
    }
}
