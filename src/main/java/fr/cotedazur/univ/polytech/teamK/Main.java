package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.BotMedDest;
import fr.cotedazur.univ.polytech.teamK.bot.DumbBot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Board gameMap = new Board("Reich");
        GameEngine gameEngine = new GameEngine("Reich");
        List<Bot> bots = Arrays.asList(new BotMedDest("bot1", gameEngine), new MidBot("bot2", gameEngine), new MidBot("bot3", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
    }
}
