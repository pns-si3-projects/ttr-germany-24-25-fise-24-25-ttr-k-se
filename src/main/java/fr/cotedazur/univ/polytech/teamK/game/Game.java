package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public MapSimple getGameMap() {
        return gameMap;
    }

    private MapSimple gameMap;
    private ArrayList<Bot> gamePlayers;
    private Deck<DestinationCard> destinationDeck;
    private Deck<WagonCard> wagonDeck;

    public Game(String gameIdentifier)
    {
        if (gameIdentifier.equals("basic"))
        {
            gameMap =new MapSimple("Reich");
            gamePlayers = new ArrayList<>(Arrays.asList(new Bot(0), new Bot(0), new Bot(0)));
            destinationDeck = new Deck<DestinationCard>(TypeOfCards.DESTINATION);
            wagonDeck = new Deck<WagonCard>(TypeOfCards.WAGON);
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
        for (int roundNumber = 0; roundNumber < 10; roundNumber++)
        {
            for (int playerIndex = 0; playerIndex < gamePlayers.size(); playerIndex++)
            {
                gamePlayers.get(playerIndex).playTurn(gameMap, destinationDeck, wagonDeck);
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
