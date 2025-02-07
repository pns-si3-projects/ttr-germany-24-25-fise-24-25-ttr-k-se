package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DeckEmptyException;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;


import java.util.*;

/**
 * The BotOverlap class represents a bot player in the game.
 * This bot implements decision-making logic for selecting destinations, drawing cards and purchase connections.
 */
public class BotOverlap extends Bot {
    //protected GeneralUtils.PathValues currentPathAndDest = null;
    private PathManager currentPath;
    private MeepleSelectorManager meepleSelector;
    private WagonDrawManager wagonDrawManager;
    private DestinationCardDrawManager destinationCardDrawManager;
    public BotOverlap(String name, GameEngine gameEngine) throws WrongPlayerException {
        super(name, gameEngine);

    }

    /**
     * Initializes additional components of the bot, such as path selection and card management.
     * @throws WrongPlayerException if the player is not valid
     */
    private void createRestOfBot() throws WrongPlayerException {
        this.meepleSelector = new MeepleSelectorManager(this, gameView);
        this.wagonDrawManager = new WagonDrawManager(gameView, this);
        this.destinationCardDrawManager = new DestinationCardDrawManager(this, gameView);
        this.currentPath = destinationCardDrawManager.chooseOriginalDestCards();

    }

    /**
     * determines the next destination to pursue based on the shortest path cost.
     *
     * @return the best destination path to follow, or null if no path is available.
     * @throws WrongPlayerException if it's not your turn
     */
    public PathManager nextDestinationToDo() throws WrongPlayerException {
        List<DestinationCard> allDestCardOwned = gameView.getMyDestinationCards();
        int cheapestPathCost = Integer.MAX_VALUE;
        PathManager bestDestinationCardWithPath = null;
        for (DestinationCard destCard : allDestCardOwned)
        {
            if (!gameEngine.valideDestination(destCard, this))
            {
                PathManager currentDestPath = new PathManager(destCard, this, gameView);
                Integer length = currentDestPath.findTotalCostRemaining();
                //length null indicated an impossible dest card
                if (length != null && length < cheapestPathCost)
                {
                    bestDestinationCardWithPath = currentDestPath;
                    cheapestPathCost = length;
                }
            }
        }
        return bestDestinationCardWithPath;
    }

    /**
     * Attempts to purchase a rail connection if possible
     * @return true if a connection was successfully purchased otherwise false.
     * @throws WrongPlayerException if it's not the player turn
     */
    public boolean buyRail() throws WrongPlayerException {
        if (this.currentPath == null) {
            return false;
        }
        //this gives me a connection I can buy, ie I have enough wagons
        Connection toPurchase = currentPath.connectionToPurchase();
        if (toPurchase != null) {
            return gameEngine.buyRail(this, toPurchase);
        }
        else {
            while (this.currentPath == null || this.currentPath.getConnectionsForCurrentDestCard().size() == 0 || this.gameEngine.valideDestination(this.currentPath.getDestCardOfpath(), this))
            {
                this.currentPath = nextDestinationToDo();
                if (this.currentPath == null)
                {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean drawWagonCard() throws WrongPlayerException {
        if (this.currentPath != null) {
            List<Colors> colorsToDraw = this.currentPath.colorsToDraw();
            if (colorsToDraw == null)
            {
                return false;
            }
            return this.wagonDrawManager.drawWagonsOnList(colorsToDraw);
        }
        return false;
    }

    public boolean drawDestinationCard() throws WrongPlayerException {
        this.currentPath = this.destinationCardDrawManager.drawDestinationsFromNumber(2);
        return this.currentPath != null;
    }


    public boolean playTurn() throws WrongPlayerException {
        displayPlayTurn();
        //first turn, you need to draw at first
        if (gameView.getRound() == 0)
        {
            createRestOfBot();
        }
        //real turns (the actual ones, not the fake invented one played above
        if (!buyRail())
        {
            if (this.currentPath == null)
            {
                try
                {
                    return drawDestinationCard();
                }
                catch (DeckEmptyException e)
                {
                    //return drawWagonCard();
                }
            }
            else
            {
                try
                {
                    return drawWagonCard();
                }
                catch (DeckEmptyException e)
                {
                    drawDestinationCard();
                    //this.currentPath = new PathManager()
                }
            }
        }
        /*
        the logic is: we try to buy a rail: if no current path, we draw a new dest
        if there is a current path but we cant buy a rail, we draw wagons
         */


        //try to buy a connection
        //if no current path, draw destination
        //if current path, draw wagons
        return false;
    }



    //------------------------------------------------------------------------------------------------------
    //methods due to bad abstraction
    public boolean drawWagonCard(Colors color)
    {
        return false;
    }
    public boolean buyConnection(ArrayList<Connection> connections)
    {
        return false;
    }


}
