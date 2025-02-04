package fr.cotedazur.univ.polytech.teamK;

import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.game.StatsAnalyse;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.game.StatsAnalyse;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.io.IOException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws WrongPlayerException, CsvValidationException, IOException {
        GameEngine gameEngine = new GameEngine("Reich");
        List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLER", gameEngine), new MidBot("RAMMER", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
        gameEngine.startGame();
        gameEngine.startGame();
        gameEngine.logGameStatistics();
    }
}
