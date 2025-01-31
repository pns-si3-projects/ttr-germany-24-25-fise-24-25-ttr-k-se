package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    Board map;
    Deck<DestinationCard> longDest;
    Deck<DestinationCard> shortDest;
    Bot bot;

    @BeforeEach
    void setUp () {
        map = new Board("Reich");
        longDest = new Deck<>(TypeOfCards.LONG_DESTINATION,map);
        shortDest = new Deck<>(TypeOfCards.SHORT_DESTINATION,map);
        bot = new DumbBot("Dumn",map);
    }

    @Test
    public void testShortestDestination() {  // Assurez-vous que c'est bien public
        City cityOne = map.getCity("Kiel");
        City cityTwo = map.getCity("Freiburg");

        ArrayList<Connection> way = bot.djikstra(cityOne, cityTwo,map);
        System.out.println(way);
        assertEquals(6, way.size());
    }

    @Test
    void testDraw () {
        List<DestinationCard> draw = bot.drawDestFromNumber(shortDest,longDest,3);
        bot.drawDestinationCard(shortDest,longDest);
        assertEquals(50, shortDest.getRemainingCards());
        assertEquals(33, longDest.getRemainingCards());
        assertTrue(bot.giveBackCard(draw,shortDest,longDest));
        assertEquals(55, shortDest.getRemainingCards());
        assertEquals(34, longDest.getRemainingCards());
    }
}