package fr.cotedazur.univ.polytech.teamK.board.Cards;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WagonCardsTest {
    private WagonCards wagonCards;

    @BeforeEach
    public void setUp() {
        List<Colors> cards = new ArrayList<>();
        wagonCards = new WagonCards("Standard", "Blue", cards);
    }

    @Test
    public void testInitializeDeck() {
        assertEquals(110, wagonCards.getRemainingCards());
    }

    @Test
    public void testDrawCard() {
        Colors drawnCard = wagonCards.drawCard();
        assertNotNull(drawnCard);
        assertEquals(109, wagonCards.getRemainingCards());
    }

    @Test
    public void testAddCard() {
        Colors card = Colors.BLUE;
        wagonCards.addCard(card);
        assertEquals(111, wagonCards.getRemainingCards());
    }

    @Test
    public void testReturnCard() {
        Colors card = Colors.RED;
        wagonCards.returnCard(card);
        assertEquals(111, wagonCards.getRemainingCards());
    }

    @Test
    public void testGetRemainingCards() {
        assertEquals(110, wagonCards.getRemainingCards());
        wagonCards.drawCard();
        assertEquals(109, wagonCards.getRemainingCards());
    }
}