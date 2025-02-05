package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.HashMap;
import java.util.Map;

public class ScoreGeneralManager {
    private final GameEngine gameEngine;
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

    public HashMap<Bot, Integer> getScores(){
        HashMap<Bot, Integer> scores = new HashMap<>();
        for(Map.Entry<Bot, Player> entry: gameEngine.getPlayers().entrySet()){
            Bot bot = entry.getKey();
            int score = entry.getValue().getScore();
            scores.put(bot, score);
        }
        return scores;
    }

    public void recordWin(Bot bot) {
        gamesWon.put(bot.getName(), gamesWon.getOrDefault(bot, 0) + 1);
    }

    public void recordLoss(Bot bot) {
        gamesLost.put(bot.getName(), gamesLost.getOrDefault(bot, 0) + 1);
    }

    public int getGamesWon(Bot bot) {
        return gamesWon.getOrDefault(bot, 0);
    }

    public int getGamesLost(Bot bot) {
        return gamesLost.getOrDefault(bot, 0);
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
