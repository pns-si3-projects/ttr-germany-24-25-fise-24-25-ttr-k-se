package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionClaimServiceTest {
    private City city1;
    private City city2;
    private Connection connection;
    private Player player;
    private Board gameMap;
    private ConnectionClaimService connectionClaimService;

    @BeforeEach
    void setUp(){
        city1 = new City("Hanover", 4);
        city2 = new City("Magdeburg", 4);
        connection = new Connection(city1, city2, 3, Colors.BLUE);
        player = new Player("Zaynab");
        connectionClaimService = new ConnectionClaimService();
        gameMap = new Board("Reich");
    }

    @Test
    void testClaimAttemptSucess(){
        boolean result = connectionClaimService.claimAttempt(connection, 4, player, gameMap, 4);
        assertTrue(result);
        assertFalse(connection.isFree());
        assertEquals(player, connection.getOwner());
    }

    @Test
    void testClaimAttemptNotEnoughCards() {
        boolean result = connectionClaimService.claimAttempt(connection, 2, player, gameMap, 4);
        assertFalse(result);
        assertTrue(connection.isFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptConnectionNotFree() {
        connection.setFree(false);
        boolean result = connectionClaimService.claimAttempt(connection, 4, player, gameMap, 4);
        assertFalse(result);
        assertFalse(connection.isFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptDoubleConnection() {
        Connection connection2 = new Connection(city1, city2, 3, Colors.RED);
        gameMap.getCity().get(city1.getName()).addConnection(connection);
        gameMap.getCity().get(city1.getName()).addConnection(connection2);
        gameMap.getCity().get(city2.getName()).addConnection(connection);
        gameMap.getCity().get(city2.getName()).addConnection(connection2);

        boolean result = connectionClaimService.claimAttempt(connection, 4, player, gameMap, 3);
        assertTrue(result);
        assertFalse(connection.isFree());
        assertEquals(player, connection.getOwner());
        assertFalse(connection2.isFree());
    }

    @Test
    void testClaimAttemptInvalidNumberOfCards() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            connectionClaimService.claimAttempt(connection, -1, player, gameMap, 4);
        });
        assertEquals("Number of Cards Used must be greater than 0", exception.getMessage());
    }
}