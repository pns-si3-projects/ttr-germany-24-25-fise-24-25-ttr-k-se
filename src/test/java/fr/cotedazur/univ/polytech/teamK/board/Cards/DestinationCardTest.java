package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationCardTest {

    @Test
    void testConstructor() {
        DestinationCard card = new DestinationCard(1, Cities.HAMBURG, Cities.BERLIN, 10);
        assertEquals(1, card.getId());
        assertEquals(Cities.HAMBURG, card.getStartCity());
        assertEquals(Cities.BERLIN, card.getEndCity());
        assertEquals(10, card.getValue());
        assertFalse(card.isComplete());
    }

    @Test
    void testToString() {
        DestinationCard card = new DestinationCard(1, Cities.HAMBURG, Cities.BERLIN, 10);
        assertEquals("DESTINATION HAMBURG->BERLIN (10 points)", card.toString());
    }

    @Test
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new DestinationCard(0, Cities.HAMBURG, Cities.BERLIN, 10));
        assertThrows(IllegalArgumentException.class, () -> new DestinationCard(1, null, Cities.BERLIN, 10));
        assertThrows(IllegalArgumentException.class, () -> new DestinationCard(1, Cities.HAMBURG, null, 10));
        assertThrows(IllegalArgumentException.class, () -> new DestinationCard(1, Cities.HAMBURG, Cities.BERLIN, 0));
    }
}
