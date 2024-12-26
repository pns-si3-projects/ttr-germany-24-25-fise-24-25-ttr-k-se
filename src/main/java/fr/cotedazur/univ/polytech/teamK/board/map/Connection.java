package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.awt.*;

public abstract class Connection {
    private City cityOne;
    private City cityTwo;
    private Integer length;
    private Colors color;

    private Player owner;
    private boolean isFree;

    public Connection(City aStartCity, City aEndCity, Integer aLength, Colors aColor)
    {
        setCityOne(aStartCity);
        setCityTwo(aEndCity);
        setLength(aLength);
        setColor(aColor);
        setFree(true);
        setOwner(null);
    }

    private void setCityOne(City startCity) {
        if (startCity == null){
            throw new IllegalArgumentException("StartCity must not be null");
        }
        this.cityOne = startCity;
    }
    private void setCityTwo(City endCity) {
        if (endCity == null){
            throw new IllegalArgumentException("EndCity must not be null");
        }
        if (cityOne.equals(endCity)){
            throw new IllegalArgumentException("StartCity and EndCity must not be the same");
        }
        this.cityTwo = endCity;
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

    @Override
    public String toString()
    {
        String base = "" + this.getCityOne() + "connected to" + this.getCityTwo();
        /*
        if (this.owner != null)
        {
            base += " owned by " + this.owner;
        }
        else
        {
            base += " is free";
        }
        */
        return base;
    }




}
