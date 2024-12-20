package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player("Deyann");

    @Test
    void testInit () {
        Player player2 = new Player("Tom");
        assertEquals(1, player.getId());
        assertEquals(2, player2.getId());
        assertEquals("Deyann", player.getName());
        assertEquals(new Meeples(), player2.getMeeples());
    }

    @Test
    void testAddScore() {
        assertEquals(0, player.getScore());
        player.addScore(20);
        assertEquals(20, player.getScore());
        player.addScore(-10);
        assertEquals(10, player.getScore());
    }

    @Test
    void testCardWagon () {
        WagonCard card1 = new WagonCard(Colors.BLUE);
        WagonCard card2 = new WagonCard(Colors.GREEN);
        WagonCard card3 = new WagonCard(Colors.BLUE);
        player.addCardWagon(card1);
        assertEquals(1, player.getNumberWagon());
        player.addCardWagon(card2);
        player.addCardWagon(card3);
        assertEquals(3, player.getNumberWagon());
        assertEquals(2, player.getNumberColor(Colors.BLUE));
        player.removeCardWagon(Colors.BLUE, 2);
        assertEquals(0, player.getNumberColor(Colors.BLUE));
    }

    @Test
    void testDestination () {
        DestinationCard dest1 = new DestinationCard(Cities.MANNHEIM, Cities.STUTTGART, 2);
        DestinationCard dest2 = new DestinationCard(Cities.BERLIN, Cities.LEIPZIG, 4);
        assertTrue(player.addCardDestination(dest1));
        assertEquals(1, player.getNumberDestination());
        assertThrows(IllegalArgumentException.class, () -> player.addCardDestination(dest1));
        assertThrows(IllegalArgumentException.class, () -> player.validDestinationCard(dest2));
        assertTrue(player.validDestinationCard(dest1));
        assertEquals(2, player.getScore());
        assertTrue(player.getCartesDestination().isEmpty());
    }

    @Test
    void testMeeples () {
        assertEquals(0, player.getNumberOfMeeples());
        assertTrue(player.takeMeeples(Cities.DANEMARK));
        assertEquals(1, player.getNumberOfMeeples());
        assertFalse(player.takeMeeples(Cities.DANEMARK));
        assertTrue(player.takeMeeples(Cities.HAMBURG));
        assertEquals(2, player.getNumberOfMeeples());
        assertEquals(3,Cities.HAMBURG.getMeeples().getNumber());
    }
}
