package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.*;
import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public MapHash getGameMap() {
        return gameMap;
    }

    private MapHash gameMap;
    private ArrayList<Bot> gamePlayers;
    private Deck<DestinationCard> destinationDeck;
    private Deck<WagonCard> wagonDeck;

    public Game(String gameIdentifier)
    {
        if (gameIdentifier.equals("basic"))
        {
            gameMap =new MapHash("Reich");
            gamePlayers = new ArrayList<>(Arrays.asList(new Bot(0, gameMap)));
            destinationDeck = new Deck<DestinationCard>(TypeOfCards.DESTINATION, gameMap);
            wagonDeck = new Deck<WagonCard>(TypeOfCards.WAGON, gameMap);
        }
    }

    /*
    STEP ONE: BUILD THE BOARD
     */

    /*
    STEP TWO: INITIALISE PLAYERS
     */

    /*
    STEP THREE: CARDS + PLAYABLE OBJECTS
    initialise the decks of cards (wagon + destination)
    initialise Meeples when done
     */

    /*
    STEP 4: ROUNDS
    run rounds until the end condition is tested or a certain number of rounds
    on each round: each 'player' acts on their turn
     */

    public void runGame()
    {
        for (int roundNumber = 0; roundNumber < 40; roundNumber++)
        {
            for (int playerIndex = 0; playerIndex < gamePlayers.size(); playerIndex++)
            {
                gamePlayers.get(playerIndex).playTurn(gameMap, destinationDeck, wagonDeck);
                //this.printPlayerStatus();
            }
        }
    }
    public void printPlayerStatus()
    {
        System.out.println("Etat des joueurs à la fin de partie :\n");

        for (int playerIndex = 0; playerIndex < gamePlayers.size(); playerIndex++)
        {
            System.out.println(gamePlayers.get(playerIndex).toString());
        }
    }
}
