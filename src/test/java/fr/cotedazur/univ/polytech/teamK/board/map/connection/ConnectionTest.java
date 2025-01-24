package fr.cotedazur.univ.polytech.teamK.board.map.connection;


import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    private City cityOne;
    private City cityTwo;
    private Connection connection;
    private Player player;
    private Board gameMap;

    @BeforeEach
    void setup(){
        cityOne = new City("Rannover", 1);
        cityTwo = new City("Berlin", 2);
        connection = new Connection(cityOne, cityTwo);
        player = new Player("Deyann");
        gameMap = new Board("Reich");
    }

    @Test
    void testConstructorAndGetters(){
        assertEquals(cityOne, connection.getCityOne());
        assertEquals(cityTwo, connection.getCityTwo());
        assertEquals(3, connection.getLength());
        assertEquals(Colors.RED, connection.getColor());
        assertTrue(connection.isFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testSetFree() {
        connection.setFree(false);
        assertFalse(connection.isFree());
    }

    @Test
    void testSetOwner() {
        connection.setOwner(player);
        assertEquals(player, connection.getOwner());
    }

    @Test
    void testClaimAttemptSuccess() {
        boolean result = connection.claimAttempt(4, player, gameMap, 4);
        assertTrue(result);
        assertFalse(connection.isFree());
        assertEquals(player, connection.getOwner());
    }

    @Test
    void testClaimAttemptNotEnoughCards() {
        boolean result = connection.claimAttempt(2, player, gameMap, 4);
        assertFalse(result);
        assertTrue(connection.isFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptConnectionNotFree() {
        connection.setFree(false);
        boolean result = connection.claimAttempt(4, player, gameMap, 4);
        assertFalse(result);
        assertFalse(connection.isFree());
        assertNull(connection.getOwner());
    }

    @Test
    void testClaimAttemptDoubleConnection() {
        Connection connection2 = new Connection(cityOne, cityTwo, 3, Colors.BLUE);
        gameMap.getCity().get(cityOne.getName()).addConnection(connection);
        gameMap.getCity().get(cityOne.getName()).addConnection(connection2);
        gameMap.getCity().get(cityTwo.getName()).addConnection(connection);
        gameMap.getCity().get(cityTwo.getName()).addConnection(connection2);

        boolean result = connection.claimAttempt(4, player, gameMap, 3);
        assertTrue(result);
        assertFalse(connection.isFree());
        assertEquals(player, connection.getOwner());
        assertFalse(connection2.isFree());
    }

    @Test
    void testCalculatePoints() {
        int points = connection.calculatePoints(connection.getLength());
        assertEquals(4, points); // connection de longueur 3 donne 4 points
    }

    @Test
    void testToString() {
        String expected = "CityOne connected to CityTwo";
        assertEquals(expected, connection.toString());
    }

}
