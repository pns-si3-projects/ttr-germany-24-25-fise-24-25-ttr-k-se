package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private Board map = new Board("Reich");

    @BeforeEach
    void setUp() {
        // Initialisation des paquets avant chaque test
        shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION,map);
        longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, map);
        wagonDeck = new Deck<>(TypeOfCards.WAGON, map);
    }

    @Test
    void testInitializeDestinationDeck() {
        assertNotNull(shortDestinationDeck);
        assertEquals(55, shortDestinationDeck.getRemainingCards());
        assertEquals(34, longDestinationDeck.getRemainingCards());
    }

    @Test
    void testInitializeWagonDeck() {
        assertNotNull(wagonDeck);
        assertEquals(110,wagonDeck.getRemainingCards());
    }


    @Test
    void testDrawCard() {
        int remainingBeforeDraw = shortDestinationDeck.getRemainingCards();

        DestinationCard drawnCard = shortDestinationDeck.draw();
        int remainingAfterDraw = shortDestinationDeck.getRemainingCards();

        assertNotNull(drawnCard);
        assertEquals(remainingBeforeDraw - 1, remainingAfterDraw);
    }

    @Test
    void testAddCard() throws PaquetPleinException {
        DestinationCard lastCard = longDestinationDeck.draw();
        assertEquals(33, longDestinationDeck.getRemainingCards());
        longDestinationDeck.addCard(lastCard);
        assertEquals(34, longDestinationDeck.getRemainingCards());
    }

    @Test
    void testEmptyDeck() {
        // Vérifier que le paquet est vide après avoir tiré toutes les cartes
        while (longDestinationDeck.getRemainingCards() > 0) {
            longDestinationDeck.draw();
        }

        // Le paquet devrait être vide
        assertNull(longDestinationDeck.draw()); // Aucun élément à tirer
    }
}
