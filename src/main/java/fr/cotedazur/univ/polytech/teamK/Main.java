package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Game;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Game currentGame = new Game("basic");
        currentGame.runGame();
        /*
        List<Connections> mapConnections = currentGame.getGameMap().getConnectionsInMap();
        for (int checkingConnections = 0; checkingConnections < mapConnections.size(); checkingConnections++)
        {
            if (mapConnections.get(checkingConnections).getWidth() != mapConnections.get(checkingConnections).getFreeRails().size())
            {
                String yo = "yo";
            }
        }
        */
        currentGame.printPlayerStatus();
    }
}
