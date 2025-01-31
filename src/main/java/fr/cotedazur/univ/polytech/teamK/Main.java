package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.BotMedDest;
import fr.cotedazur.univ.polytech.teamK.bot.DumbBot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws WrongPlayerException {
        Board gameMap = new Board("Reich");
        GameEngine<DumbBot> gameEngine = new GameEngine("Reich");
        List<DumbBot> bots = Arrays.asList(new DumbBot("bot1", gameEngine), new DumbBot("bot2", gameEngine), new DumbBot("bot3", gameEngine));
        gameEngine.addBotsToPlayerMap(bots);
        gameEngine.startGame();
    }
}
