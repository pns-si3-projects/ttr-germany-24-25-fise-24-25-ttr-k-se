package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;

public class Connection {
    private City cityOne;
    private City cityTwo;
    private Integer length;
    private Colors color;
    
    private Player owner;
    private boolean isFree;
    private static int COUNT = 1;
    private int tag;

    private ConnectionClaimService claimService;
    private ConnectionUtils utils;

    /**
     * Constructs a Connection object for virtual connections.
     *
     * @param cityOne the first city
     * @param cityTwo the second city
     */
    public Connection(City cityOne, City cityTwo) {
        setCityOne(cityOne);
        setCityTwo(cityTwo);
    }

    /**
     * Constructs a Connection object for physical connections.
     *
     * @param cityOne the first city
     * @param cityTwo the second city
     * @param aLength the length of the connection
     * @param aColor  the color of the connection
     */
    public Connection(City cityOne, City cityTwo, Integer aLength, Colors aColor)
    {
        this(cityOne,cityTwo);
        setLength(aLength);
        setColor(aColor);
        setFree(true);
        setOwner(null);
        this.tag = COUNT++;
    }

    /**
     * Sets the first city.
     *
     * @param cityOne the first city
     * @throws IllegalArgumentException if cityOne is null
     */
    private void setCityOne(City cityOne) {
        if (cityOne == null){
            throw new IllegalArgumentException("cityOne must not be null");
        }
        this.cityOne = cityOne;
    }

    /**
     * Sets the second city.
     *
     * @param cityTwo the second city
     * @throws IllegalArgumentException if cityTwo is null or the same as cityOne
     */
    private void setCityTwo(City cityTwo) {
        if (cityTwo == null){
            throw new IllegalArgumentException("cityTwo must not be null");
        }
        if (cityOne.equals(cityTwo)){
            throw new IllegalArgumentException("StartCity and EndCity must not be the same");
        }
        this.cityTwo = cityTwo;
    }

    /**
     * Sets the length of the connection.
     *
     * @param length the length of the connection
     * @throws IllegalArgumentException if length is less than or equal to 0
     */
    private void setLength(Integer length) {
        if (length <= 0){
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        this.length = length;
    }

    /**
     * Sets the color of the connection.
     *
     * @param color the color of the connection
     */
    private void setColor(Colors color) {
        this.color = color;
    }

    /**
     * Sets whether the connection is free.
     *
     * @param free true if the connection is free, false otherwise
     */
    public void setFree(boolean free)
    {
        isFree = free;
    }

    /**
     * Sets the owner of the connection.
     *
     * @param owner the owner of the connection
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the first city.
     *
     * @return the first city
     */
    public City getCityOne() {
        return cityOne;
    }

    /**
     * Gets the second city.
     *
     * @return the second city
     */
    public City getCityTwo() {
        return cityTwo;
    }

    /**
     * Give the other city of a connection
     * @param city the city we know
     * @return the city we don't know
     * @throws IllegalArgumentException if the city isn't in the connection
     */
    public City getOtherCity (City city) throws IllegalArgumentException {
        if (city == cityOne) {
            return cityTwo;
        } else if (city == cityTwo) {
            return cityOne;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Gets the length of the connection.
     *
     * @return the length of the connection
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Gets the color of the connection.
     *
     * @return the color of the connection
     */
    public Colors getColor() {
        return color;
    }

    public City getOtherCity(City city) {
        if (city.equals(cityOne)) {
            return cityTwo;
        } else if (city.equals(cityTwo)) {
            return cityOne;
        } else {
            throw new IllegalArgumentException("City not part of this connection");
        }
    }

    /**
     * Checks if the connection is free.
     *
     * @return true if the connection is free, false otherwise
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * Gets the owner of the connection.
     *
     * @return the owner of the connection
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Attempts to claim the connection.
     *
     * @param numberOfCardsUsed the number of cards used to claim the connection
     * @param player            the player attempting to claim the connection
     * @param gameMap           the game map
     * @param numberOfPlayers   the number of players in the game
     * @return true if the connection is successfully claimed, false otherwise
     * @throws IllegalArgumentException if the number of cards used is less than 0
     */
    public boolean claimAttempt(Integer numberOfCardsUsed, Player player, Board gameMap, int numberOfPlayers) {
        return claimService.claimAttempt(this, numberOfCardsUsed, player, gameMap, numberOfPlayers);
    }

    /**
     * Calculates the points for the connection's length.
     *
     * @return the points for the connection's length
     * @throws IllegalArgumentException if the length is invalid
     */
    public int calculatePoints(int length) {
        return utils.calculatePoints(length);
    }

    @Override
    public String toString()
    {
        String base = "" + this.getCityOne() + "connected to" + this.getCityTwo();
        return base;
    }
}
