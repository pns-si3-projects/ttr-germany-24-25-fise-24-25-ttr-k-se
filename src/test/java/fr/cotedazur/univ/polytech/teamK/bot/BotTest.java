package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.game.MapHash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        bot = new DumbBot("Dumn",map);
    }

    @Test
    void testDraw () {
        List<DestinationCard> draw = bot.drawDestFromNumber(shortDest,longDest,3);
        bot.drawDestinationCard(shortDest,longDest);
        assertEquals(52, shortDest.getRemainingCards());
        assertEquals(33, longDest.getRemainingCards());
        assertTrue(bot.giveBackCard(draw,shortDest,longDest));
        assertEquals(55, shortDest.getRemainingCards());
        assertEquals(34, longDest.getRemainingCards());
    }
}