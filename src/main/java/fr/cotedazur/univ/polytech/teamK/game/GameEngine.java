package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameEngine <T extends Player> {
    private Board gameMap;
    private List<T> players;
    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private GameView gameView;

    public GameEngine(List<T> players, String mapName) {
        this.gameMap = new Board(mapName);
        this.players = players;
        this.shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION, gameMap);
        this.longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, gameMap);
        this.wagonDeck = new Deck<>(TypeOfCards.WAGON, gameMap);
        this.gameView = new GameView(this);
    }

    public Board getGameMap() { return gameMap; }
    public List<T> getPlayers() { return players; }
    public Deck<DestinationCard> getShortDestinationDeck() { return shortDestinationDeck; }
    public Deck<DestinationCard> getLongDestinationDeck() { return longDestinationDeck; }
    public Deck<WagonCard> getWagonDeck() { return wagonDeck; }

    public void startGame() {
        while (!isGameOver()) {
            for (T player : players) {
                player.playTurn(gameView);
            }
        }
        for (T player : players) {
            player.playTurn(gameView);
        }
        calculateMeeplePoints();
        System.out.println("Partie terminée !");
        gameView.displayFinalScores();
    }

    private boolean isGameOver() {
        return players.stream().anyMatch(p -> p.getWagonsRemaining() < 3);
    }

    private void calculateMeeplePoints() {
        for (Colors meepleColor : Colors.values()) {
            processMeepleColorPoints(meepleColor);
            if (meepleColor.ordinal() == 5) break; // Stop après la 6e couleur
        }
    }

    /**
     * Traite le calcul des points pour une couleur de meeples spécifique.
     */
    private void processMeepleColorPoints(Colors meepleColor) {
        List<T> firstWinners = new ArrayList<>();
        List<T> secondWinners = new ArrayList<>();

        determineMeepleWinners(meepleColor, firstWinners, secondWinners);
        awardMeeplePoints(firstWinners, secondWinners);
    }

    /**
     * Détermine les joueurs ayant le plus et le deuxième plus grand nombre de meeples d'une couleur donnée.
     */
    private void determineMeepleWinners(Colors meepleColor, List<T> firstWinners, List<T> secondWinners) {
        firstWinners.add(players.getFirst());
        secondWinners.add(players.getFirst());

        for (T player : players) {
            int playerMeeples = player.getMeeples().getNumberOfAColor(meepleColor);
            updateWinners(player, playerMeeples, meepleColor, firstWinners, secondWinners);
        }
    }

    /**
     * Met à jour les listes des premiers et deuxièmes gagnants en fonction du nombre de meeples.
     */
    private void updateWinners(T player, int playerMeeples, Colors meepleColor, List<T> firstWinners, List<T> secondWinners) {
        int firstMeeples = firstWinners.getFirst().getMeeples().getNumberOfAColor(meepleColor);
        int secondMeeples = secondWinners.getFirst().getMeeples().getNumberOfAColor(meepleColor);

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
    private void awardMeeplePoints(List<T> firstWinners, List<T> secondWinners) {
        for (T winner : firstWinners) {
            winner.addScore(20);
        }

        if (firstWinners.size() == 1) { // Un seul gagnant => les seconds gagnent 10 points
            for (T winner : secondWinners) {
                winner.addScore(10);
            }
        }
    }
}
