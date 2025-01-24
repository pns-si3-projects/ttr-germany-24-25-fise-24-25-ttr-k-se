package fr.cotedazur.univ.polytech.teamK.board.cards;


import fr.cotedazur.univ.polytech.teamK.board.map.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationCardTest {
    City cities1;
    City cities2;
    City cities3;
    City cities4;

    @BeforeEach
    void setUp() {
        cities1 = new City("Danemark", 1);
        cities2 = new City("Kiel", 1);
       cities3 =  new City("Rostock", 1);
        cities4 = new City("Emden", 1);
    }

    @Test
    void testConstructor() {
        DestinationCard card = new DestinationCard(cities1,cities2, 10);
        DestinationCard cardLong = new DestinationCard(cities3, cities4, 20);
        assertEquals(cities1, card.getStartCity());
        assertEquals(cities2, card.getEndCity());
        assertEquals(10, card.getValue());
        assertEquals(TypeOfCards.SHORT_DESTINATION, card.getType());
        assertEquals(20, cardLong.getValue());
        assertEquals(TypeOfCards.LONG_DESTINATION, cardLong.getType());
        assertFalse(card.isComplete());
        cardLong.setComplete();
        assertTrue(cardLong.isComplete());
    }

    @Test
    void testEqual () {
        DestinationCard card = new DestinationCard(cities1,cities2, 10);
        DestinationCard card2 = new DestinationCard(cities1,cities2, 10);
        assertEquals(card, card2);
    }

    @Test
    void testToString() {
        DestinationCard card = new DestinationCard(cities2, cities4, 10);
        assertEquals("SHORT_DESTINATION Kiel->Emden (10 points)", card.toString());
    }
}
