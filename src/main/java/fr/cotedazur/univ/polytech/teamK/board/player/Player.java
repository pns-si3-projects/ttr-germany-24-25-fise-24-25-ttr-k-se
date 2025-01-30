package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.*;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameView;

import java.util.*;

public class Player {
    private int id ;
    private static int COUNT = 1;
    private String name ;
    private int score;
    private Meeple meeples;
    private int wagonsRemaining;
    private PlayerOwnedMap playerMap;

    //private Board gameMap;

    private ArrayList<Connection> connections;
    private ArrayList<WagonCard> wagonCards;
    private ArrayList<DestinationCard> destinationCards;


    public Player(String name) {
        this.id = COUNT++;
        this.name = name;
        this.score = 0;
        this.wagonsRemaining = 5;
        this.wagonCards = new ArrayList<>();
        this.destinationCards = new ArrayList<>();
        this.connections = new ArrayList<>();
        this.meeples = new Meeple();
        playerMap = new PlayerOwnedMap();
        //this.gameMap = gameMap;

    }

    public Player(int id, String name, ArrayList<WagonCard> wagonCards, ArrayList<DestinationCard> destinationCards) {
        this(name);
        this.wagonCards = wagonCards;
        this.destinationCards = destinationCards;
    }

    // Getteur and Setteur
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public void resetId() {this.id = 0;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getScore() {return score;}
    public ArrayList<DestinationCard> getCartesDestination() {return destinationCards;}
    public ArrayList<WagonCard> getCartesWagon() {return wagonCards;}
    public int getWagonsRemaining() {return wagonsRemaining;}
    public int getNumberWagon() {return wagonCards.size();}
    public int getNumberDestination () {return destinationCards.size();}
    public Meeple getMeeples() {return meeples;}
    public void setMeeples(Meeple meeples) {this.meeples = meeples;}
    public int getNumberOfMeeples() {return meeples.getNumber();}
    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public static void resetIdCounter() {
        COUNT = 1;
    }

    public void playTurn(GameView gameView) {
        System.out.println(name + " joue son tour !");
    }

    /**
     * Modify the score of the player by adding a value
     * @param value the value to add to the score
     */
    public void addScore(int value) {
        this.score += value;
    }

    /**
     * Add a WagonCard to the player's hand
     * @param carte the card to add
     */
    public boolean addCardWagon(WagonCard carte) {
        this.wagonCards.add(carte);
        return true;
    }


    public boolean removeCardWagon(Colors color, int count) {
        if (getNumberColor(color) < count) {
            throw new IllegalArgumentException("The player doesn't have enough cards");
        }
        if (getWagonsRemaining() < count) {
            throw new IllegalArgumentException("The player doesn't have enough wagons");
        }
        wagonsRemaining -= count;
        ArrayList<WagonCard> toRemove = new ArrayList<>();
        for (WagonCard carte : this.wagonCards) {
            if (carte.getColor() == color && count > 0) {
                toRemove.add(carte);
                count--;
            }
        }

        if (count > 0) {
            throw new IllegalArgumentException("Not enough cards to remove");
        }


        this.wagonCards.removeAll(toRemove);
        return true;
    }

    /**
     * Get the number of WagonCard of a specific color
     * @param color the color to count
     * @return the number of WagonCard of the color
     */
    public int getNumberColor(Colors color) {
        int count = 0;
        for (WagonCard carte : this.wagonCards) {
            if (carte.getColor() == color) {
                count++;
            }
        }
        return count;
    }

    /**
     * Add a DestinationCard to the player's hand
     * @param carte the card to add
     * @return true if the card was added, false otherwise
     */
    public boolean addCardDestination(DestinationCard carte) {
        if (destinationCards.contains(carte)) {
            throw new IllegalArgumentException("The player already has this card");
        }
        this.destinationCards.add(carte);
        return true;
    }



    /**
     * Add DestinationCard points and remove it from the player's hand
     * @param carte the card to check
     * @return true if the card was removed, false otherwise
     */
    public boolean validDestinationCard(DestinationCard carte) {
        String cityOne = carte.getEndCity().getName();
        String cityTwo = carte.getStartCity().getName();
        Boolean connected = false;
        if (playerMap.isNeighbour(cityOne, cityTwo) && destinationCards.contains(carte)) {
            this.score += carte.getValue();
            this.destinationCards.remove(carte);
            return true;
        }
        throw new IllegalArgumentException("The player doesn't have this card");
    }

    /**
     * Transfer the neeples from a city to the player
     * @param city the city to take the neeples from
     */
    /**
     * Transfer the neeples from a city to the player
     * @param city the city to take the neeples from
     * @param colorChoice a list with the order for color choice
     */
    public boolean takeMeeples(City city, Colors colorChoice) {
        if (colorChoice.ordinal() > 5) {
            throw new IllegalArgumentException("Couleur de meeples inconnue");
        }
        if (!city.getPlayersThatPickedUpMeeples().contains(this)) {
            if(meeples.transferMeeples(city.getMeeples(), colorChoice)) {
                city.addPlayer(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Function who allow a player to buy a rail
     * @param connectionToBuy the connection we want to buy
     * @return true if we bought it, false otherwise
     */
    public boolean buyRail(Connection connectionToBuy, Board gameMap, int numberOfPlayers)
    {
        Colors connectionColor = connectionToBuy.getColor();
        int cardsOfCorrectColor = getNumberColor(connectionColor);
        int lengthOfRail = connectionToBuy.getLength();
        int rainbowCards = getNumberColor(Colors.RAINBOW);

        if (connectionToBuy.claimAttempt(cardsOfCorrectColor + rainbowCards, this, gameMap, numberOfPlayers))
        {
            this.connections.add(connectionToBuy);

            if (lengthOfRail > cardsOfCorrectColor)
            {
                int rainbowsUsed = lengthOfRail - cardsOfCorrectColor;
                removeCardWagon(Colors.RAINBOW, rainbowsUsed);
                removeCardWagon(connectionColor, cardsOfCorrectColor);
            }
            else
            {
                removeCardWagon(connectionColor, lengthOfRail);
            }
            //removeCardWagon(connectonColor, min(lengthOfRail, cardsOfCorrectColor)
            connectionToBuy.setOwner(this);
            playerMap.updateMap(connectionToBuy, gameMap);
            score += connectionToBuy.calculatePoints(lengthOfRail);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (getName().equals("PlayerBank")){
            return "";
        }
        return "\nNom: " + getName() + "\nScore: " + getScore() + "\nCartes Destination: " + getCartesDestination() + "\nCartes Wagons: " + getCartesWagon() + "\nMeeples: " + getMeeples() + "\nConnections Owned" + getConnections();
    }
}

