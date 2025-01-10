package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List physicalConnectionList;
    private List<Player> playersThatPickedUpMeeples;
    /**
     * Constructs a new City with the specified name and number of meeples.
     *
     * @param name the name of the city
     * @param numberOfMeeples the number of meeples in the city
     */
    public City(String name, int numberOfMeeples) {
        this.id = COUNT++;
        setName(name);
        this.meeples = new Meeple(numberOfMeeples);
        setPhysicalConnectionList();
        this.playersThatPickedUpMeeples = new ArrayList<>();
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
    public List getPhysicalConnectionList() {return physicalConnectionList;}

    /**
     * Initializes the list of physical connections.
     */
    public void setPhysicalConnectionList() {
        this.physicalConnectionList = new ArrayList();
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
     * @param physicalConnection the physical connection to add
     */
    public void addPhysicalConnection(PhysicalConnection physicalConnection){
        this.physicalConnectionList.add(physicalConnection);
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

}
