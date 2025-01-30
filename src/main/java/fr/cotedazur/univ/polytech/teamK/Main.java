package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.DumbBot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DumbBot dummy1 = new DumbBot("dummy1"), dummy2 = new DumbBot("dummy2");
        List<Bot> bots = Arrays.asList(dummy1, dummy2);

        GameEngine<? extends Player> currentGame = new GameEngine(bots,"Reich");
        currentGame.startGame();
    }
}
