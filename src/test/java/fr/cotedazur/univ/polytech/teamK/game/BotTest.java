package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    MapHash map;
    Deck<DestinationCard> longDest;
    Deck<DestinationCard> shortDest;
    Bot bot;

    @BeforeEach
    void setUp () {
        map = new MapHash("Reich");
        longDest = new Deck<>(TypeOfCards.LONG_DESTINATION,map);
        shortDest = new Deck<>(TypeOfCards.SHORT_DESTINATION,map);
        bot = new Bot(1,map);
    }

    @Test
    void testDraw () {
        List<DestinationCard> draw = bot.drawDestCard(shortDest,longDest,3);
        assertEquals(52, shortDest.getRemainingCards());
        assertEquals(33, longDest.getRemainingCards());
        draw.remove(3);
        draw.remove(1);
        assertTrue(bot.giveBackCard(draw,shortDest,longDest));
        assertEquals(54, shortDest.getRemainingCards());
        assertEquals(33, longDest.getRemainingCards());
    }
}