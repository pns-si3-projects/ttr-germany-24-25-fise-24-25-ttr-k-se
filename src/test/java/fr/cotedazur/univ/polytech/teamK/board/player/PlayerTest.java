package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeples;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player1;
    Player player2;

    @BeforeEach
    void setUp () {
        Player.resetIdCounter();
        player1 = new Player("Deyann");
        player2 = new Player("Tom");
    }

    @Test
    void testInit () {
        assertEquals(1, player1.getId());
        assertEquals(2, player2.getId());
        assertEquals("Deyann", player1.getName());
        assertEquals(new Meeple(), player2.getMeeples());
    }

    @Test
    void testAddScore() {
        assertEquals(0,player2.getScore());
        player2.addScore(20);
        assertEquals(20, player2.getScore());
        player2.addScore(-10);
        assertEquals(10, player2.getScore());
    }

    @Test
    void testCardWagon () {
        WagonCard card1 = new WagonCard(Colors.BLUE);
        WagonCard card2 = new WagonCard(Colors.GREEN);
        WagonCard card3 = new WagonCard(Colors.BLUE);
        player1.addCardWagon(card1);
        assertEquals(1, player1.getNumberWagon());
        player1.addCardWagon(card2);
        player1.addCardWagon(card3);
        assertEquals(3, player1.getNumberWagon());
        assertEquals(2, player1.getNumberColor(Colors.BLUE));
        player1.removeCardWagon(Colors.BLUE, 2);
        assertEquals(0, player1.getNumberColor(Colors.BLUE));
    }

    @Test
    void testDestination () {
        DestinationCard dest1 = new DestinationCard(Cities.MANNHEIM, Cities.STUTTGART, 2);
        DestinationCard dest2 = new DestinationCard(Cities.BERLIN, Cities.LEIPZIG, 4);
        assertTrue(player1.addCardDestination(dest1));
        assertEquals(1,player1.getNumberDestination());
        assertThrows(IllegalArgumentException.class, () -> player1.addCardDestination(dest1));
        assertThrows(IllegalArgumentException.class, () -> player1.validDestinationCard(dest2));
        assertTrue(player1.validDestinationCard(dest1));
        assertEquals(2, player1.getScore());
        assertTrue(player1.getCartesDestination().isEmpty());
    }

    /**
    @Test
    void testMeeples () {
        assertEquals(0, player1.getNumberOfMeeples());
        assertTrue(player1.takeMeeples(Cities.DANEMARK, new int[]{1, 2, 3, 4, 5}));
        assertEquals(1, player1.getNumberOfMeeples());
        assertFalse(player1.takeMeeples(Cities.DANEMARK, new int[]{1, 2, 3, 4, 5}));
        assertTrue(player1.takeMeeples(Cities.HAMBURG, new int[]{1, 2, 3, 4, 5}));
        assertEquals(2, player1.getNumberOfMeeples());
        assertEquals(3,Cities.HAMBURG.getMeeples().getNumber());
    }*/
}