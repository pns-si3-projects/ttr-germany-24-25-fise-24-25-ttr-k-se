package fr.cotedazur.univ.polytech.teamK.board.Cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import fr.cotedazur.univ.polytech.teamK.board.Cards.*;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;

class DeckTest {

    private Deck<DestinationCard> destinationDeck;
    private Deck<WagonCard> wagonDeck;

    @BeforeEach
    void setUp() {
        // Initialisation des paquets avant chaque test
        destinationDeck = new Deck<>(TypeOfCards.DESTINATION);
        wagonDeck = new Deck<>(TypeOfCards.WAGON);
    }

    @Test
    void testInitializeDestinationDeck() {
        assertNotNull(destinationDeck);
        assertEquals(89,destinationDeck.getRemainingCards());
    }

    @Test
    void testInitializeWagonDeck() {
        assertNotNull(wagonDeck);
        assertEquals(110,wagonDeck.getRemainingCards());
    }


    @Test
    void testDrawCard() {
        Deck<DestinationCard> deck = new Deck<>(TypeOfCards.DESTINATION);
        int remainingBeforeDraw = deck.getRemainingCards();

        DestinationCard drawnCard = deck.draw();
        int remainingAfterDraw = deck.getRemainingCards();

        assertNotNull(drawnCard);
        assertEquals(remainingBeforeDraw - 1, remainingAfterDraw);
    }

    @Test
    void testAddCard() {
        Deck<DestinationCard> deck = new Deck<>(TypeOfCards.DESTINATION);
        int remainingBeforeAdd = deck.getRemainingCards();

        deck.addCard(new DestinationCard(Cities.BERLIN, Cities.MUNCHEN, 7));
        int remainingAfterAdd = deck.getRemainingCards();

        assertEquals(remainingBeforeAdd + 1, remainingAfterAdd);
    }

    @Test
    void testEmptyDeck() {
        // Vérifier que le paquet est vide après avoir tiré toutes les cartes
        Deck<DestinationCard> deck = new Deck<>(TypeOfCards.DESTINATION);
        while (deck.getRemainingCards() > 0) {
            deck.draw();
        }

        // Le paquet devrait être vide
        assertEquals(0, deck.getRemainingCards());
        assertNull(deck.draw()); // Aucun élément à tirer
    }
}
