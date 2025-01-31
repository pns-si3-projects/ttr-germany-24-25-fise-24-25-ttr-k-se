package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;

public class ConnectionClaimService {
    /**
     * Attempts to claim the connection.
     *
     * @param connection        the connection to be claimed
     * @param numberOfCardsUsed the number of cards used to claim the connection
     * @param player            the player attempting to claim the connection
     * @param gameMap           the game map
     * @param numberOfPlayers   the number of players in the game
     * @return true if the connection is successfully claimed, false otherwise
     * @throws IllegalArgumentException if the number of cards used is less than 0
     */
    public boolean claimAttempt(Connection connection, Integer numberOfCardsUsed, Player player, Board gameMap, int numberOfPlayers) {
        validateNumberOfCards(numberOfCardsUsed);
        if (!hasEnoughCards(numberOfCardsUsed, connection)) {
            return false;
        }
        if (!isConnectionFree(connection)) {
            return false;
        }
        if (numberOfPlayers > 3) {
            claimConnection(connection, player, gameMap);
            return true;
        }else{
            claimConnection(connection, player, gameMap);
            markOtherConnectionsAsClaimed(gameMap, connection.getCityOne(), connection.getCityTwo(), connection);
            return true;
        }
    }

    private void validateNumberOfCards(Integer numberOfCardsUsed) {
        if (numberOfCardsUsed < 0) {
            throw new IllegalArgumentException("Number of Cards Used must be greater than 0");
        }
    }

    private boolean hasEnoughCards(Integer numberOfCardsUsed, Connection connection) {
        return numberOfCardsUsed >= connection.getLength();
    }

    private boolean isConnectionFree(Connection connection) {
        return connection.isFree();
    }

    private void claimConnection(Connection connection, Player player, Board gameMap) {
        connection.setFree(false);
        connection.setOwner(player);
    }

    /**
     * Marks other connections between the same cities as claimed.
     *
     * @param gameMap the game map
     * @param cityOne the first city
     * @param cityTwo the second city
     */
    private void markOtherConnectionsAsClaimed(Board gameMap, City cityOne, City cityTwo, Connection claimedConnection) {
        for (Connection conn : gameMap.getCitiesConnections(cityOne.getName())) {
            if (conn.getCityTwo().equals(cityTwo) && !conn.equals(claimedConnection)) {
                conn.setFree(false);
            }
        }
        for (Connection conn : gameMap.getCitiesConnections(cityTwo.getName())) {
            if (conn.getCityOne().equals(cityOne) && !conn.equals(claimedConnection)) {
                conn.setFree(false);
            }

        }
    }
}