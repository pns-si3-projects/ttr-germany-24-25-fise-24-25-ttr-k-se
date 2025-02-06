package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class GamesStatisticsLogger {
    private static final Logger logger = Logger.getLogger(GamesStatisticsLogger.class.getName());
    private final GameEngine gameEngine;

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new LoggerColoredFormatter());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
    }

    public GamesStatisticsLogger(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Logs the game statistics including the total number of games, the percentage of games won
     * and lost by each bot, and the average score for each bot. It also logs the highest score
     * and the winner of the game.
     */
    public void logGameStatistics() {
        int totalGames = gameEngine.getNumberOfTotalGames();
        Map<Bot, Player> players = gameEngine.getPlayers();
        Map<String, Integer> playerScores = gameEngine.getTotalScores();

        logger.info("Total games: " + totalGames);

        // Calculate and log the percentage of games won for each bot
        for (Player player : players.values()) {
            int gamesWon = gameEngine.getScoreManager().getGamesWon(player);
            double winPercentage = (double) gamesWon / totalGames * 100;
            logger.info(player.getName() + " - Games won: " + Math.round(winPercentage) + "%");
        }

        // Log the average score for each bot
        for (String playerName : playerScores.keySet()) {
            int totalScore = playerScores.get(playerName);
            if (totalGames > 0) {
                logger.info("Average score for " + playerName + " is : " + (double) totalScore / totalGames);
            } else {
                logger.info("Average score for " + playerName + " is : 0");
            }
        }
    }
}
