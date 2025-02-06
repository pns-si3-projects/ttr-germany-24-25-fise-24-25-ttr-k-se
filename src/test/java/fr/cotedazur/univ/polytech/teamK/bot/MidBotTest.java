package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MidBotTest {
    MidBot bot;
    GameView gameView;
    GameEngine gameEngine;

    @BeforeEach
    public void setUp () {
        gameEngine = new GameEngine("Reich");
        List<Bot> bots = new ArrayList<>();
        bot = new MidBot("Mid", gameEngine);
        bots.add(bot);
        gameEngine.addBotsToPlayerMap(bots);
        gameView = bot.gameView;
    }

    @Test
    public void testDrawDestination () throws WrongPlayerException {
        Deck<DestinationCard> shortDestination = gameView.getShortDestination();
        Deck<DestinationCard> longDesination = gameView.getLongueDestination();
        gameEngine.setCurrentBot(bot);
        bot.drawDestinationCard();
        DestinationCard drawShort = shortDestination.draw();
        DestinationCard drawLong = longDesination.draw();
        DestinationCard secondDraw = shortDestination.draw();
        if (drawShort.getValue() < secondDraw.getValue()) drawShort = secondDraw;
        secondDraw = longDesination.draw();
        if (drawLong.getValue() < secondDraw.getValue()) drawLong = secondDraw;
        assertTrue(gameView.getMyDestinationCards().contains(drawLong));
        assertTrue(gameView.getMyDestinationCards().contains(drawShort));
    }

    @Test
    public void testDrawWagon () throws WrongPlayerException {
        Deck<WagonCard> wagonCardDeck =  gameView.getWagonDeck();
        WagonCard wagonCard = new WagonCard(Colors.BLUE);
        List<WagonCard> listWagon = new ArrayList<>();
        listWagon.add(wagonCard);
        listWagon.add(wagonCard);
        gameEngine.setCurrentBot(bot);
        bot.drawWagonCard(Colors.BLUE);
        if(wagonCardDeck.getVisibleCard().contains(wagonCard)) {
            if(wagonCardDeck.draw() == wagonCard) assertTrue(gameView.getMyWagonCards().containsAll(listWagon));
            else gameView.getMyWagonCards().contains(wagonCard);
        }
        assertEquals(2, gameView.getMyWagonCards().size());
    }
}