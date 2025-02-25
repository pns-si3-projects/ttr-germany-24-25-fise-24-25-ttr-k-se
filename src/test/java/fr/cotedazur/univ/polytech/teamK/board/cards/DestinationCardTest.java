package fr.cotedazur.univ.polytech.teamK.board.cards;


import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
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
        Meeple.resetMeeples();
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

    @Test
    void testHashCode() {
        DestinationCard card1 = new DestinationCard(cities1, cities2, 10);
        DestinationCard card2 = new DestinationCard(cities1, cities2, 10);
        assertEquals(card1.hashCode(), card2.hashCode());
    }

    @Test
    void testEqualsDifferentValues() {
        DestinationCard card1 = new DestinationCard(cities1, cities2, 10);
        DestinationCard card2 = new DestinationCard(cities1, cities3, 10); // Different end city
        DestinationCard card3 = new DestinationCard(cities1, cities2, 15); // Different value
        DestinationCard card4 = new DestinationCard(cities3, cities4, 10); // Different start city

        assertNotEquals(card1, card2);
        assertNotEquals(card1, card3);
        assertNotEquals(card1, card4);
    }

    @Test
    void testSetComplete() {
        DestinationCard card = new DestinationCard(cities1, cities2, 10);
        assertFalse(card.isComplete()); // Initially, the card is not complete
        card.setComplete();
        assertTrue(card.isComplete()); // After calling setComplete, the card should be complete
    }

    @Test
    void testToStringLongDestination() {
        DestinationCard cardLong = new DestinationCard(cities3, cities4, 20);
        assertEquals("LONG_DESTINATION Rostock->Emden (20 points)", cardLong.toString());
    }

    @Test
    void testTypeOfCards() {
        DestinationCard cardShort = new DestinationCard(cities1, cities2, 5);
        DestinationCard cardLong = new DestinationCard(cities3, cities4, 15);

        assertEquals(TypeOfCards.SHORT_DESTINATION, cardShort.getType());
        assertEquals(TypeOfCards.LONG_DESTINATION, cardLong.getType());
    }


}
