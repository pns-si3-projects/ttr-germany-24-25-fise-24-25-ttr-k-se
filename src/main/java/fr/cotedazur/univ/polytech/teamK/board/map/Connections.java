package fr.cotedazur.univ.polytech.teamK.board.map;

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

    //private Cities startCity;
    //private Cities endCity;
    private String startCity;
    private String endCity;
    private Integer length = 0;
    private Integer width;
    private List<String> rails; //each element of the List is a color

    //private Player owner;
    private List<String> owners; //each element of the list is a player; an unowned one has owner NULL
    private Integer freeToPurchase;

    public Connections(/*Cities aStartCity, Cities aEndCity*/ String aStartCity, String aEndCity, Integer aLength, Integer aWidth) {
        this.startCity = aStartCity;
        this.endCity = aEndCity;
        this.length = max(aLength, this.length);
        this.width = aWidth;
        this.freeToPurchase = this.width;
        this.rails = new ArrayList<>(Collections.nCopies(aWidth, "NULL"));
        //this.fillRails();
        this.owners = new ArrayList<>(Collections.nCopies(aWidth, "NULL"));
    }

    public Integer getLength() {
        return length;
    }

    public List<String> getFreeRails() {
        List<String> freeRails = new ArrayList<>(this.freeToPurchase);
        for (int railIndex = 0; railIndex < this.width; railIndex++) {
            if (this.owners.get(railIndex).equals("NULL")){
                freeRails.add(rails.get(railIndex));
            }
        }
        return freeRails;
    }

    public Integer isFreeColor(String aColor){
        List<String> freeRails = getFreeRails();
        for (int railIndex = 0; railIndex < this.freeToPurchase; railIndex++) {
            if (freeRails.get(railIndex).equals(aColor)){
                return railIndex;
            }
        }
        return -1;
    }
    public boolean buyRail(String aColor, Integer numberCardsUsed, String buyer) {
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
