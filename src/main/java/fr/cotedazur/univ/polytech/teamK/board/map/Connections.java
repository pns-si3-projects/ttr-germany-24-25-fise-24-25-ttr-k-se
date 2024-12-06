package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;

public class Connections {
    /*
    needs:
    endpoint: a city
    starting point: a city
    length of connection
    width: number of rails
    colors: one color per rail
    */

    private Cities startCity;
    private Cities endCity;
    //private String startCity;
    //private String endCity;
    private Integer length = 0;
    private Integer width;
    private List<Colors> rails; //each element of the List is a color

    //private Player owner;
    private List<String> owners; //each element of the list is a player; an unowned one has owner NULL
    private Integer freeToPurchase;

    public Connections(Cities aStartCity, Cities aEndCity, Integer aLength, List<Colors> aRailsList) {
        this.startCity = aStartCity;
        this.endCity = aEndCity;
        this.length = aLength;
        this.width = aRailsList.size();
        this.freeToPurchase = this.width;
        this.rails = aRailsList;
        //this.fillRails();
        this.owners = new ArrayList<>(Collections.nCopies(width, "NULL"));
        if (this.length <= 0){
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        if (this.width <= 0){
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        if (this.startCity == null){
            throw new IllegalArgumentException("StartCity must not be null");
        }
        if (this.endCity == null){
            throw new IllegalArgumentException("EndCity must not be null");
        }
        if (this.startCity.equals(endCity)){
            throw new IllegalArgumentException("StartCity and EndCity must not be the same");
        }
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
        for (int railIndex = 0; railIndex < this.width; railIndex++) {
            if (this.owners.get(railIndex).equals("NULL") && (this.rails.get(railIndex).equals(aColor) /* idk where we want to do the grey being any color, so i have this in case || this.rails.get(railIndex).equals(Colors.GRAY)*/)){
                return railIndex;
            }
        }
        //value to indicate no free slot of that color: -1
        return -1;
    }
    public boolean buyRail(Colors aColor, Integer numberCardsUsed, String buyer) {
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
            return true;
        }
    }

}
