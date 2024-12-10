package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck<WagonCard> deck;
    private WagonCard card1;
    private WagonCard card2;

    @BeforeEach
    void setUp() {
        deck = new Deck<>();
        card1 = new WagonCard(1, Colors.BLUE);
        card2 = new WagonCard(2, Colors.RED);
    }

    @Test
    void testInitialize() {
        List<WagonCard> initialCards = List.of(card1, card2);
        deck.initialize(initialCards);
        assertEquals(2, deck.getRemainingCards());
    }

    @Test
    void testShuffle() {
        List<WagonCard> initialCards = List.of(card1, card2);
        deck.initialize(initialCards);
        deck.shuffle();
        assertEquals(2, deck.getRemainingCards());
    }

    @Test
    void testDraw() {
        deck.addCard(card1);
        deck.addCard(card2);
        WagonCard drawnCard = deck.draw();
        assertNotNull(drawnCard);
        assertEquals(1, deck.getRemainingCards());
    }

    @Test
    void testAddCard() {
        deck.addCard(card1);
        assertEquals(1, deck.getRemainingCards());
    }
}
