package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.HashMap;
import java.util.Map;

public class ScoreGeneralManager {
    private final GameEngine gameEngine;

    private final Map<String, Integer> totalScores = new HashMap<>();
    private final Map<String, Integer> gamesWon = new HashMap<>();
    private final Map<String, Integer> gamesLost = new HashMap<>();

    public ScoreGeneralManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


    public boolean addScore(Bot bot, int score) throws WrongPlayerException {
        if(gameEngine.confirmId(bot)){
            gameEngine.getPlayerByBot(bot).addScore(score);
        }
        return false;
    }

    public Map<Player, Integer> getScores(){
        HashMap<Player, Integer> scores = new HashMap<>();
        for(Player player : gameEngine.getPlayers().values()){
            int score = player.getScore();
            scores.put(player, score);
        }
        return scores;
    }

    public Map<String, Integer> getTotalScores(){
        return totalScores;
    }

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
        return gamesWon.get(player.getName());
    }

    public int getGamesLost(Player player) {
        return gamesLost.get(player.getName());
    }

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
