package fr.cotedazur.univ.polytech.teamK.board.map.connection;


import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    private City cityOne;
    private City cityTwo;
    private Connection connection;
    private Player player;
    private Board gameMap;

    @BeforeEach
    void setup(){
        Meeple.resetMeeples();
        player = new Player("Deyann");
        gameMap = new Board("Reich");
        cityTwo = gameMap.getCity("Hannover");
        cityOne = gameMap.getCity("Berlin");
        connection = gameMap.getNeighbourConnection(cityOne,cityTwo);
    }

    @Test
    void testConstructorAndGetters(){
        assertEquals(cityOne, connection.getCityOne());
        assertEquals(cityTwo, connection.getCityTwo());
        assertEquals(7, connection.getLength());
        assertEquals(Colors.YELLOW, connection.getColor());
        assertTrue(connection.getIsFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testSetFree() {
        connection.setFree(false);
        assertFalse(connection.getIsFree());
    }

    @Test
    void testSetOwner() {
        connection.setOwner(player);
        assertEquals(player, connection.getOwner());
    }

    @Test
    void testClaimAttemptSuccess() {
        boolean result = connection.claimAttempt(7, player, gameMap, 4);
        assertTrue(result);
        assertFalse(connection.getIsFree());
        assertEquals(player, connection.getOwner());
    }

    @Test
    void testClaimAttemptNotEnoughCards() {
        boolean result = connection.claimAttempt(2, player, gameMap, 4);
        assertFalse(result);
        assertTrue(connection.getIsFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptConnectionNotFree() {
        connection.setFree(false);
        boolean result = connection.claimAttempt(4, player, gameMap, 4);
        assertFalse(result);
        assertFalse(connection.getIsFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptDoubleConnection() {
        Connection connection2 = gameMap.getNeighbourConnection(cityOne, cityTwo);
        System.out.println(cityOne.getName());
        gameMap.getCity().get(cityOne.getName()).addConnection(connection);
        gameMap.getCity().get(cityOne.getName()).addConnection(connection2);
        gameMap.getCity().get(cityTwo.getName()).addConnection(connection);
        gameMap.getCity().get(cityTwo.getName()).addConnection(connection2);

        boolean result = connection.claimAttempt(7, player, gameMap, 5);
        assertTrue(result);
        assertFalse(connection.getIsFree());
        assertEquals(player, connection.getOwner());
        assertFalse(connection2.getIsFree());
    }

    @Test
    void testCalculatePoints() {
        int points = connection.calculatePoints(connection.getLength());
        assertEquals(18, points); // connection de longueur 7 donne 18 points
    }

    @Test
    void testToString() {
        String expected = "Berlin connected to Hannover";
        System.out.println(connection.toString());
        assertEquals(expected, connection.toString());
    }

}
