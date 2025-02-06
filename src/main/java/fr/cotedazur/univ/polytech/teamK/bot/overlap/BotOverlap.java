package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;


import java.util.*;

public class BotOverlap extends Bot {
    //protected GeneralUtils.PathValues currentPathAndDest = null;
    private PathManager currentPath;
    private MeepleSelectorManager meepleSelector;
    private WagonDrawManager wagonDrawManager;
    private DestinationCardDrawManager destinationCardDrawManager;
    public BotOverlap(String name, GameEngine gameEngine) throws WrongPlayerException {
        super(name, gameEngine);

    }


    private void createRestOfBot() throws WrongPlayerException {
        this.meepleSelector = new MeepleSelectorManager(this, gameView);
        this.wagonDrawManager = new WagonDrawManager(gameView, this);
        this.destinationCardDrawManager = new DestinationCardDrawManager(this, gameView);
        this.currentPath = destinationCardDrawManager.chooseOriginalDestCards();

    }

    public PathManager nextDestinationToDo()
    {
        List<DestinationCard> allDestCardOwned = gameView.getMyDestinationCards();
        Integer cheapestPathCost = Integer.MAX_VALUE;
        PathManager bestDestinationCardWithpath = null;
        for (DestinationCard destCard : allDestCardOwned)
        {
            if (!gameView.getPlayerByBot(this).validDestinationCard(destCard))
            {
                PathManager currentDestPath = new PathManager(destCard, this, gameView);
                Integer length = currentDestPath.findTotalCostRemaining();
                //length null indicated an impossible dest card
                if (length != null && length < cheapestPathCost)
                {
                    bestDestinationCardWithpath = currentDestPath;
                    cheapestPathCost = length;
                }
            }
        }
        return bestDestinationCardWithpath;
    }

    public boolean buyRail() throws WrongPlayerException {
        if (this.currentPath == null)
        {
            return false;
        }
        //this gives me a connection I can buy, ie I have enough wagons
        Connection toPurchase =  currentPath.connectionToPurchase();
        if (toPurchase != null)
        {
            boolean success = gameEngine.buyRail(this, toPurchase);
            if (!success)
            {
                //System.out.println("Failed to buy the rail, error in verificaiton problably");
                if (!toPurchase.getColor().equals(Colors.GRAY))
                {
                    System.out.println("Failed to buy the rail, error in verificaiton problably, the color is" + toPurchase.getColor());
                }
                //System.out.println(toPurchase.getColor());
            }

            return success;
        }
        else
        {
            if (this.gameView.getPlayerByBot(this).validDestinationCard(this.currentPath.getDestCardOfpath()))
            {
                this.currentPath = nextDestinationToDo();
            }
        }
        return false;
    }




    public boolean drawWagonCard()
    {
        List<Colors> colorsToDraw = this.currentPath.colorsToDraw();
        return this.wagonDrawManager.drawWagonsOnList(colorsToDraw);
    }

    public boolean drawDestinationCard() throws WrongPlayerException {
        try
        {
            this.currentPath = this.destinationCardDrawManager.drawDestinationsFromNumber(2);
            return true;
        }
        catch (WrongPlayerException e)
        {
            String noMoreDestination = "damn tourism realllllly took off eh mate";
        }
        return false;
    }


    public boolean playTurn() throws WrongPlayerException {
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
                return drawDestinationCard();
            }
            else
            {
                return drawWagonCard();
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

    //-------------------------------------------------------------------------------------------------------------
    //verifying state of bot methods
    /*
    private boolean verifNoDestCardsToDoAndPossible()
    {
        for (DestinationCard destcard : gameView.getMyDestinationCards())
        {
            if (!(gameView.getPlayerByBot(this).validDestinationCard(destcard)) && (djikstraPathValues(destcard) != null))
            {
                return false;
            }
        }
        return true;
    }
    */
    //-------------------------------------------------------------------
    //end of game safety method tests
    private boolean emptyDestinationDecksTest(String action)
    {
        if (gameView.getLongueDestination().isEmpty() && gameView.getShortDestination().isEmpty())
        {
            System.out.println("Destination decks are empty");
            System.out.println("You are trying to" + action);
            System.out.println("You have " + gameView.getMyWagonCards().size() + "number of wagon cards");
            System.out.println("You have " + gameView.getMyDestinationCards().size() + "number of destination cards cards");
            System.out.println("Your connections are " + gameView.getMyConnections());
            return true;
        }
        return false;
    }

    private boolean emptyWagonDeckTest(String action)
    {
        if (gameView.getWagonDeck().isEmpty() && gameView.getVisibleWagonCards().isEmpty())
        {
            System.out.println("Wagon decks are empty");
            System.out.println("You are trying to" + action);
            System.out.println("You have " + gameView.getMyWagonCards().size() + "number of wagon cards");
            System.out.println("You have " + gameView.getMyDestinationCards().size() + "number of destination cards cards");
            System.out.println("Your connections are " + gameView.getMyConnections());
            return true;
        }
        return false;
    }

}
