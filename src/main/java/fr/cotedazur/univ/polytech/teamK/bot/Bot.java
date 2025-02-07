package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import fr.cotedazur.univ.polytech.teamK.game.LoggerDetailed;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public abstract class Bot{

    private static int COUNT = 1;
    public GameView gameView;
    public final GameEngine gameEngine;
    public String name;
    public final int id;
    public LoggerDetailed logger;

    protected Bot(String name, GameEngine gameEngine)
    {
        this.name = name;
        this.id = COUNT++;
        this.gameEngine = gameEngine;
    }

    public GameEngine getGameEngine()
    {
        return gameEngine;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setGameView(GameView gameView){
        this.gameView = gameView;
        this.logger = new LoggerDetailed(gameEngine);
    }
    /**
     * Method who will draw the 4 dest Cards with the number of short dest the player chose
     * @param number_short the number of short dest to draw
     * @return a list of the cards
     */
    public List<DestinationCard> drawDestFromNumber (int number_short) throws WrongPlayerException {
        List<DestinationCard> destCardDrawn = new ArrayList<>(4) ;
        DestinationCard toAddCard;

        for (int i = 0; i < 4; i++)
        {
            if (i < number_short)
            {
                try
                {
                    toAddCard = gameEngine.drawShortDestination(this);
                    destCardDrawn.add(toAddCard);
                }
                catch (DeckEmptyException e)
                {
                    try
                    {
                        toAddCard = gameEngine.drawLongueDestination(this);
                        destCardDrawn.add(toAddCard);
                    }
                    catch (DeckEmptyException e1)
                    {
                        return destCardDrawn;
                    }
                }
            }
            else
            {
                try
                {
                    toAddCard = gameEngine.drawLongueDestination(this);
                    destCardDrawn.add(toAddCard);
                }
                catch (DeckEmptyException e)
                {
                    try
                    {
                        toAddCard = gameEngine.drawShortDestination(this);
                        destCardDrawn.add(toAddCard);
                    }
                    catch (DeckEmptyException e1)
                    {
                        return destCardDrawn;
                    }
                }
            }
        }

        /*
        try {
            for (int i = 0; i < 4; i++) {
                if (i < number_short) {
                    toAddCard = gameEngine.drawShortDestination(this);
                    if (toAddCard != null)
                        destCardDrawn.add(toAddCard);
                    else
                        destCardDrawn.add(gameEngine.drawLongueDestination(this));
                } else {
                    toAddCard = gameEngine.drawLongueDestination(this);
                    if (toAddCard != null)
                        destCardDrawn.add(toAddCard);
                    else
                        destCardDrawn.add(gameEngine.drawShortDestination(this));
                }
            }
        }
        catch (DeckEmptyException e)
        {
            return destCardDrawn;
        }*/
        return destCardDrawn;
    }


    public boolean giveBackCard(DestinationCard card)
    {
        try {gameEngine.addDestinationCardToDeck(this, card);}
        catch (DeckFullException e)
        {
            return false;
        }
        return true;
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
        } catch (DeckFullException e) {
            return false;
        }
        return true;
    }

    /**
     * The Bot will choose the number of short dest card to draw and give back the one he doesn't want
     * @return true if the draw succeed
     * @throws DeckEmptyException if the destination deck is empty
     */
    public abstract boolean drawDestinationCard() throws DeckEmptyException, WrongPlayerException;

    public void displayDrawDestinationCardAction(){
        logger.logDrawDestinationCard(this);
    }
    /**
     * The bot will choose the wagon card he want in the deck
     * @return true if the draw succeed
     * @throws DeckEmptyException if the wagon deck is empty
     */
    public abstract boolean drawWagonCard(Colors toFocus) throws DeckEmptyException, WrongPlayerException ;

    public void displayDrawWagonCardAction(){
        logger.logDrawWagonCard(this);
    }

    /**
     * The bot will choose to buy a connection or not
     */
    public abstract boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException;

    public void displayBuyConnectionAction(){
        logger.logBuyConnection(this);
    }

    /**
     * The main fonction who run the bot
     * @return true if the bot did something
     */
    public abstract boolean playTurn() throws WrongPlayerException ;

    public void displayPlayTurn(){
        logger.logPlayTurn(this);
    }

}
