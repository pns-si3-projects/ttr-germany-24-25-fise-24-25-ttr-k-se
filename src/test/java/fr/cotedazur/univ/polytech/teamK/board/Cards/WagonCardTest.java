package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagonCardTest {

    @Test
    void testConstructor() {
        WagonCard card = new WagonCard(Colors.GREEN);
        assertEquals(Colors.GREEN, card.getColor());
    }

    @Test
    void testSetColor() {
        WagonCard card = new WagonCard(Colors.RED);
        card.setColor(Colors.BLUE);
        assertEquals(Colors.BLUE, card.getColor());
    }

    @Test
    void testToString() {
        WagonCard card = new WagonCard(Colors.RAINBOW);
        assertEquals("WAGON RAINBOW", card.toString());
    }

}
