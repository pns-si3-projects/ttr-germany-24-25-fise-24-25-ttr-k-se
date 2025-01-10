package fr.cotedazur.univ.polytech.teamK.board.map;

public class VirtualConnection extends Connection {


    public VirtualConnection(City cityOne, City cityTwo) {
        super(cityOne,cityTwo);
    }

    @Override
    public String toString() {
        return getCityOne() + " <--> " + getCityTwo();
    }
}
