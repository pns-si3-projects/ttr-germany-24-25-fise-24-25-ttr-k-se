package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.*;

import java.util.*;

public class Player {
    private int id ;
    private static int COUNT = 1;
    private String name ;
    private int score;
    private Meeples meeples;

    public ArrayList<Connection> getConnections() {
        return connections;
    }
    private Map<Integer, Map<Integer, Integer>> virtualConnectionsCreated;

    private ArrayList<Connection> connections;
    private ArrayList<WagonCard> wagonCards;
    private ArrayList<DestinationCard> destinationCards;


    public Player(String name) {
        this.id = COUNT++;
        this.name = name;
        this.score = 0;
        this.wagonCards = new ArrayList<>();
        this.destinationCards = new ArrayList<>();
        this.connections = new ArrayList<>();
        this.meeples = new Meeples();
        createVirtualConnectionMap();

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
    public int getNumberWagon() {return wagonCards.size();}
    public int getNumberDestination () {return destinationCards.size();}
    public Meeples getMeeples() {return meeples;}
    public void setMeeples(Meeples meeples) {this.meeples = meeples;}
    public int getNumberOfMeeples() {return meeples.getNumber();}

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


    public boolean removeCardWagon(Colors color, int number) {
        int count = number;
        if (getNumberColor(color) < number) {
            throw new IllegalArgumentException("The player doesn't have enough cards");
        }

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
        if (destinationCards.contains(carte)) {
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
    public boolean takeMeeples(City city) {
        try {
            if (!city.getPlayersThatPickedUpMeeples().contains(this)) {
                this.meeples.transferMeeples(city.getMeeples());
                city.addPlayer(this);
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    /**
     * Function who allow a player to buy a rail
     * @param connectionToBuy the connection we want to buy
     * @return true if we bought it, false otherwise
     */
    public boolean buyRail(PhysicalConnection connectionToBuy)
    {
        if (connectionToBuy.claimAttempt(getNumberColor(connectionToBuy.getColor())))
        {
            this.connections.add(connectionToBuy);
            removeCardWagon(connectionToBuy.getColor(), connectionToBuy.getLength());
            connectionToBuy.setOwner(this);
            updateMap(connectionToBuy);
            return true;
        }
        return false;
    }

    //hashmap of (city, Hashmap of (cities, distance))

    public void createVirtualConnectionMap(){
        virtualConnectionsCreated = new HashMap<Integer, Map<Integer, Integer>>();
    }
    public void addCityToHashmap(City city) {
        virtualConnectionsCreated.put(city.getId(), new HashMap<Integer, Integer>());
    }
    public void connectTwoCities(Integer cityOneID, Integer cityTwoID, Integer length) {
        //test if there already is a connection: if yes, keep the longest one ; WE LOOSE INFO
        Map<Integer, Integer> cityOneMap = virtualConnectionsCreated.get(cityOneID);
        if ((!cityOneMap.containsKey(cityTwoID)) || (cityOneMap.containsKey(cityTwoID) && cityOneMap.get(cityTwoID) <= length)) {
            //only replace if length is greater than previous length VERIFY
            virtualConnectionsCreated.get(cityOneID).put(cityTwoID, length);
            virtualConnectionsCreated.get(cityTwoID).put(cityOneID, length);
        }

    }
    //i start with two cities A and B
    //in every city connected to A, i connect it to every city connected to city B
    public void updateMap(Connection c) {
        City cityA = c.getCityOne();
        City cityB = c.getCityTwo();
        Integer lengthToAdd = c.getLength();
        //update map only if the lengthToAdd is longer than the existing distance between A and B (originally -infinity)
        if ((virtualConnectionsCreated.get(cityA.getId()) == null) || (virtualConnectionsCreated.get(cityA.getId()).get(cityB.getId()) == null) || (lengthToAdd > virtualConnectionsCreated.get(cityA.getId()).get(cityB.getId()))) {
            //here I have ensured that either there was no previous path from A to B, or that the new connection is Longer
            //so I want to connect all neighbors of A to all neigbors of B (just need to avoid connecting a city to itself
            //loop over all neighbors of A
            for (var cityOne : (virtualConnectionsCreated.get(cityA.getId())).entrySet()) {
                Integer cityOneID = cityOne.getKey();
                //loop over all neighbors of B
                for (var cityTwo : (virtualConnectionsCreated.get(cityB.getId())).entrySet()) {
                    Integer cityTwoID = cityTwo.getKey();
                    //ensure I dont connect cityOne to cityTwo.
                    //otherwise, connect cityOne to cityTwo with length(1-A) + length (A-B) + length(B-2)
                    if (!cityOneID.equals(cityTwoID)) {
                        lengthToAdd += virtualConnectionsCreated.get(cityOneID).get(cityA.getId());
                        lengthToAdd += virtualConnectionsCreated.get(cityTwoID).get(cityB.getId());
                        connectTwoCities(cityOneID, cityTwoID, lengthToAdd);
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

