package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class ConnectionsTest {

    Cities city1 = Cities.AUGSBURG;
    Cities city2 = Cities.HANNOVER;
    Connections connectionStandard = new Connections(city1, city2, 4, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));
    //Connections connectionTooShort = new Connections(city1, city2, -2, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));
    //Connections connectionSameCity = new Connections(city1, city1, 4, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));


    @Test
    void getLength() {
        Assertions.assertEquals(4, connectionStandard.getLength());
    }


    @Test
    void isFreeColor() {
        Assertions.assertEquals(0, connectionStandard.isFreeColor(Colors.BLACK));
        Assertions.assertEquals(1, connectionStandard.isFreeColor(Colors.PINK));
        Assertions.assertEquals(-1, connectionStandard.isFreeColor(Colors.BLUE));

    }

    @Test
    void isBuyable() {
        Player John = new Player("John");
        //try to buy with weird stuff, should fail
        Assertions.assertFalse(connectionStandard.claimAttempt(Colors.BLUE, 3, John));
        Assertions.assertFalse(connectionStandard.claimAttempt(Colors.BLACK, 3, John));
        Assertions.assertFalse(connectionStandard.claimAttempt(Colors.BLACK, -2, John));
        //try to buy, should succeed
        Assertions.assertTrue(connectionStandard.claimAttempt(Colors.BLACK, 5, John));
        //test if properly done after purchase
        Assertions.assertEquals(2, connectionStandard.isFreeColor(Colors.BLACK));
        //try to buy a new one, should succeed
        Assertions.assertTrue(connectionStandard.claimAttempt(Colors.BLACK, 5, John));
        //try to buy a third one, should fail
        Assertions.assertFalse(connectionStandard.claimAttempt(Colors.BLACK, 5, John));
        //try to buy the pink one
        Assertions.assertTrue(connectionStandard.claimAttempt(Colors.PINK, 5, John));
        //all of them should be purchased, test to see what happens when i try to purchase another one
        Assertions.assertFalse(connectionStandard.claimAttempt(Colors.BLUE, 5, John));

    }
}
