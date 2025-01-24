package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.bot.DumbBot;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public Board getGameMap() {
        return gameMap;
    }

    private Board gameMap;
    private ArrayList<DumbBot> gamePlayers;
    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;

    public Game(String gameIdentifier)
    {
        if (gameIdentifier.equals("basic"))
        {
            gameMap =new Board("Reich");
            gamePlayers = new ArrayList<>(Arrays.asList(new DumbBot("test1", gameMap), new DumbBot("test2", gameMap), new DumbBot("test3", gameMap)));
            shortDestinationDeck = new Deck<DestinationCard>(TypeOfCards.SHORT_DESTINATION, gameMap);
            longDestinationDeck = new Deck<DestinationCard>(TypeOfCards.LONG_DESTINATION, gameMap);
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

    public void calculatePointForMeeplesForColor () {
        int playerValue;
        ArrayList<DumbBot> firstWinner = new ArrayList<>();
        ArrayList<DumbBot> secondWinner = new ArrayList<>();
        for (Colors meeplesColor : Colors.values()) {
            firstWinner.clear();
            secondWinner.clear();
            firstWinner.add(gamePlayers.getFirst());
            secondWinner.add(gamePlayers.getFirst());
            for (int i = 1 ; i < gamePlayers.size() ; i++) {
                playerValue = gamePlayers.get(i).getMeeples().getNumberOfAColor(meeplesColor);
                if( playerValue> firstWinner.getFirst().getMeeples().getNumberOfAColor(meeplesColor)){
                    secondWinner.clear();
                    secondWinner.addAll(firstWinner);
                    firstWinner.clear();
                    firstWinner.add(gamePlayers.get(i));
                } else if (playerValue == firstWinner.getFirst().getMeeples().getNumberOfAColor(meeplesColor)) {
                    firstWinner.add(gamePlayers.get(i));
                } else if (playerValue > secondWinner.getFirst().getMeeples().getNumberOfAColor(meeplesColor)){
                    secondWinner.clear();
                    secondWinner.add(gamePlayers.get(i));
                } else if (playerValue == secondWinner.getFirst().getMeeples().getNumberOfAColor(meeplesColor)) {
                    secondWinner.add(gamePlayers.get(i));
                }
            }
            for (DumbBot winner : firstWinner) {
                winner.addScore(20);
            }
            if (firstWinner.size() == 1){
                for(DumbBot winner : secondWinner) {
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
        boolean run = true;
        while(run){
            for (DumbBot gamePlayer : gamePlayers) {
                gamePlayer.playTurn(gameMap, shortDestinationDeck,longDestinationDeck, wagonDeck);
            }
            for (DumbBot gamePlayer : gamePlayers) { //si un joueur à 0,1 ou 2 wagons restant, il y a un dernier tour avant que la partie se finisse
                if(gamePlayer.getWagonsRemaining() < 3){
                    for (DumbBot gamePlayerLastRound : gamePlayers) {
                        gamePlayerLastRound.playTurn(gameMap, shortDestinationDeck,longDestinationDeck, wagonDeck);
                    }
                    run = false;
                }
            }
        }
    }
    public void printPlayerStatus()
    {
        System.out.println("Etat des joueurs à la fin de partie :\n");

        for (DumbBot gamePlayer : gamePlayers) {
            System.out.println(gamePlayer);
        }
    }
}
