package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;

import java.awt.*;
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

    public void calculatePointForMeeplesForColor () {
        for (Colors meeplesColor : Colors.values()) {
            ArrayList<Bot> firstWinner = new ArrayList<>();
            ArrayList<Bot> secondWinner = new ArrayList<>();
            firstWinner.add(gamePlayers.getFirst());
            for (int i = 1 ; i < gamePlayers.size() ; i++) {
                if(gamePlayers.get(i).getNumberColor(meeplesColor) > firstWinner.getFirst().getNumberColor(meeplesColor)){
                    secondWinner.clear();
                    secondWinner.addAll(firstWinner);
                    firstWinner.clear();
                    firstWinner.add(gamePlayers.get(i));
                } else if (gamePlayers.get(i).getNumberColor(meeplesColor) == firstWinner.getFirst().getNumberColor(meeplesColor)) {
                    firstWinner.add(gamePlayers.get(i));
                } else if (gamePlayers.get(i).getNumberColor(meeplesColor) > secondWinner.getFirst().getNumberColor(meeplesColor)){
                    secondWinner.clear();
                    secondWinner.add(gamePlayers.get(i));
                } else if (gamePlayers.get(i).getNumberColor(meeplesColor) == secondWinner.getFirst().getNumberColor(meeplesColor)) {
                    secondWinner.add(gamePlayers.get(i));
                }
            }
            for (Bot winner : firstWinner) {
                winner.addScore(20);
            }
            if (firstWinner.size() == 1){
                for(Bot winner : secondWinner) {
                    winner.addScore(10);
                }
            }
            if (meeplesColor.ordinal() == 5) {
                break;
            }
        }
    }

    public void runGame()
    {
        for (int roundNumber = 0; roundNumber < 10; roundNumber++)
        {
            for (Bot gamePlayer : gamePlayers) {
                gamePlayer.playTurn(gameMap, destinationDeck, wagonDeck);
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
