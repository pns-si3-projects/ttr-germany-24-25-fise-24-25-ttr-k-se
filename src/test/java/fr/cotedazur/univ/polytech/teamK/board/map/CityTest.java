package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CityTest {

    private City city;
    private Player player;
    private Board map = new Board("Reich");
    private Random trickedRandom = mock(Random.class);

    @BeforeEach
    void setUp(){
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
    void testGetId(){
        assertEquals(1, city.getId());
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
        assertNull(city.getPlayersThatPickedUpMeeples());
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
    void testTransferMeeples(){
        City city2 = new City("Hamburg", 3);
        assertTrue(city.getMeeples().transferMeeples(city2.getMeeples(), Colors.RED));
        assertEquals(4, city.getMeeples().getNumber());
        assertEquals(2, city2.getMeeples().getNumber());
    }
}