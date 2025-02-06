package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The MeeplePointsManager class is responsible for calculating and awarding meeple points
 * to players based on the number of meeples they have of each color.
 */
public class MeeplePointsManager {
    private final GameEngine gameEngine;

    public MeeplePointsManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Calculates and awards meeple points to players.
     */
    public void calculateMeeplePoints() {
        for (Colors meepleColor : Colors.values()) {
            processMeepleColorPoints(meepleColor);
            if (meepleColor.ordinal() == 5) break; // Stop après la 6e couleur
        }
    }

    /**
     * Processes the calculation of points for a specific meeple color.
     *
     * @param meepleColor the color of the meeples to be processed
     */
    private void processMeepleColorPoints(Colors meepleColor) {
        List<Player> firstWinners = new ArrayList<>();
        List<Player> secondWinners = new ArrayList<>();

        determineMeepleWinners(meepleColor, firstWinners, secondWinners);
        awardMeeplePoints(firstWinners, secondWinners);
    }

    /**
     * Determines the players with the most and second most meeples of a given color.
     *
     * @param meepleColor the color of the meeples to be counted
     * @param firstWinners the list of players with the most meeples
     * @param secondWinners the list of players with the second most meeples
     */
    private void determineMeepleWinners(Colors meepleColor, List<Player> firstWinners, List<Player> secondWinners) {
        Map<Bot, Player> players = gameEngine.getPlayers();
        if (players.isEmpty()) return;

        // Get an arbitrary player for initialization
        Player first = players.values().iterator().next();
        firstWinners.add(first);
        secondWinners.add(first);

        for (Player player : players.values()) {
            int playerMeeples = player.getMeeples().getNumberOfAColor(meepleColor);
            updateWinners(player, playerMeeples, meepleColor, firstWinners, secondWinners);
        }
    }

    /**
     * Updates the lists of first and second winners based on the number of meeples.
     *
     * @param player the player being evaluated
     * @param playerMeeples the number of meeples the player has
     * @param meepleColor the color of the meeples being counted
     * @param firstWinners the list of players with the most meeples
     * @param secondWinners the list of players with the second most meeples
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
     * Awards points to players based on the determined winners.
     *
     * @param firstWinners the list of players with the most meeples
     * @param secondWinners the list of players with the second most meeples
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
}