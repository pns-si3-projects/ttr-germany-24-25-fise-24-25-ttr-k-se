package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CityTest {

    private City city;
    private Player player;
    private GameBoard map = new GameBoard("Reich");
    private SecureRandom trickedRandom = mock(SecureRandom.class);

    @BeforeEach
    void setUp(){
        Meeple.resetMeeples();
        when(trickedRandom.nextInt(6)).thenReturn(4);
        city = new City("Kiel", 5, trickedRandom, false);
        player = new Player("DaTutelBoss");
    }

    @Test
    void testConstructor(){
        assertEquals("Kiel", city.getName());
        assertEquals(5, city.getMeeples().getNumber());
        assertNotNull(city.getConnectionList());
    }

    @Test
    void testGetName(){
        assertEquals("Kiel", city.getName());
    }

    @Test
    void testGetMeeples(){
        assertEquals(5, city.getMeeples().getNumber());
    }

    @Test
    void testGetConnectionList(){
        assertNotNull(city.getConnectionList());
        assertTrue(city.getConnectionList().isEmpty());
    }

    @Test
    void testSetConnectionList(){
        city.setConnectionList();
        assertNotNull(city.getConnectionList());
        assertTrue(city.getConnectionList().isEmpty());
    }

    @Test
    void testAddConnection(){
        Connection connection = new Connection(city, new City("Hamburg", 3), 5, Colors.RED);
        city.addConnection(connection);
        assertEquals(1, city.getConnectionList().size());
        assertEquals(connection, city.getConnectionList().get(0));
    }

    @Test
    void testGetPlayersThatPickedUpMeeples(){
        assertTrue(city.getPlayersThatPickedUpMeeples().isEmpty());
        player.takeMeeples(city, Colors.YELLOW);
        assertNotNull(city.getPlayersThatPickedUpMeeples());
    }

    @Test
    void testAddPlayer(){
        city.addPlayer(player);
        assertTrue(city.getPlayersThatPickedUpMeeples().contains(player));
    }

    @Test
    void testCoutry(){
        assertFalse(city.isCountry());
        City countryCity = new City("Berlin", 5, true);
        assertTrue(countryCity.isCountry());
    }

    @Test
    void testAddPlayerWhenListAlreadyInitialized() {
        city.addPlayer(player);  // Adding player to an initialized list
        assertTrue(city.getPlayersThatPickedUpMeeples().contains(player));
    }

    @Test
    void testAddMultipleConnections() {
        City anotherCity = new City("Hamburg", 3, trickedRandom, false);
        Connection connection1 = new Connection(city, anotherCity, 5, Colors.RED);
        Connection connection2 = new Connection(anotherCity, city, 3, Colors.BLUE);

        city.addConnection(connection1);
        city.addConnection(connection2);

        assertEquals(2, city.getConnectionList().size());
        assertTrue(city.getConnectionList().contains(connection1));
        assertTrue(city.getConnectionList().contains(connection2));
    }

    @Test
    void testSetName() {
        city.setName("NewCity");
        assertEquals("NewCity", city.getName());
    }

    @Test
    void testAddPlayerWhenListIsNull() {
        City cityWithNullList = new City("Munich", 5, trickedRandom, false);
        cityWithNullList.addPlayer(player);
        assertTrue(cityWithNullList.getPlayersThatPickedUpMeeples().contains(player));
    }

}