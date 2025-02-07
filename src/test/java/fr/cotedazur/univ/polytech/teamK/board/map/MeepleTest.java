package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MeepleTest {
    Meeple city;
    Meeple player;
    SecureRandom trickedRandom;

    @BeforeEach
    void setUp () {
        Meeple.resetMeeples();
        Player.resetIdCounter();
        trickedRandom = spy(SecureRandom.class);
        player = new Meeple();
    }

    @Test
    void testInit () {
        when(trickedRandom.nextInt(6)).thenReturn(4);
        city = new Meeple(2, trickedRandom);
        assertEquals(2, city.getNumber());
        System.out.println(city.getListOfOwnedMeeples());
        assertArrayEquals(new int[]{0, 0, 0, 0, 2, 0}, city.getListOfOwnedMeeples());
        assertEquals(0, player.getNumber());
        assertArrayEquals(new int[] {0,0,0,0,0,0}, player.getListOfOwnedMeeples());
    }

    @Test
    void testTranserMeeples () {
        when(trickedRandom.nextInt(6)).thenReturn(4).thenReturn(5);
        city = new Meeple(2, trickedRandom);
        assertFalse(player.transferMeeples(city, Colors.RED));
        assertTrue(player.transferMeeples(city, Colors.YELLOW));
        assertTrue(player.transferMeeples(city, Colors.GREEN));
        assertEquals(0, city.getNumber());
        assertArrayEquals(new int[] {0,0,0,0,1,1}, player.getListOfOwnedMeeples());
    }

    @Test
    void testMultipleTransfers() {
        when(trickedRandom.nextInt(6)).thenReturn(0).thenReturn(1);
        city = new Meeple(5, trickedRandom);  // Create a city with 5 meeples
        assertTrue(player.transferMeeples(city, Colors.BLACK));  // Transfer black meeple
        assertTrue(player.transferMeeples(city, Colors.BLUE));  // Transfer blue meeple
        assertArrayEquals(new int[]{1, 1, 0, 0, 0, 0}, player.getListOfOwnedMeeples());
        assertEquals(3, city.getNumber());  // 3 meeples should remain in the city
    }

    @Test
    void testTransferWithNoMeeples() {
        when(trickedRandom.nextInt(6)).thenReturn(4);
        city = new Meeple(2, trickedRandom);
        assertFalse(player.transferMeeples(city, Colors.RED));  // No red meeples in city
        assertFalse(player.transferMeeples(city, Colors.GREEN));  // No green meeples in city
    }


    @Test
    void testGetNumberOfAColorAfterTransfers() {
        when(trickedRandom.nextInt(6)).thenReturn(4).thenReturn(5);
        city = new Meeple(2, trickedRandom);
        player.transferMeeples(city, Colors.YELLOW);  // Transfer yellow meeple
        assertEquals(1, player.getNumberOfAColor(Colors.YELLOW));  // Player should now have 1 yellow meeple
    }

    @Test
    void testToString() {
        when(trickedRandom.nextInt(6)).thenReturn(4);
        city = new Meeple(2, trickedRandom);
        String expectedString = "Black=0, Blue=0, Red=0, White=0, Yellow=2, Green=0";
        assertEquals(expectedString, city.toString());
    }
}