package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player1;
    Player player2;
    GameBoard map;

    @BeforeEach
    void setUp () {
        Meeple.resetMeeples();
        Player.resetIdCounter();
        map = new GameBoard("Reich");
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
        City cityOne = map.getCity("Kiel");
        City cityTwo = map.getCity("Rostock");
        Connection connection = map.getNeighbourConnection(cityOne,cityTwo);
        DestinationCard dest1 = new DestinationCard(cityOne, cityTwo, 2);
        DestinationCard dest2 = new DestinationCard(map.getCity("Berlin"), map.getCity("Munster"), 4);
        assertTrue(player1.addCardDestination(dest1));
        assertEquals(1,player1.getNumberDestination());
        assertThrows(IllegalArgumentException.class, () -> player1.addCardDestination(dest1));
        assertThrows(IllegalArgumentException.class, () -> player1.validDestinationCard(dest2));
        player1.addCardWagon(new WagonCard(Colors.ORANGE));
        player1.addCardWagon(new WagonCard(Colors.ORANGE));
        player1.addCardWagon(new WagonCard(Colors.ORANGE));
        player1.addCardWagon(new WagonCard(Colors.ORANGE));
        assertTrue(player1.buyRail(connection,map,5));
        assertTrue(player1.validDestinationCard(dest1));
        assertEquals(9, player1.getScore());
        assertTrue(player1.getCartesDestination().isEmpty());
    }
}