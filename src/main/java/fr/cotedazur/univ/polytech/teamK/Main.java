package fr.cotedazur.univ.polytech.teamK;

import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.BotOverlap;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws WrongPlayerException, CsvValidationException, IOException {
        GameEngine gameEngine = new GameEngine("Reich");
        //List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine), new MidBot("RAMMER", gameEngine));
        List<Bot> bots = Arrays.asList(new BotOverlap("Bot1", gameEngine), new BotOverlap("Bot2", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
        gameEngine.startGame();
        gameEngine.startGame();
        gameEngine.logGameStatistics();
    }
}
