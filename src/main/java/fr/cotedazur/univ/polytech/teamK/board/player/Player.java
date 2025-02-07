package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.*;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;

import java.util.*;

public class Player {
    private final int id ;
    private static int count = 1;
    private String name ;
    private int score;
    private Meeple meeples;
    private int wagonsRemaining;
    private final PlayerOwnedMap playerMap; //first idea to find if two cities are connected

    private final ArrayList<Connection> connections;
    private ArrayList<WagonCard> wagonCards;
    private ArrayList<DestinationCard> destinationCards;
    private final ArrayList<DestinationCard> achieveDestination;

    public Player(String name) {
        this.id = count++;
        this.name = name;
        this.score = 0;
        this.wagonsRemaining = 45;
        this.wagonCards = new ArrayList<>();
        this.destinationCards = new ArrayList<>();
        this.achieveDestination = new ArrayList<>();
        this.connections = new ArrayList<>();
        this.meeples = new Meeple();
        playerMap = new PlayerOwnedMap();

    }

    public Player(String name, ArrayList<WagonCard> wagonCards, ArrayList<DestinationCard> destinationCards) {
        this(name);
        this.wagonCards = wagonCards;
        this.destinationCards = destinationCards;
    }

    // Getter and Setter
    public int getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getScore() {return score;}
    public ArrayList<DestinationCard> getCartesDestination() {return destinationCards;}
    public ArrayList<WagonCard> getCartesWagon() {return wagonCards;}
    public int getWagonsRemaining() {return wagonsRemaining;}
    public int getNumberWagon() {return wagonCards.size();}
    public int getNumberDestination () {return destinationCards.size();}
    public ArrayList<DestinationCard> getAchieveDestination () {return achieveDestination;}
    public Meeple getMeeples() {return meeples;}
    public void setMeeples(Meeple meeples) {this.meeples = meeples;}
    public ArrayList<Connection> getConnections() {
        return connections;
    }
    public static void resetIdCounter() {
        count = 1;
    }

    /**
     * Modify the score of the player by adding a value
     * @param value the value to add to the score
     */
    public void addScore(int value) {
        this.score += value;
    }
    public void removeDestinationCard(DestinationCard destinationCard) {
        getCartesDestination().remove(destinationCard);
    }

    /**
     * Add a WagonCard to the player's hand
     *
     * @param carte the card to add
     */
    public void addCardWagon(WagonCard carte) {
        this.wagonCards.add(carte);
    }

    /**
     * Remove some wagon cards from the player deck and the wagon associated to it
     * @param color the color of cards to remove
     * @param count the number of cards to remove
     * @throws IllegalArgumentException if the player doesn't have enough cards or enough wagon
     */
    public void removeCardWagon(Colors color, int count) throws  IllegalArgumentException{
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
    }

    /**
     * Get the number of WagonCard of a specific color
     * @param color the color to count
     * @return the number of WagonCard of the color
     */
    public int getNumberColor(Colors color) {
        int numWagonCardOfXColor = 0;
        for (WagonCard carte : this.wagonCards) {
            if (carte.getColor() == color) {
                numWagonCardOfXColor++;
            }
        }
        return numWagonCardOfXColor;
    }

    public Colors getMaxColor () {
        int [] listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0,0,0,0,0};
        for (WagonCard card : wagonCards) {
            listOfOwnedMeeples[card.getColor().ordinal()] ++;
        }
        Colors res = Colors.YELLOW.getColorById(0);
        for (int i=1 ; i< listOfOwnedMeeples.length-1 ; i++) {
            if(listOfOwnedMeeples[i] > listOfOwnedMeeples[res.ordinal()]) res = Colors.YELLOW.getColorById(i);
        }
        return res;
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
     * @param card the card to check
     * @return true if the card was removed, false otherwise
     */
    public boolean validDestinationCard(DestinationCard card) {
        if (destinationCards.contains(card)) {
            this.score += card.getValue();
            this.destinationCards.remove(card);
            this.achieveDestination.add(card);
            return true;
        }
        else
            throw new IllegalArgumentException("The player doesn't have this card");
        }


    /**
     * Transfer the meeples from a city to the player
     * @param city the city to take the meeples from
     * @param colorChoice a list with the order for color choice
     * @throws IllegalArgumentException if the meeple color doesn't exist
     */
    public boolean takeMeeples(City city, Colors colorChoice) throws IllegalArgumentException, PlayerSeenException {
        if (city.getMeeples().isEmpty()) return true;
        if (colorChoice.ordinal() > 5) {
            throw new IllegalArgumentException("Couleur de meeples inconnue");
        }
        if (!city.getPlayersThatPickedUpMeeples().contains(this)) {
            if(meeples.transferMeeples(city.getMeeples(), colorChoice)) {
                city.addPlayer(this);
                return true;
            }
        } else {
            throw new PlayerSeenException("Player déjà vu");
        }
        return false;
    }

    /**
     * Function who allow a player to buy a rail
     * @param connectionToBuy the connection we want to buy
     * @return true if we bought it, false otherwise
     */
    public boolean buyRail(Connection connectionToBuy, GameBoard gameMap, int numberOfPlayers)
    {
        Colors connectionColor = connectionToBuy.getColor();
        if (connectionColor.equals(Colors.GRAY)) {
            connectionColor = getMaxColor();
        }
        int cardsOfCorrectColor = getNumberColor(connectionColor);
        int lengthOfRail = connectionToBuy.getLength();
        int rainbowCards = getNumberColor(Colors.RAINBOW);

        if (connectionToBuy.claimAttempt(cardsOfCorrectColor + rainbowCards, this, gameMap, numberOfPlayers))
        {
            this.connections.add(connectionToBuy);

            if (lengthOfRail >= cardsOfCorrectColor)
            {
                int rainbowsUsed = lengthOfRail - cardsOfCorrectColor;
                removeCardWagon(Colors.RAINBOW, rainbowsUsed);
                removeCardWagon(connectionColor, cardsOfCorrectColor);
            }
            else
            {
                removeCardWagon(connectionColor, lengthOfRail);
            }
            //removeCardWagon(connectionColor, min(lengthOfRail, cardsOfCorrectColor)
            connectionToBuy.setOwner(this);
            //playerMap.updateMap(connectionToBuy, gameMap);
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
