package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private Board map;

    @BeforeEach
    void setUp() {
        // Initialisation des paquets avant chaque test
        map = new Board("Reich");
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
        assertEquals(106,wagonDeck.getRemainingCards());
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
    void testDrawCardFromEmptyDeck(){
        while(shortDestinationDeck.getRemainingCards() > 0){
            shortDestinationDeck.draw();
        }
        assertNull(shortDestinationDeck.draw());
    }

    @Test
    void testAddCard() throws PaquetPleinException {
        DestinationCard lastCard = longDestinationDeck.draw();
        assertEquals(33, longDestinationDeck.getRemainingCards());
        longDestinationDeck.addCard(lastCard);
        assertEquals(34, longDestinationDeck.getRemainingCards());
    }

    @Test
    void testAddCardToFullDeck() {
        assertEquals(34, longDestinationDeck.getRemainingCards());
        DestinationCard card = new DestinationCard(map.getCity("Berlin"), map.getCity("Munchen"), 10);
        assertThrows(PaquetPleinException.class, () -> longDestinationDeck.addCard(card));
    }

    @Test
    void testDrawVisibleCardWithInvalidIndex(){
        assertThrows(IllegalArgumentException.class, ()->wagonDeck.drawVisibleCard(5));
    }

    @Test
    void testDrawVisibleCard(){
        WagonCard drawnCard = wagonDeck.drawVisibleCard(0);
        assertNotNull(drawnCard);
        assertEquals(105, wagonDeck.getRemainingCards());
        assertEquals(4, wagonDeck.getVisibleCard().size());
    }

    @Test
    void testShuffleDeck() {
        Deck<WagonCard> deck = new Deck<>(TypeOfCards.WAGON, map);
        deck.shuffle();
        assertEquals(106, deck.getRemainingCards());
    }

}
