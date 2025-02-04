package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreManager {
    private final GameEngine gameEngine;
    private final Map<Bot, Integer> gamesWon = new HashMap<>();
    private final Map<Bot, Integer> gamesLost = new HashMap<>();

    public ScoreManager(GameEngine gameEngine) {
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

    public void calculateMeeplePoints() {
        for (Colors meepleColor : Colors.values()) {
            processMeepleColorPoints(meepleColor);
            if (meepleColor.ordinal() == 5) break; // Stop après la 6e couleur
        }
    }

    /**
     * Traite le calcul des points pour une couleur de meeples spécifique.
     */
    private void processMeepleColorPoints(Colors meepleColor) {
        List<Player> firstWinners = new ArrayList<>();
        List<Player> secondWinners = new ArrayList<>();

        determineMeepleWinners(meepleColor, firstWinners, secondWinners);
        awardMeeplePoints(firstWinners, secondWinners);
    }

    /**
     * Détermine les joueurs ayant le plus et le deuxième plus grand nombre de meeples d'une couleur donnée.
     */
    private void determineMeepleWinners(Colors meepleColor, List<Player> firstWinners, List<Player> secondWinners) {
        Map<Bot, Player> players = gameEngine.getPlayers();
        if (players.isEmpty()) return;

        // Récupère un joueur arbitraire pour l'initialisation
        Player first = players.values().iterator().next();
        firstWinners.add(first);
        secondWinners.add(first);

        for (Player player : players.values()) {
            int playerMeeples = player.getMeeples().getNumberOfAColor(meepleColor);
            updateWinners(player, playerMeeples, meepleColor, firstWinners, secondWinners);
        }
    }

    /**
     * Met à jour les listes des premiers et deuxièmes gagnants en fonction du nombre de meeples.
     */
    private void updateWinners(Player player, int playerMeeples, Colors meepleColor, List<Player> firstWinners, List<Player> secondWinners) {
        int firstMeeples = firstWinners.get(0).getMeeples().getNumberOfAColor(meepleColor);
        int secondMeeples = secondWinners.get(0).getMeeples().getNumberOfAColor(meepleColor);

        if (playerMeeples > firstMeeples) {
            secondWinners.clear();
            secondWinners.addAll(firstWinners);
            firstWinners.clear();
            firstWinners.add(player);
        } else if (playerMeeples == firstMeeples) {
            firstWinners.add(player);
        } else if (playerMeeples > secondMeeples) {
            secondWinners.clear();
            secondWinners.add(player);
        } else if (playerMeeples == secondMeeples) {
            secondWinners.add(player);
        }
    }

    /**
     * Attribue les points aux joueurs en fonction des gagnants déterminés.
     */
    private void awardMeeplePoints(List<Player> firstWinners, List<Player> secondWinners) {
        for (Player winner : firstWinners) {
            winner.addScore(20);
        }

        if (firstWinners.size() == 1) { // Un seul gagnant => les seconds gagnent 10 points
            for (Player winner : secondWinners) {
                winner.addScore(10);
            }
        }
    }

    public void recordWin(Bot bot) {
        gamesWon.put(bot, gamesWon.getOrDefault(bot, 0) + 1);
    }

    public void recordLoss(Bot bot) {
        gamesLost.put(bot, gamesLost.getOrDefault(bot, 0) + 1);
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
