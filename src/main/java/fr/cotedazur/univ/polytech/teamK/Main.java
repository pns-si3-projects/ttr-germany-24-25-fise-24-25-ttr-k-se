package fr.cotedazur.univ.polytech.teamK;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;

import fr.cotedazur.univ.polytech.teamK.game.*;
import fr.cotedazur.univ.polytech.teamK.game.GamesStatisticsLogger;
import fr.cotedazur.univ.polytech.teamK.game.LoggerControlCenter;
import fr.cotedazur.univ.polytech.teamK.game.LoggerDetailed;

import fr.cotedazur.univ.polytech.teamK.bot.overlap.BotOverlap;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;




public class Main {
    @Parameter(names = "--2thousands", description = "Lancer 1000 parties d'un bot1 contre un bot2, et 1000 parties d'un bot1 contre un bot1")
    boolean thousands;

    @Parameter(names = "--demo", description = "Partie classique avec tous les logs pour la soutenance")
    boolean demo;

    GameEngine gameEngine;
    GamesStatisticsLogger statisticsLogger;
    LoggerDetailed detailed;
    LoggerControlCenter logger;

    public static void main(String[] args) throws WrongPlayerException, CsvValidationException, IOException {

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws WrongPlayerException, CsvValidationException, IOException {
        if(thousands){
            run2Thousands();
        }
        if(demo){
            runDemo();
        }
    }

    public void run2Thousands() throws WrongPlayerException, CsvValidationException, IOException {
        initialise();
        runThousand(gameEngine);
        initialise();
        runThousand(gameEngine);
    }

    void runThousand(GameEngine gameEngine) throws WrongPlayerException, CsvValidationException, IOException {
        int compteur = 0;
        logger.showOnlyInfo();
        while(compteur < 1000){
            List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine),new MidBot("WILLER", gameEngine));
            gameEngine.addBotsToPlayerMap(bots);
            gameEngine.startGame();
            compteur++;
        }
        while(compteur < 2000){
            List<Bot> bots = Arrays.asList(new BotOverlap("Bot1", gameEngine), new BotOverlap("Bot2", gameEngine));
            gameEngine.addBotsToPlayerMap(bots);
            gameEngine.startGame();
            compteur++;
        }

        statisticsLogger.logGameStatistics();
    }

    public void runDemo() throws WrongPlayerException, CsvValidationException, IOException {
        initialise();
        logger.showInfoAndFineToFinest();
        //List<Bot> bots = Arrays.asList(new MidBot("STEVE", gameEngine), new MidBot("WILLER", gameEngine));
        List<Bot> bots = Arrays.asList(new BotOverlap("Bot1", gameEngine), new BotOverlap("Bot2", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
    }

    void initialise() {
        gameEngine = new GameEngine("Reich");
        statisticsLogger = new GamesStatisticsLogger(gameEngine);
        detailed = new LoggerDetailed(gameEngine);
        logger = new LoggerControlCenter(statisticsLogger, detailed);
        }
}
