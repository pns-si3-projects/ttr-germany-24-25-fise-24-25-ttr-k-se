package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.game.Game;

public class Main {

    public static void main(String[] args) {
        Game currentGame = new Game("basic");
        currentGame.runGame();

        currentGame.calculatePointForMeeplesForColor();
        currentGame.printPlayerStatus();
    }
}
