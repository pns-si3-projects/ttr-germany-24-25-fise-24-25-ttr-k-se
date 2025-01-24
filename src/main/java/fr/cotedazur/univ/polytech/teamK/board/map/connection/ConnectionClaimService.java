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
        if (!canClaimConnection(connection, gameMap, numberOfPlayers)) {
            return false;
        }
        claimConnection(connection, player, gameMap);
        return true;
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

    private boolean canClaimConnection(Connection connection, Board gameMap, int numberOfPlayers) {
        int connectionCount = gameMap.countConnectionsBetweenCities(connection.getCityOne(), connection.getCityTwo());
        return !(numberOfPlayers <= 3 && connectionCount > 1);
    }

    private void claimConnection(Connection connection, Player player, Board gameMap) {
        connection.setFree(false);
        connection.setOwner(player);
        int connectionCount = gameMap.countConnectionsBetweenCities(connection.getCityOne(), connection.getCityTwo());
        if (connectionCount > 1) {
            markOtherConnectionsAsClaimed(gameMap, connection.getCityOne(), connection.getCityTwo());
        }
    }
    
    /**
     * Marks other connections between the same cities as claimed.
     *
     * @param gameMap the game map
     * @param cityOne the first city
     * @param cityTwo the second city
     */
    private void markOtherConnectionsAsClaimed(Board gameMap, City cityOne, City cityTwo) {
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
}