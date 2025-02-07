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
}