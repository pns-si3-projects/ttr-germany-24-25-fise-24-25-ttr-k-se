package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;

public class Connection {
    /*
    needs:
    endpoint: a city
    starting point: a city
    length of connection
    width: number of rails
    colors: one color per rail
    */
    private Player playerBank; //used to fill owners list

    public Cities getStartCity() {
        return startCity;
    }

    private Cities startCity;

    public Cities getEndCity() {
        return endCity;
    }

    private Cities endCity;
    //private String startCity;
    //private String endCity;
    private Integer length;

    public Integer getWidth() {
        return width;
    }

    private Integer width;
    private List<Colors> rails; //each element of the List is a color

    //private Player owner;
    private List<Player> owners; //each element of the list is a player; an unowned one has owner NULL
    private Integer freeToPurchase;

    public Connection(Cities aStartCity, Cities aEndCity, Integer aLength, List<Colors> aRailsList) {
        setStartCity(aStartCity);
        setEndCity(aEndCity);
        setLength(aLength);
        setWidth(aRailsList.size());
        this.freeToPurchase = this.width;
        this.rails = aRailsList;
        //this.fillRails();
        playerBank = new Player("PlayerBank");
        this.owners = new ArrayList<>(Collections.nCopies(width, playerBank));
    }

    private void setStartCity(Cities startCity) {
        if (startCity == null){
            throw new IllegalArgumentException("StartCity must not be null");
        }
        this.startCity = startCity;
    }
    private void setEndCity(Cities endCity) {
        if (endCity == null){
            throw new IllegalArgumentException("EndCity must not be null");
        }
        if (startCity.equals(endCity)){
            throw new IllegalArgumentException("StartCity and EndCity must not be the same");
        }
        this.endCity = endCity;
    }
    private void setLength(Integer length) {
        if (length <= 0){
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        this.length = length;
    }
    private void setWidth(Integer width) {
        if (width <= 0){
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        this.width = width;
    }

    public void addOwner(Player player) {
        this.owners.add(player);
    }


    public Integer getLength() {
        return length;
    }

    public List<Colors> getFreeRails() {
        List<Colors> freeRails = new ArrayList<>(this.freeToPurchase);
        for (int railIndex = 0; railIndex < this.width; railIndex++) {
            if (this.owners.get(railIndex).equals("NULL")){
                freeRails.add(rails.get(railIndex));
            }
        }
        return freeRails;
    }

    public Integer isFreeColor(Colors aColor){
        for (int railIndex = 0; railIndex < rails.size(); railIndex++) {
            if (rails.get(railIndex) == aColor && owners.get(railIndex).equals(playerBank)){/* idk where we want to do the grey being any color, so i have this in case || this.rails.get(railIndex).equals(Colors.GRAY)*/
                return railIndex;
            }
        }
        //value to indicate no free slot of that color: -1
        return -1;
    }
    public boolean claimAttempt(Colors aColor, Integer numberCardsUsed, Player buyer) {
        if (this.length > numberCardsUsed || numberCardsUsed <= 0){
            //test if invalid or insufficient number of Cards uses
            return false;
        }
        Integer colorIndex = isFreeColor(aColor);
        if (colorIndex.equals(-1))
        {
            //test if a free rail of the correct color exists:
            return false;
        }
        else {
            owners.set(colorIndex, buyer);
            freeToPurchase--;
            int points = calculatePoints(this.length);
            buyer.addScore(points);
            return true;
        }
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
    public String toString() {
        return "" + getStartCity() + " <--> " + getEndCity();
        //return owners.toString();
    }

}
