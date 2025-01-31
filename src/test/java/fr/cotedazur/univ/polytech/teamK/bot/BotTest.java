package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    Bot bot;
    List<Bot> listBot ;
    GameEngine<Bot> gameEngine ;

    @BeforeEach
    void setUp () {
        gameEngine = new GameEngine<>("Reich");
        bot = new DumbBot("Dumb", gameEngine);
        listBot = new ArrayList<>();
        listBot.add(bot);
        gameEngine.addBotsToPlayerMap(listBot);
    }

    @Test
    public void testShortestDestination() {  // Assurez-vous que c'est bien public
        City cityOne = gameEngine.getGameMap().getCity("Kiel");
        City cityTwo = gameEngine.getGameMap().getCity("Freiburg");

        ArrayList<Connection> way = bot.djikstra(cityOne, cityTwo);
        System.out.println(way);
        assertEquals(6, way.size());
    }

    @Test
    void testDraw () {
        List<DestinationCard> draw = bot.drawDestFromNumber(3);
        //assertThrows(WrongPlayerException.class, () ->bot.drawDestinationCard());
        assertEquals(52, gameEngine.getShortDestinationDeck().getRemainingCards());
        assertEquals(33, gameEngine.getLongDestinationDeck().getRemainingCards());
        assertTrue(bot.giveBackCard(draw));
        assertEquals(55, gameEngine.getShortDestinationDeck().getRemainingCards());
        assertEquals(34, gameEngine.getLongDestinationDeck().getRemainingCards());
    }
}