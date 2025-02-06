package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class LoggerDetailedTest {
    private LoggerDetailed loggerDetailed;
    private GameEngine gameEngine;
    private MidBot bot;
    private Player player;

    @BeforeEach
    void setUp() {
        gameEngine = spy(new GameEngine("Reich"));
        loggerDetailed = new LoggerDetailed(gameEngine);

        bot = spy (new MidBot("name", gameEngine));
        List<Bot> bots = new ArrayList<>();
        bots.add(bot);
        gameEngine.addBotsToPlayerMap(bots);
        player = spy(gameEngine.getPlayerByBot(bot));

        Map<Bot, Player> players = new HashMap<>();
        players.put(bot, player);
        doReturn(players).when(gameEngine).getPlayers();
        doReturn(5).when(gameEngine).getRound();
        doReturn("TestPlayer").when(player).getName();
        doReturn(100).when(player).getScore();
        doReturn("TestBot").when(bot).getName();
    }
/*
    @Test
    void testLogGameStart() {
        loggerDetailed.logGameStart();
        verify(gameEngine, times(1)).getPlayers();
        verify(player, times(1)).getName();
    }

    @Test
    void testLogRound() {
        loggerDetailed.logRound();
        verify(gameEngine, times(1)).getRound();
        verify(gameEngine, times(1)).getPlayers();
        verify(bot, times(1)).getName();
    }

    @Test
    void testLogFiveNoActionRounds() {
        loggerDetailed.logFiveNoActionRounds();
    }

    @Test
    void testLogGameEndWagonsCardsLeft() {
        loggerDetailed.logGameEndWagonsCardsLeft("TestPlayer", 3);
    }

    @Test
    void testLogDrawDestinationCard() {
        loggerDetailed.logDrawDestinationCard(bot);
        verify(bot, times(1)).getName();
    }

    @Test
    void testLogDrawWagonCard() throws WrongPlayerException {
        bot.drawWagonCard(Colors.BLUE);
        loggerDetailed.logDrawWagonCard(bot);
        verify(bot, times(1)).getName();
    }

    @Test
    void testBuyConnection() {
        City cityOne = bot.gameView.getGameMap().getCity("Kiel");
        Connection connection1 = bot.gameView.getGameMap().getNeighbourConnection(cityOne, bot.gameView.getGameMap().getCity("Hamburg"));
        player.addCardWagon(new WagonCard(Colors.BLACK));
        player.addCardWagon(new WagonCard(Colors.BLACK));
        assertTrue(player.buyRail(connection1,bot.gameView.getGameMap(),5));
        loggerDetailed.buyConnection(bot);
        verify(bot, times(2)).getName();
    }

    @Test
    void testLogGameResults() {
        loggerDetailed.logGameResults();
        verify(gameEngine, times(1)).getRound();
        verify(gameEngine, times(1)).getPlayers();
        verify(player, times(1)).getScore();
        verify(gameEngine, times(1)).getHighestScoreAndWinner();
    }*/
}
