package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.map.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Game;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Game currentGame = new Game("basic");
        currentGame.runGame();
        List<Connection> mapConnections = currentGame.getGameMap().getConnectionsInMap();
        for (int checkingConnections = 0; checkingConnections < mapConnections.size(); checkingConnections++)
        {
            if (mapConnections.get(checkingConnections).getWidth() != mapConnections.get(checkingConnections).getFreeRails().size())
            {
                String yo = "yo";
            }
        }
        currentGame.calculatePointForMeeplesForColor();
        currentGame.printPlayerStatus();
    }
}
