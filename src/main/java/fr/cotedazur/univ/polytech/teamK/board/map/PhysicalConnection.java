package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

public class PhysicalConnection extends Connection {

    public PhysicalConnection(City cityOne, City cityTwo, Integer aLength, Colors aColor) {
        super(cityOne, cityTwo, aLength, aColor);
    }

    public boolean claimAttempt(Integer numberOfCardsUsed)
    {
        if (numberOfCardsUsed < 0){
            throw new IllegalArgumentException("Number of Cards Used must be greater than 0");
        }
        else if (numberOfCardsUsed <= super.getLength()){
            return false;
            //make an exception? or idk. to say "hey we dont have enough cards"
        }
        else if (this.getOwner() != null)
        {
            return false;
            //differentiate already owned to too expensive?
        }
        else
        {
            return true;
            //used enough cards, and the rail is free
        }
    }

    @Override
    public String toString() {
        return "" + getCityOne() + " <--> " + getCityTwo();
        //return owners.toString();
    }

}
