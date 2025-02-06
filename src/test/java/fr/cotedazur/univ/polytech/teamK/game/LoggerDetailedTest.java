package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class LoggerDetailedTest {
    private LoggerDetailed loggerDetailed;
    private GameEngine gameEngine;
    private Bot bot;
    private Player player;

    @BeforeEach
    void setUp() {
        gameEngine = mock(GameEngine.class);
        loggerDetailed = new LoggerDetailed(gameEngine);

        bot = mock(Bot.class);
        player = mock(Player.class);

        Map<Bot, Player> players = new HashMap<>();
        players.put(bot, player);
        when(gameEngine.getPlayers()).thenReturn((HashMap<Bot, Player>) players);
        when(gameEngine.getRound()).thenReturn(5);
        when(player.getName()).thenReturn("TestPlayer");
        when(player.getScore()).thenReturn(100);
        when(bot.getName()).thenReturn("TestBot");
    }

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
    void testLogDrawWagonCard() {
        loggerDetailed.logDrawWagonCard(bot);
        verify(bot, times(1)).getName();
    }

    @Test
    void testBuyConnection() {
        loggerDetailed.buyConnection(bot);
        verify(bot, times(1)).getName();
    }

    @Test
    void testLogGameResults() {
        loggerDetailed.logGameResults();
        verify(gameEngine, times(1)).getRound();
        verify(gameEngine, times(1)).getPlayers();
        verify(player, times(1)).getScore();
        verify(gameEngine, times(1)).getHighestScoreAndWinner();
    }
}
