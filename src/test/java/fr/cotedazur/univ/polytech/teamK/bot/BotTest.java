package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    Bot bot;
    List<Bot> listBot ;
    GameEngine gameEngine = new GameEngine("Reich");
    GameView gameView ;

    @BeforeEach
    void setUp () {
        Meeple.resetMeeples();
        //gameEngine = new GameEngine<>("Reich");
        bot = new DumbBot("Dumb", gameEngine);
        listBot = new ArrayList<>();
        listBot.add(bot);
        gameEngine.addBotsToPlayerMap(listBot);
        gameView = new GameView(gameEngine,bot);
    }

    @Test
    public void testFindBestPath() {
        City cityOne = gameView.getGameMap().getCity("Kiel");
        City cityTwo = gameView.getGameMap().getCity("Freiburg");
        City cityThree = gameView.getGameMap().getCity("Karlsruhe");
        City cityFour = gameView.getGameMap().getCity("Hannover");
        Player player1 = gameView.getPlayerByBot(bot);
        ArrayList<Connection> way = bot.djikstra(cityOne, cityTwo);
        //System.out.println(way);
        assertEquals(11, way.size());
        gameView.getGameMap().getNeighbourConnection(cityTwo,cityThree).setFree(false);
        way = bot.djikstra(cityOne, cityTwo);
        //System.out.println(way);
        Connection connection1 = gameView.getGameMap().getNeighbourConnection(cityOne, gameView.getGameMap().getCity("Hamburg"));
        Connection connection2 = gameView.getGameMap().getNeighbourConnection(cityFour,gameView.getGameMap().getCity("Hamburg"));
        player1.addCardWagon(new WagonCard(Colors.BLACK));
        player1.addCardWagon(new WagonCard(Colors.BLACK));
        player1.addCardWagon(new WagonCard(Colors.RED));
        player1.addCardWagon(new WagonCard(Colors.RED));
        player1.addCardWagon(new WagonCard(Colors.RED));
        player1.addCardWagon(new WagonCard(Colors.RED));
        assertTrue(player1.buyRail(connection1,gameView.getGameMap(),5));
        assertTrue(player1.buyRail(connection2,gameView.getGameMap(),5));
        way = bot.djikstra(cityOne, cityTwo);
        //System.out.println(way);
    }

    @Test
    void testDraw () {
        List<DestinationCard> draw = bot.drawDestFromNumber(3);
        //assertThrows(WrongPlayerException.class, () ->bot.drawDestinationCard());
        assertEquals(52, gameView.getShortDestination().getRemainingCards());
        assertEquals(33, gameView.getLongueDestination().getRemainingCards());
        assertTrue(bot.giveBackCard(draw));
        assertEquals(55, gameView.getShortDestination().getRemainingCards());
        assertEquals(34, gameView.getLongueDestination().getRemainingCards());
    }
}