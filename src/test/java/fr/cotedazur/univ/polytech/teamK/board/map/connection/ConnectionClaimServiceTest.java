package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionClaimServiceTest {
    private City city1;
    private City city2;
    private Connection connection;
    private Player player;
    private GameBoard gameMap;

    @BeforeEach
    void setUp(){
        Meeple.resetMeeples();
        city1 = new City("Nurnberg", 1);
        city2 = new City("Regensburg", 1);
        connection = new Connection(city1, city2, 3, Colors.GREEN);
        player = new Player("Zaynab");
        gameMap = new GameBoard("Reich");
    }

    @Test
    void testClaimAttemptSucess(){
        boolean result = ConnectionClaimService.claimAttempt(connection, 4, player, gameMap, 4);
        assertTrue(result);
        assertFalse(connection.getIsFree());
        assertEquals(player, connection.getOwner());
    }

    @Test
    void testClaimAttemptNotEnoughCards() {
        boolean result = ConnectionClaimService.claimAttempt(connection, 2, player, gameMap, 4);
        assertFalse(result);
        assertTrue(connection.getIsFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptConnectionNotFree() {
        connection.setFree(false);
        boolean result = ConnectionClaimService.claimAttempt(connection, 4, player, gameMap, 4);
        assertFalse(result);
        assertFalse(connection.getIsFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptDoubleConnection() {
        // 2 cities with double connections between them
        City kiel = new City("Kiel", 1);
        City hamburg = new City("Hamburg", 1);
        Connection connection1 = new Connection(kiel, hamburg, 2, Colors.BLACK);
        Connection connection2 = new Connection(kiel, hamburg, 2, Colors.PINK);
        boolean result = ConnectionClaimService.claimAttempt(connection2, 2, player, gameMap, 3);
        assertTrue(result);
        assertFalse(connection2.getIsFree());
        assertEquals(player, connection2.getOwner());
        assertFalse(connection2.getIsFree());
        assertTrue(connection1.getIsFree());
    }

    @Test
    void testClaimAttemptInvalidNumberOfCards() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ConnectionClaimService.claimAttempt(connection, -1, player, gameMap, 4);
        });
        assertEquals("Number of Cards Used must be greater than 0", exception.getMessage());
    }
}