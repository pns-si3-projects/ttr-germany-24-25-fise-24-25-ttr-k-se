package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class ConnectionsTest {

    Cities city1 = Cities.AUGSBURG;
    Cities city2 = Cities.HANNOVER;
    Connection connectionStandard = new Connection(city1, city2, 4, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));
    private Player player;
    //Connections connectionTooShort = new Connections(city1, city2, -2, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));
    //Connections connectionSameCity = new Connections(city1, city1, 4, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));

    @Test
    void testConstructor() {
        assertEquals(city1, connectionStandard.getStartCity());
        assertEquals(city2, connectionStandard.getEndCity());
        assertEquals(3, connectionStandard.getLength());
        assertEquals(3, connectionStandard.getWidth());
    }

    @Test
    void testGetLength() {
        assertEquals(4, connectionStandard.getLength());
    }


    @Test
    void testIsFreeColor() {
        assertEquals(0, connectionStandard.isFreeColor(Colors.BLACK));
        assertEquals(1, connectionStandard.isFreeColor(Colors.PINK));
        assertEquals(-1, connectionStandard.isFreeColor(Colors.BLUE));

    }

    @Test
    void testIsBuyable() {
        Player John = new Player("John");
        //try to buy with weird stuff, should fail
        assertFalse(connectionStandard.claimAttempt(Colors.BLUE, 3, John));
        assertFalse(connectionStandard.claimAttempt(Colors.BLACK, 3, John));
        assertFalse(connectionStandard.claimAttempt(Colors.BLACK, -2, John));
        //try to buy, should succeed
        assertTrue(connectionStandard.claimAttempt(Colors.BLACK, 5, John));
        //test if properly done after purchase
        assertEquals(2, connectionStandard.isFreeColor(Colors.BLACK));
        //try to buy a new one, should succeed
        assertTrue(connectionStandard.claimAttempt(Colors.BLACK, 5, John));
        //try to buy a third one, should fail
        assertFalse(connectionStandard.claimAttempt(Colors.BLACK, 5, John));
        //try to buy the pink one
        assertTrue(connectionStandard.claimAttempt(Colors.PINK, 5, John));
        //all of them should be purchased, test to see what happens when i try to purchase another one
        assertFalse(connectionStandard.claimAttempt(Colors.BLUE, 5, John));

    }

    @Test
    void testScoreAfterClaimAttempt(){
        player = new Player("Zaynab");
        assertTrue(connectionStandard.claimAttempt(Colors.RED, 3, player));
        assertEquals(4, player.getScore()); // a 3-length route gives you 4 points
    }
}
