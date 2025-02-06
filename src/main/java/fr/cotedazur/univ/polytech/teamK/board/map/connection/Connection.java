package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;

public class Connection {
    private City cityOne;
    private City cityTwo;
    private Integer length;
    private Colors color;
    
    private Player owner;
    private boolean isFree;
    private static int COUNT = 1;

    public Connection(City cityOne, City cityTwo) {
        this(cityOne,cityTwo,0, null);
    }

    public Connection(City cityOne, City cityTwo, Integer aLength, Colors aColor)
    {
        setCityOne(cityOne);
        setCityTwo(cityTwo);
        setLength(aLength);
        this.color = aColor;
        this.isFree = true;
        this.owner =null;
        COUNT++;
    }

    /**
     * Sets the first city.
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

    public void setOwner(Player owner) {this.owner = owner;}
    public void setFree(boolean free) {isFree = free;}
    public City getCityOne() {
        return cityOne;
    }
    public City getCityTwo() {
        return cityTwo;
    }
    public Integer getLength() {
        return length;
    }
    public Colors getColor() {
        return color;
    }

    /**
     * Get a city we don't know from a connection
     * @param city the city we know
     * @return the city we don't know
     */
    public City getOtherCity(City city) {
        if (city.equals(cityOne)) {
            return cityTwo;
        } else if (city.equals(cityTwo)) {
            return cityOne;
        } else {
            throw new IllegalArgumentException("City not part of this connection");
        }
    }

    public boolean getIsFree() {
        return isFree;
    }
    public Player getOwner() {
        return owner;
    }

    /**
     * Attempts to claim the connection.
     * @param numberOfCardsUsed the number of cards used to claim the connection
     * @param player            the player attempting to claim the connection
     * @param gameMap           the game map
     * @param numberOfPlayers   the number of players in the game
     * @return true if the connection is successfully claimed, false otherwise
     * @throws IllegalArgumentException if the number of cards used is less than 0
     */
    public boolean claimAttempt(Integer numberOfCardsUsed, Player player, GameBoard gameMap, int numberOfPlayers) {
        return ConnectionClaimService.claimAttempt(this, numberOfCardsUsed, player, gameMap, numberOfPlayers);
    }

    /**
     * Calculates the points for the connection's length.
     * @return the points for the connection's length
     * @throws IllegalArgumentException if the length is invalid
     */
    public int calculatePoints(int length) {
        return ConnectionUtils.calculatePoints(length);
    }

    @Override
    public String toString()
    {
        return this.getCityOne() + " -> " + this.getCityTwo();
    }
}
