package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City {
    //has a name
    //has a number of meeples, and their color
    //has a list of virtual connections, per player, going out.
    private int id;
    private static int COUNT = 1;
    private String name;
    private Meeples meeples;
    private List physicalConnectionList;
    private List<Player> playersThatPickedUpMeeples = new ArrayList<>();

    public City(String name, int numberOfMeeples) {
        this.id = COUNT++;
        setName(name);
        this.meeples = new Meeples(numberOfMeeples);
        setPhysicalConnectionList();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Meeples getMeeples() {
        return meeples;
    }
    public List getPhysicalConnectionList() {return physicalConnectionList;}

    public void setPhysicalConnectionList() {
        this.physicalConnectionList = new ArrayList();
    }


    public void setName(String name){
        this.name = name;
    }
    public void addPhysicalConnection(PhysicalConnection physicalConnection){
        this.physicalConnectionList.add(physicalConnection);
    }

    public List<Player> getPlayersThatPickedUpMeeples()
    {
        return playersThatPickedUpMeeples;
    }
    public void addPlayer(Player player) {
        playersThatPickedUpMeeples.add(player);
    }

    @Override
    public String toString() {
        return name;
    }
}
