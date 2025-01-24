package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.*;

/**
 * Represents a city in the Ticket to Ride game.
 * A city has a name, a number of meeples, and a list of physical connections.
 */

public class City {
    //has a name
    //has a number of meeples, and their color
    //has a list of virtual connections, per player, going out.
    private int id;
    private static int COUNT = 1;
    private String name;
    private Meeple meeples;
    private List connectionList;
    private List<Player> playersThatPickedUpMeeples;
    private boolean isCountry;
    /**
     * Constructs a new City with the specified name and number of meeples.
     *
     * @param name the name of the city
     * @param numberOfMeeples the number of meeples in the city
     */
    public City(String name, int numberOfMeeples) {
        this(name, numberOfMeeples, new Random(), false);
    }
    public City(String name, int numberOfMeeples, boolean isCountry) {
        this(name, numberOfMeeples, new Random(), isCountry);
    }
    public City(String name, int numberOfMeeples, Random rand, boolean isCountry) {
        this.id = COUNT++;
        setName(name);
        this.meeples = new Meeple(numberOfMeeples, rand);
        setPhysicalConnectionList();
        this.playersThatPickedUpMeeples = new ArrayList<>();
        this.isCountry = isCountry;
    }

    public boolean isCountry() {
        return isCountry;
    }

    /**
     * Returns the ID of the city.
     *
     * @return the ID of the city
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the city.
     *
     * @return the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the meeples in the city.
     *
     * @return the meeples in the city
     */
    public Meeple getMeeples() {
        return meeples;
    }

    /**
     * Returns the list of physical connections of the city.
     *
     * @return the list of physical connections
     */
    public List getConnectionList() {return connectionList;}

    /**
     * Initializes the list of physical connections.
     */
    public void setPhysicalConnectionList() {
        this.connectionList = new ArrayList();
    }

    /**
     * Sets the name of the city.
     *
     * @param name the new name of the city
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Adds a physical connection to the city's list of physical connections.
     *
     * @param connection the connection to add
     */
    public void addConnection(Connection connection){
        this.connectionList.add(connection);
    }

    /**
     * Returns the list of players that picked up meeples from the city.
     *
     * @return the list of players that picked up meeples
     */
    public List<Player> getPlayersThatPickedUpMeeples() {
        return playersThatPickedUpMeeples;
    }

    /**
     * Adds a player to the list of players that picked up meeples from the city.
     *
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        if (playersThatPickedUpMeeples == null) {
            playersThatPickedUpMeeples = new ArrayList<>();
        }
        playersThatPickedUpMeeples.add(player);
    }

    @Override
    public String toString() {
        return getName();
    }
}
