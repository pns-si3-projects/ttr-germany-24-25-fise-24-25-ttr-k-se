package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;

public class ConnectionClaimService {
    private ConnectionClaimService () {}

    /**
     * Attempts to claim the connection.
     * @param connection        the connection to be claimed
     * @param numberOfCardsUsed the number of cards used to claim the connection
     * @param player            the player attempting to claim the connection
     * @param gameMap           the game map
     * @param numberOfPlayers   the number of players in the game
     * @return true if the connection is successfully claimed, false otherwise
     * @throws IllegalArgumentException if the number of cards used is less than 0
     */
    public static boolean claimAttempt(Connection connection, Integer numberOfCardsUsed, Player player, GameBoard gameMap, int numberOfPlayers) {
        validateNumberOfCards(numberOfCardsUsed);
        if (!hasEnoughCards(numberOfCardsUsed, connection)) {
            return false;
        }
        if (!isConnectionFree(connection)) {
            return false;
        }
        if (numberOfPlayers > 3) {
            claimConnection(connection, player);
            return true;
        }else{
            claimConnection(connection, player);
            markOtherConnectionsAsClaimed(gameMap, connection.getCityOne(), connection.getCityTwo(), connection);
            return true;
        }
    }

    /**
     * check if the number of cards is normal
     * @param numberOfCardsUsed the number of cards
     */
    private static void validateNumberOfCards(Integer numberOfCardsUsed) {
        if (numberOfCardsUsed < 0) {
            throw new IllegalArgumentException("Number of Cards Used must be greater than 0");
        }
    }

    /**
     * check if a number of cards is enoough to buy a connection
     * @param numberOfCardsUsed the number we have
     * @param connection the connection we want to check
     * @return true if the number is enough, false otherwise
     */
    private static boolean hasEnoughCards(Integer numberOfCardsUsed, Connection connection) {
        return numberOfCardsUsed >= connection.getLength();
    }

    /**
     * check if a connection is free
     * @param connection the connection to check
     * @return true if the connection is free, false otherwise
     */
    private static boolean isConnectionFree(Connection connection) {
        return connection.getIsFree();
    }

    /**
     * Claim a connection
     * @param connection the connection to claim
     * @param player the player who claim it
     */
    private static void claimConnection(Connection connection, Player player) {
        connection.setFree(false);
        connection.setOwner(player);
    }

    /**
     * Marks other connections between the same cities as claimed.
     * @param gameMap the game map
     * @param cityOne the first city
     * @param cityTwo the second city
     */
    private static void markOtherConnectionsAsClaimed(GameBoard gameMap, City cityOne, City cityTwo, Connection claimedConnection) {
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