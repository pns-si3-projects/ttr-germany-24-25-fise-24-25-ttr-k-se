package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.List;

public class GameView {
    private GameEngine<? extends Player> gameEngine;

    public GameView(GameEngine<? extends Player> gameEngine) {
        this.gameEngine = gameEngine;
    }

    public Board getGameMap() {
        return gameEngine.getGameMap();
    }

    public List<?> getPlayers() {
        return gameEngine.getPlayers();
    }


    public void displayFinalScores() {
        System.out.println("Scores finaux :");
        for (Player player : gameEngine.getPlayers()) {
            System.out.println(player.getName() + " : " + player.getScore() + " points");
        }
    }
}
