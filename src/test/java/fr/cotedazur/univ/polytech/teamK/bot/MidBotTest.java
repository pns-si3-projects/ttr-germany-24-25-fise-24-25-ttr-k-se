package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
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
        Meeple.resetMeeples();
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
    public void testBuyRail () throws WrongPlayerException {
        City cityOne = gameView.getGameMap().getCity("Magdeburg");
        City cityTwo = gameView.getGameMap().getCity("Chemnitz");
        Player player1;
        ArrayList<Connection> path = bot.djikstra(cityOne, cityTwo);
        do{
            gameEngine.setCurrentBot(bot);
            bot.drawWagonCard(Colors.BLUE);
            player1 = gameView.getPlayerByBot(bot);
        } while(player1.getNumberColor(Colors.BLUE) != 2 && player1.getNumberColor(Colors.YELLOW) != 2);
        bot.buyConnection(path);
        assertEquals(1, gameView.getMyConnections().size());
        if(player1.getNumberColor(Colors.BLUE) == 2) assertEquals(path.getLast(),gameView.getMyConnections().getFirst());
        if(player1.getNumberColor(Colors.YELLOW) == 2) assertEquals(path.getFirst(),gameView.getMyConnections().getFirst());
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
    void testDraw () throws WrongPlayerException {
        gameEngine.setCurrentBot(bot);
        List<DestinationCard> draw = bot.drawDestFromNumber(3);
        assertEquals(52, gameView.getShortDestination().getRemainingCards());
        assertEquals(33, gameView.getLongueDestination().getRemainingCards());
        assertTrue(bot.giveBackCard(draw));
        assertEquals(55, gameView.getShortDestination().getRemainingCards());
        assertEquals(34, gameView.getLongueDestination().getRemainingCards());
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
        assertEquals(1, gameView.getMyWagonCards().size());
    }

    @Test
    public void testPlayTurn () throws WrongPlayerException {
        assertTrue(gameView.getMyDestinationCards().isEmpty());
        assertTrue(gameView.getMyWagonCards().isEmpty());
        assertTrue(gameView.getMyWagonCards().isEmpty());
        gameEngine.setCurrentBot(bot);
        bot.playTurn();
        assertEquals(2,gameView.getMyDestinationCards().size());
        DestinationCard toAchieve = gameView.getMyDestinationCards().getFirst();
        gameEngine.setCurrentBot(bot);
        bot.playTurn();
        assertEquals(2,gameView.getMyWagonCards().size());
    }
}