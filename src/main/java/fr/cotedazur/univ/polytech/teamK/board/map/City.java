package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.security.SecureRandom;
import java.util.*;

/**
 * Represents a city in the Ticket to Ride game.
 * A city has a name, a number of meeples, and a list of physical connections.
 */

public class City {
    private int id;
    private static int COUNT = 1;
    private String name;
    private Meeple meeples;
    private List<Connection> connectionList;
    private List<Player> playersThatPickedUpMeeples;
    private boolean isCountry;


    public City(String name, int numberOfMeeples) {
        this(name, numberOfMeeples, new SecureRandom(), false);
    }
    public City(String name, int numberOfMeeples, boolean isCountry) {this(name, numberOfMeeples, new SecureRandom(), isCountry);}
    public City(String name, int numberOfMeeples, SecureRandom rand, boolean isCountry) {
        this.id = COUNT++;
        setName(name);
        this.meeples = new Meeple(numberOfMeeples, rand);
        setConnectionList();
        this.playersThatPickedUpMeeples = new ArrayList<>();
        this.isCountry = isCountry;
    }

    public boolean isCountry() {
        return isCountry;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Meeple getMeeples() {
        return meeples;
    }
    public List<Connection> getConnectionList() {return connectionList;}
    public void setConnectionList() {
        this.connectionList = new ArrayList<>();
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * Adds a  connection to the city's list of  connections.
     * @param connection the connection to add
     */
    public void addConnection(Connection connection){
        this.connectionList.add(connection);
    }

    /**
     * Returns the list of players that picked up meeples from the city.
     * @return the list of players that picked up meeples
     */
    public List<Player> getPlayersThatPickedUpMeeples() {
        return playersThatPickedUpMeeples;
    }

    /**
     * Adds a player to the list of players that picked up meeples from the city.
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return id == city.id && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
