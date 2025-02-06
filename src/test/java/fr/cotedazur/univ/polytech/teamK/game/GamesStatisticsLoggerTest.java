package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class GamesStatisticsLoggerTest {

    @Mock
    private GameEngine gameEngine;

    @Mock
    private ScoreGeneralManager scoreManager;

    private GamesStatisticsLogger gamesStatisticsLogger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gamesStatisticsLogger = new GamesStatisticsLogger(gameEngine);
    }

    @Test
    void testLogGameStatistics() {
        Map<Bot, Player> players = new HashMap<>();
        Map<String, Integer> playerScores = new HashMap<>();

        Bot bot1 = mock(Bot.class);
        Bot bot2 = mock(Bot.class);
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getName()).thenReturn("Alice");
        when(player2.getName()).thenReturn("Bob");
        when(gameEngine.getNumberOfTotalGames()).thenReturn(10);
        when(gameEngine.getPlayers()).thenReturn((HashMap<Bot, Player>) players);
        when(gameEngine.getTotalScores()).thenReturn(playerScores);
        when(gameEngine.getScoreManager()).thenReturn(scoreManager);

        players.put(bot1, player1);
        players.put(bot2, player2);
        playerScores.put("Alice", 800);
        playerScores.put("Bob", 600);

        when(scoreManager.getGamesWon(player1)).thenReturn(4);
        when(scoreManager.getGamesWon(player2)).thenReturn(6);

        gamesStatisticsLogger.logGameStatistics();

        verify(gameEngine, times(1)).getNumberOfTotalGames();
        verify(gameEngine, times(1)).getPlayers();
        verify(gameEngine, times(1)).getTotalScores();
        verify(scoreManager, times(1)).getGamesWon(player1);
        verify(scoreManager, times(1)).getGamesWon(player2);
    }
}
