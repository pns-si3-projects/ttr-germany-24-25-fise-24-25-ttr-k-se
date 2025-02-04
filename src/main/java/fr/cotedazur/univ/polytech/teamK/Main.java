package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.*;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws WrongPlayerException {
        GameEngine gameEngine = new GameEngine("Reich");
        //List<Bot> bots = Arrays.asList(new MidBot("YEETER", gameEngine), new MidBot("WILLY", gameEngine), new MidBot("RAMMER", gameEngine));
        List<Bot> bots = Arrays.asList(new BotOverlap("Bot1", gameEngine), new BotOverlap("Bot2", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
        gameEngine.logGameStatistics();
    }
}
