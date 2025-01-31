package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Bot {

    private static int COUNT = 1;

    public final GameEngine gameEngine;
    public String name;
    public final int id;

    public Bot(String name, GameEngine gameEngine)
    {
        this.name = name;
        this.id = COUNT++;
        this.gameEngine = gameEngine;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    /**
     * Method who will draw the 4 dest Cards with the number of short dest the player chose
     * @param number_short the number of short dest to draw
     * @return a list of the cards
     */
    public List<DestinationCard> drawDestFromNumber (int number_short) {
        List<DestinationCard> destCardDrawn = new ArrayList<>(4) ;
        DestinationCard toAddCard;
        Deck<DestinationCard> shortDestinationDeck = gameEngine.getShortDestinationDeck();
        Deck<DestinationCard> longDestinationDeck = gameEngine.getLongDestinationDeck();

        for (int i = 0 ; i < 4 ; i++) {
            if (i < number_short) {
                toAddCard = shortDestinationDeck.draw();
                if(toAddCard != null)
                    destCardDrawn.add(toAddCard);
                else
                    destCardDrawn.add(longDestinationDeck.draw());
            } else {
                toAddCard = longDestinationDeck.draw();
                if (toAddCard != null)
                    destCardDrawn.add(longDestinationDeck.draw());
                else
                    destCardDrawn.add(shortDestinationDeck.draw());
            }
        }
        return destCardDrawn;
    }

    /**
     The player give a list of the dest Card he doesn't want
     @param cards the cards he doesn't want
     @return true if he could give back cards
     */
    public boolean giveBackCard (List<DestinationCard> cards) {
        try {
            for (DestinationCard card : cards) {
                    gameEngine.addDestinationCardToDeck(this,card);
            }
        } catch (PaquetPleinException e) {
            System.out.println("you gave too much cards");
            return false;
        }
        return true;
    }

    /**
     * The Bot will choose the number of short dest card to draw and give back the one he doesn't want
     * @exception PaquetVideException if the destination deck is empty
     */
    public abstract void drawDestinationCard() throws PaquetVideException;

    /**
     * The bot will choose the wagon card he want in the deck
     * @exception PaquetVideException if the wagon deck is empty
     */
    public abstract void drawWagonCard() throws PaquetVideException ;

    /**
     * The bot will choose to buy a connection or not
     * @param currentMap the map of the game
     */
    public abstract boolean buyConnection(Board currentMap);

    /**
     * The main fonction who run the bot
     * @param currentMap the map of the game
     * @param shortDestinationDeck the deck of short Destination
     * @param longDestinationDeck the deck of long Destination
     * @param wagonDeck the deck were the bot can pick cards
     * @return true if the bot did something
     */
    public abstract boolean playTurn(Board currentMap, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck) ;
}
