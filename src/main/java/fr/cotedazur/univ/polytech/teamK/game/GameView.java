package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.HashMap;
import java.util.List;

public class GameView {
    private GameEngine<? extends Bot> gameEngine;

    public GameView(GameEngine<? extends Bot> gameEngine) {
        this.gameEngine = gameEngine;
    }

    public Board getGameMap() {
        return gameEngine.getGameMap();
    }

    public HashMap<Integer, Player> getPlayers() {
        return gameEngine.getPlayers();
    }


    public void displayFinalScores() {
        System.out.println("Scores finaux :");
        gameEngine.getPlayers().values().forEach(player -> System.out.println(player.getName() + " : " + player.getScore() + " points"));
    }

    /**
     * savoir le score des autres
     * savoir le nombre de carte
     */
}
