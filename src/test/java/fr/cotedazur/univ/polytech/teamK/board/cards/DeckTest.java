package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private GameBoard map;

    @BeforeEach
    void setUp() {
        Meeple.resetMeeples();
        // Initialisation des paquets avant chaque test
        map = new GameBoard("Reich");
        shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION,map);
        longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, map);
        wagonDeck = new Deck<>(TypeOfCards.WAGON, map);
    }

    @Test
    void testInitializeDestinationDeck() {
        assertNotNull(shortDestinationDeck);
        assertFalse(shortDestinationDeck.isEmpty());
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
        assertThrows(DeckEmptyException.class, () -> shortDestinationDeck.draw());
    }

    @Test
    void testAddCard() throws DeckFullException {
        DestinationCard lastCard = longDestinationDeck.draw();
        assertEquals(33, longDestinationDeck.getRemainingCards());
        longDestinationDeck.addCard(lastCard);
        assertEquals(34, longDestinationDeck.getRemainingCards());
    }

    @Test
    void testAddCardToFullDeck() {
        assertEquals(34, longDestinationDeck.getRemainingCards());
        DestinationCard card = new DestinationCard(map.getCity("Berlin"), map.getCity("Munchen"), 10);
        assertThrows(DeckFullException.class, () -> longDestinationDeck.addCard(card));
    }

    @Test
    void testDrawVisibleCardWithInvalidIndex(){
        assertThrows(IllegalArgumentException.class, ()->wagonDeck.drawVisibleCard(5));
    }

    @Test
    void testDrawVisibleCard() throws IllegalArgumentException {
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
