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
        handler.setFormatter(new ColoredFormatter());
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
        int totalRounds = gameEngine.getNumberOfTotalGames();
        Map<Bot, Player> players = gameEngine.getPlayers();
        Map<Bot, Integer> botScores = gameEngine.getScores();

        logger.info("Total games: " + totalRounds);

        // Calculate and log the percentage of games won for each bot
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            Bot bot = entry.getKey();
            int gamesWon = gameEngine.getScoreManager().getGamesWon(bot);
            double winPercentage = (double) gamesWon / totalRounds * 100;
            logger.info(bot.getName() + " - Games won: " + Math.round(winPercentage) + "%");
        }

        // Calculate and log the percentage of games lost for each bot
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            Bot bot = entry.getKey();
            int gamesLost = gameEngine.getScoreManager().getGamesLost(bot);
            double lossPercentage = (double) gamesLost / totalRounds * 100;
            logger.info(bot.getName() + " - Games lost: " + Math.round(lossPercentage) + "%");
        }

        // Log the average score for each bot
        for (Map.Entry<Bot, Integer> entry : botScores.entrySet()) {
            Bot bot = entry.getKey();
            int totalScore = entry.getValue();
            if (totalRounds > 0) {
                logger.info("Average score for " + bot.getName() + " is : " + Math.round((double) totalScore / totalRounds));
            } else {
                logger.info("Average score for " + bot.getName() + " is : 0");
            }
        }

        // Log the highest score and winner
        Map.Entry<Player, Integer> highestScoreAndWinner = gameEngine.getHighestScoreAndWinner();
        Player winner = highestScoreAndWinner.getKey();
        int highestScore = highestScoreAndWinner.getValue();
        logger.info("Winner: " + winner.getName() + " with a score of " + highestScore);
    }
}
