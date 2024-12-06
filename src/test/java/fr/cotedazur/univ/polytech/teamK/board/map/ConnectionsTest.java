package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class ConnectionsTest {

    Cities city1 = Cities.AUSBURG;
    Cities city2 = Cities.HANNOVER;
    Connections connectionStandard = new Connections(city1, city2, 4, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));
    //Connections connectionTooShort = new Connections(city1, city2, -2, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));
    //Connections connectionSameCity = new Connections(city1, city1, 4, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)));


    @Test
    void getLength() {
        Assertions.assertEquals(4, connectionStandard.getLength());
    }

    @Test
    void getFreeRails() {
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK, Colors.BLACK)), connectionStandard.getFreeRails());

    }

    @Test
    void isFreeColor() {
        Assertions.assertEquals(0, connectionStandard.isFreeColor(Colors.BLACK));
        Assertions.assertEquals(1, connectionStandard.isFreeColor(Colors.PINK));
        Assertions.assertEquals(-1, connectionStandard.isFreeColor(Colors.BLUE));

    }

    @Test
    void buyRail() {
        //try to buy with weird stuff, should fail
        Assertions.assertEquals(false, connectionStandard.buyRail(Colors.BLUE, 3, "John"));
        Assertions.assertEquals(false, connectionStandard.buyRail(Colors.BLACK, 3, "John"));
        Assertions.assertEquals(false, connectionStandard.buyRail(Colors.BLACK, -2, "John"));
        //try to buy, should succeed
        Assertions.assertEquals(true, connectionStandard.buyRail(Colors.BLACK, 5, "John"));
        //test if properly done after purchase
        Assertions.assertEquals(2, connectionStandard.isFreeColor(Colors.BLACK));
        //try to buy a new one, should succeed
        Assertions.assertEquals(true, connectionStandard.buyRail(Colors.BLACK, 5, "John"));
        //try to buy a third one, should fail
        Assertions.assertEquals(false, connectionStandard.buyRail(Colors.BLACK, 5, "John"));
        //try to buy the pink one
        Assertions.assertEquals(true, connectionStandard.buyRail(Colors.PINK, 5, "John"));
        //all of them should be purchased, test to see what happens when i try to purchase another one
        Assertions.assertEquals(false, connectionStandard.buyRail(Colors.BLUE, 5, "John"));

    }
}