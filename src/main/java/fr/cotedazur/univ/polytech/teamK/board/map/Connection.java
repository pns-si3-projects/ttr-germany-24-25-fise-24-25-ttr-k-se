package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.MapHash;

import java.awt.*;

public abstract class Connection {
    private City cityOne;
    private City cityTwo;
    private Integer length;
    private Colors color;

    private Player owner;
    private boolean isFree;

    //Constructeur pour les virtual connections
    public Connection(City cityOne, City cityTwo)
    {
        setCityOne(cityOne);
        setCityTwo(cityTwo);
    }

    //Constructeur pour les physical conections
    public Connection(City cityOne, City cityTwo, Integer aLength, Colors aColor)
    {
        this(cityOne,cityTwo);
        setLength(aLength);
        setColor(aColor);
        setFree(true);
        setOwner(null);
    }
    private void setCityOne(City cityOne) {
        if (cityOne == null){
            throw new IllegalArgumentException("cityOne must not be null");
        }
        this.cityOne = cityOne;
    }
    private void setCityTwo(City cityTwo) {
        if (cityTwo == null){
            throw new IllegalArgumentException("cityTwo must not be null");
        }
        if (cityOne.equals(cityTwo)){
            throw new IllegalArgumentException("StartCity and EndCity must not be the same");
        }
        this.cityTwo = cityTwo;
    }
    private void setLength(Integer length) {
        if (length <= 0){
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        this.length = length;
    }
    private void setColor(Colors color) {
        this.color = color;
    }
    private void setFree(boolean free)
    {
        isFree = free;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }

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
    public boolean isFree() {
        return isFree;
    }
    public Player getOwner() {
        return owner;
    }



    /**
     * Calculates the points earned for a given route length
     * @param length the length of the route
     * @return the points earned
     */
    public static int calculatePoints(int length){
        switch(length){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 5:
                return 10;
            case 6:
                return 15;
            case 7:
                return 18;
            case 8:
                return 21;
            default:
                throw new IllegalArgumentException("Invalid route lenght: "+ length);
        }
    }

    @Override
    public String toString()
    {
        String base = "" + this.getCityOne() + "connected to" + this.getCityTwo();
        return base;
    }
}
