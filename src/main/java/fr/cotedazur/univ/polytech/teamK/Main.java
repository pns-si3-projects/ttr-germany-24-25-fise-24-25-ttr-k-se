package fr.cotedazur.univ.polytech.teamK;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.BotMedDest;
import fr.cotedazur.univ.polytech.teamK.bot.DumbBot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Main {
    @Parameter(names = "--2thousands", description = "Lancer 1000 parties d'un bot1 contre un bot2, et 1000 parties d'un bot1 contre un bot1")
    boolean thousands;

    @Parameter(names = "--demo", description = "Partie classique avec tous les logs pour la soutenance")
    boolean demo;

    public static void main(String[] args) throws WrongPlayerException {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws WrongPlayerException {
        if(thousands){
            runThousands();
        }
        if(demo){
            runDemo();
        }
    }

    public void runThousands() throws WrongPlayerException {
        GameEngine gameEngine = new GameEngine("Reich");
        int compteur = 0;
        while(compteur < 1000){
            List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine),new MidBot("WILLER", gameEngine));
            gameEngine.addBotsToPlayerMap(bots);
            gameEngine.startGame();
            compteur++;
        }
        gameEngine.logGameStatistics();

    }

    public void runDemo() throws WrongPlayerException {
        GameEngine gameEngine = new GameEngine("Reich");
        List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
    }
}
