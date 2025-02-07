package fr.cotedazur.univ.polytech.teamK.game;

import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class LoggerDetailedTest {
    private LoggerDetailed loggerDetailed;
    private GameEngine gameEngine;
    private Bot bot;
    private Player player;
    private Logger logger;
    private Handler handler;

    @BeforeEach
    void setUp() {
        gameEngine = mock(GameEngine.class);
        loggerDetailed = new LoggerDetailed(gameEngine);

        bot = mock(Bot.class);
        player = mock(Player.class);

        Map<Bot, Player> players = new HashMap<>();
        players.put(bot, player);
        when(gameEngine.getPlayers()).thenReturn((HashMap<Bot, Player>) players);
        when(player.getName()).thenReturn("TestPlayer");
        when(bot.getName()).thenReturn("TestBot");

        // Capture log messages
        logger = Logger.getLogger(LoggerDetailed.class.getName());
        logger.setLevel(Level.FINER);
        handler = mock(Handler.class);
        logger.addHandler(handler);
    }

    @Test
    void testLogGameStart() {
        loggerDetailed.logGameStart();

        ArgumentCaptor<LogRecord> captor = ArgumentCaptor.forClass(LogRecord.class);
        verify(handler, atLeastOnce()).publish(captor.capture());

        boolean messageLogged = captor.getAllValues().stream()
                .map(LogRecord::getMessage)
                .anyMatch(msg -> msg.contains("All aboard the train") || msg.contains("Number of Players") || msg.contains("Players Names"));

        assertTrue(messageLogged, "logGameStart should log game start messages");
    }

    @Test
    void testLogFiveNoActionRounds() {
        loggerDetailed.logFiveNoActionRounds();
    }

    @Test
    void testLogGameEndWagonsCardsLeft() {
        loggerDetailed.logGameEndWagonsCardsLeft("TestPlayer", 3);
    }
}