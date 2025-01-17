package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.MapHash;


public class PhysicalConnection extends Connection {
    public PhysicalConnection(City cityOne, City cityTwo, Integer aLength, Colors aColor) {
        super(cityOne, cityTwo, aLength, aColor);
    }

    public boolean claimAttempt(Integer numberOfCardsUsed, Player player, MapHash gameMap, int numberOfPlayers)
    {
        if (numberOfCardsUsed < 0){
            throw new IllegalArgumentException("Number of Cards Used must be greater than 0");
        }
        else if (numberOfCardsUsed <= super.getLength()){
            return false;
            //make an exception? or idk. to say "hey we dont have enough cards"
        }
        else if (!this.isFree())
        {
            return false;
            //differentiate already owned to too expensive?
        }
        else
        {
            int connectionCount = gameMap.countConnectionsBetweenCities(this.getCityOne(), this.getCityTwo());
            if (numberOfPlayers <= 3 && connectionCount > 1) {
                return false; // Double/triple connections not available for 2-3 players
            } else {
                this.setFree(false);
                this.setOwner(player);
                if (connectionCount > 1) {
                    markOtherConnectionsAsClaimed(gameMap, this.getCityOne(), this.getCityTwo());
                }
                return true; // Successfully claimed
            }
        }
    }

    private void markOtherConnectionsAsClaimed(MapHash gameMap, City cityOne, City cityTwo) {
        for (Object obj : gameMap.getCities().get(cityOne.getName()).getPhysicalConnectionList()) {
            Connection conn = (Connection) obj;
            if (conn.getCityTwo().equals(cityTwo) && !conn.equals(this)) {
                conn.setFree(false);
            }
        }
        for (Object obj : gameMap.getCities().get(cityTwo.getName()).getPhysicalConnectionList()) {
            Connection conn = (Connection) obj;
            if (conn.getCityOne().equals(cityOne) && !conn.equals(this)) {
                conn.setFree(false);
            }
        }
    }

    @Override
    public String toString() {
        return "" + getCityOne() + " <--> " + getCityTwo();
        //return owners.toString();
    }

}
