package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScoreGeneralManager {
    private final GameEngine gameEngine;

    private final Map<String, Integer> totalScores = new HashMap<>();
    private final Map<String, Integer> gamesWon = new HashMap<>();
    private final Map<String, Integer> gamesLost = new HashMap<>();

    /**
     * Manages the scores of players in the game, tracking total scores,
     * wins, and losses for each player.
     */
    public ScoreGeneralManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Adds a score to the given bot if it is a valid player in the game.
     *
     * @param bot   The bot whose score is to be added.
     * @param score The score to be added.
     * @return true if the score was successfully added, false otherwise.
     * @throws WrongPlayerException If the bot is not recognized as a valid player.
     */
    public boolean addScore(Bot bot, int score) throws WrongPlayerException {
        if(gameEngine.confirmId(bot)){
            gameEngine.getPlayerByBot(bot).addScore(score);
        }
        return false;
    }

    /**
     * Retrieves the current scores of all players.
     *
     * @return A map of players and their corresponding scores.
     */
    public Map<Player, Integer> getScores(){
        HashMap<Player, Integer> scores = new HashMap<>();
        for(Player player : gameEngine.getPlayers().values()){
            int score = player.getScore();
            scores.put(player, score);
        }
        return scores;
    }

    /**
     * Gets the score of a specific player by their name.
     *
     * @param name The name of the player.
     * @return The player's score, or 0 if the player is not found.
     */
    public Integer getPlayerScore (String name) {
        Map<Player, Integer> scores = getScores();
        for (Map.Entry<Player, Integer> entry  : scores.entrySet()) {
            if(Objects.equals(entry.getKey().getName(), name)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    public Map<String, Integer> getTotalScores(){
        return totalScores;
    }

    /**
     * Records the final score of a player at the end of a game.
     *
     * @param player The player whose score is to be recorded.
     */
    public void recordScore(Player player) {
        String nomPlayer = player.getName();
        totalScores.put(nomPlayer, totalScores.getOrDefault(nomPlayer, 0) + player.getScore());
    }


    public void recordWin(Player player) {
        String nomPlayer = player.getName();
        gamesWon.put(nomPlayer, gamesWon.getOrDefault(nomPlayer, 0) + 1);
    }


    public void recordLoss(Player player) {
        String nomPlayer = player.getName();
        gamesLost.put(nomPlayer, gamesLost.getOrDefault(nomPlayer, 0) + 1);
    }

    public int getGamesWon(Player player) {
        try {
            return gamesWon.get(player.getName());
        }
        catch (NullPointerException e) {
            return 0;
        }
    }

    public int getGamesLost(Player player) {
        try {
            return gamesLost.get(player.getName());
        }
        catch (NullPointerException e) {
            return 0;
        }
    }

    /**
     * Determines the player with the highest score and returns their score.
     *
     * @return A Map.Entry containing the highest-scoring player and their score.
     */
    public Map.Entry<Player, Integer> getHighestScoreAndWinner() {
        Player highestScoringPlayer = null;
        int highestScore = 0;
        for (Map.Entry<Bot, Player> entry : gameEngine.getPlayers().entrySet()) {
            Player player = entry.getValue();
            int score = player.getScore();
            if (score > highestScore) {
                highestScore = score;
                highestScoringPlayer = player;
            }
        }
        return new HashMap.SimpleEntry<>(highestScoringPlayer, highestScore);
    }
}
