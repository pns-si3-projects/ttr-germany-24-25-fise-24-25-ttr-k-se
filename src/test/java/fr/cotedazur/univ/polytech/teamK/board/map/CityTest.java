package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
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
/*
    @Test
    void testConstructor(){
        assertEquals("Kiel", city.getName());
        assertEquals(5, city.getMeeples().getNumber());
        assertNotNull(city.getPhysicalConnectionList());
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
    void testGetPhysicalConnectionList(){
        assertNotNull(city.getPhysicalConnectionList());
        assertTrue(city.getPhysicalConnectionList().isEmpty());
    }

    @Test
    void testSetPhysicalConnectionList(){
        city.setPhysicalConnectionList();
        assertNotNull(city.getPhysicalConnectionList());
        assertTrue(city.getPhysicalConnectionList().isEmpty());
    }

    @Test
    void testAddPhysicalConnection(){
        PhysicalConnection connection = new PhysicalConnection(city, new City("Hamburg", 3), 5, Colors.RED);
        city.addPhysicalConnection(connection);
        assertEquals(1, city.getPhysicalConnectionList().size());
        assertEquals(connection, city.getPhysicalConnectionList().get(0));
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
    }*/
}