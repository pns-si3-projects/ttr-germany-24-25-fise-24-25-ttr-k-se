package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.*;
import fr.cotedazur.univ.polytech.teamK.game.MapHash;
import org.w3c.dom.css.Counter;

import java.util.*;

public class Player {
    private int id ;
    private static int COUNT = 1;
    private String name ;
    private int score;
    private Meeple meeples;
    private int wagonsRemaining;

    private Map<String, Map<String, Integer>> virtualConnectionsCreated;
    //private MapHash gameMap;

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
        createVirtualConnectionMap();
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
        if (isNeighbour(cityOne, cityTwo) && destinationCards.contains(carte)) {
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
    public boolean buyRail(Connection connectionToBuy, MapHash gameMap, int numberOfPlayers)
    {
        if (connectionToBuy.claimAttempt(getNumberColor(connectionToBuy.getColor()), this, gameMap, numberOfPlayers))
        {
            this.connections.add(connectionToBuy);
            removeCardWagon(connectionToBuy.getColor(), connectionToBuy.getLength());
            connectionToBuy.setOwner(this);
            updateMap(connectionToBuy);
            score += connectionToBuy.calculatePoints(connectionToBuy.getLength());
            return true;
        }
        return false;
    }

    /**
     * creates the map of cities the player has managed to tie together
     *
     */
    public void createVirtualConnectionMap(){
        virtualConnectionsCreated = new HashMap<String, Map<String, Integer>>();
    }
    /**
     * adds a city to the map of cities the player has manged to tie together
     *
     */
    private void addCityToHashmap(City city) {
        virtualConnectionsCreated.put(city.getName(), new HashMap<String, Integer>());
    }
    /**
     * connects two cities together on the map of connected cities:
     * @param cityOneName name of the first city
     * @param cityTwoName name of the second city
     * @param length length of the connection between the two cities
     * @param gameMap the board of the current game
     */
    public void connectTwoCities(String cityOneName, String cityTwoName, Integer length, MapHash gameMap) {
        if (!virtualConnectionsCreated.containsKey(cityOneName)) {
            addCityToHashmap(gameMap.getCity(cityOneName));
        }
        if (!virtualConnectionsCreated.containsKey(cityTwoName)) {
            addCityToHashmap(gameMap.getCity(cityTwoName));
        }

        if ((!isNeighbour(cityOneName, cityTwoName)) || (distance(cityOneName, cityTwoName) <= length)) {
            virtualConnectionsCreated.get(cityOneName).put(cityTwoName, length);
            virtualConnectionsCreated.get(cityTwoName).put(cityOneName, length);
        }

    }
    /**
     * updates the map of connected cities
     * @param c the connection that was just purchased
     * @param gameMap the board of the game
     */
    public void updateMap(Connection c, MapHash gameMap) {
        City cityA = c.getCityOne();
        String cityAName = cityA.getName();
        City cityB = c.getCityTwo();
        String cityBName = cityB.getName();
        Integer lengthToAdd = c.getLength();
        if (foundCity(cityAName) || (!isNeighbour(cityAName, cityBName)) || (lengthToAdd > distance(cityAName, cityBName))) {

            connectTwoCities(cityA.getName(), cityB.getName(), lengthToAdd, gameMap);
            if (cityA.isCountry() && !cityB.isCountry())
            {
                connectCountryAndCity(cityAName, cityBName, lengthToAdd, gameMap);
            }
            else if (cityB.isCountry() && !cityA.isCountry())
            {
                connectCountryAndCity(cityBName, cityAName, lengthToAdd, gameMap);
            }
            else if (!cityA.isCountry() && !cityB.isCountry())
            {
                connectCityAndCity(cityAName, cityBName, lengthToAdd, gameMap);
            }
        }
    }
    /**
     * have we found the city yet
     * @param cityName the city we want to see if we have found yet
     *
     */
    public boolean foundCity(String cityName) {
        return virtualConnectionsCreated.containsKey(cityName);
    }
    /**
     * test if two cities are connected
     * @param cityTwoName the first city
     * @param cityOneName the other city
     */
    public boolean isNeighbour(String cityOneName, String cityTwoName) {
        if (!virtualConnectionsCreated.containsKey(cityOneName))
        {
            return false;
        }
        else {
            return virtualConnectionsCreated.get(cityOneName).containsKey(cityTwoName);
        }
    }
    /**
     * distance between two cities
     * @param cityTwoName first city
     * @param cityOneName other city
     *
     */
    public int distance(String cityOneName, String cityTwoName) {
        return virtualConnectionsCreated.get(cityOneName).get(cityTwoName);
    }
    /**
     * connects a country and city
     * countries cannot be a link in a chain, they are dead ends
     * @param countryName name of the country
     * @param cityName name of the city
     * @param lengthToAdd distance between the two
     * @param gameMap board
     */

    private void connectCountryAndCity(String countryName, String cityName, int lengthToAdd, MapHash gameMap)
    {
        for (var cityTwo : (virtualConnectionsCreated.get(cityName)).entrySet()) {
            String cityTwoName = cityTwo.getKey();
            if (!countryName.equals(cityTwoName)) {
                lengthToAdd += virtualConnectionsCreated.get(cityTwoName).get(cityName);
                connectTwoCities(countryName, cityTwoName, lengthToAdd, gameMap);
            }
        }
    }
    /**
     * connects a city and city
     * countries cannot be a link in a chain, they are dead ends
     * @param cityBName name of the country
     * @param cityAName name of the city
     * @param lengthToAdd distance between the two
     * @param gameMap board
     */
    private void connectCityAndCity(String cityAName, String cityBName, int lengthToAdd, MapHash gameMap)
    {
        for (var cityOne : (virtualConnectionsCreated.get(cityAName)).entrySet()) {
            String cityOneName = cityOne.getKey();
            //loop over all neighbors of B
            if (!cityOneName.equals(cityBName)) {
                for (var cityTwo : (virtualConnectionsCreated.get(cityBName)).entrySet()) {
                    String cityTwoName = cityTwo.getKey();
                    //ensure I dont connect cityOne to cityTwo.
                    //otherwise, connect cityOne to cityTwo with length(1-A) + length (A-B) + length(B-2)
                    if (!cityOneName.equals(cityTwoName) && !cityTwoName.equals(cityAName)) {
                        lengthToAdd += virtualConnectionsCreated.get(cityOneName).get(cityAName);
                        lengthToAdd += virtualConnectionsCreated.get(cityTwoName).get(cityBName);
                        connectTwoCities(cityOneName, cityTwoName, lengthToAdd, gameMap);
                    }
                }
            }
        }
    }



    @Override
    public String toString() {
        if (getName().equals("PlayerBank")){
            return "";
        }
        return "\nNom: " + getName() + "\nScore: " + getScore() + "\nCartes Destination: " + getCartesDestination() + "\nCartes Wagons: " + getCartesWagon() + "\nMeeples: " + getMeeples() + "\nConnections Owned" + getConnections();
    }
}

