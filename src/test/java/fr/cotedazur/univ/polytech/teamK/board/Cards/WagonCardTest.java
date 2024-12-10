package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagonCardTest {

    @Test
    void testConstructor() {
        WagonCard card = new WagonCard(1, Colors.GREEN);
        assertEquals(1, card.getId());
        assertEquals(Colors.GREEN, card.getColor());
    }

    @Test
    void testSetColor() {
        WagonCard card = new WagonCard(1, Colors.RED);
        card.setColor(Colors.BLUE);
        assertEquals(Colors.BLUE, card.getColor());
    }

    @Test
    void testToString() {
        WagonCard card = new WagonCard(1, Colors.RAINBOW);
        assertEquals("WAGON RAINBOW", card.toString());
    }

    @Test
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new WagonCard(0, Colors.RED));
        assertThrows(IllegalArgumentException.class, () -> new WagonCard(1, null));
    }
}
