package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameEngine <T extends Bot> {
    private Board gameMap;
    private HashMap<Integer,Player> players;
    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private GameView gameView;

    public GameEngine(List<T> players, String mapName) {
        this.gameMap = new Board(mapName);
        this.players = new HashMap<>();
        addBotToPlayerMap(players);
        this.shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION, gameMap);
        this.longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, gameMap);
        this.wagonDeck = new Deck<>(TypeOfCards.WAGON, gameMap);
        this.gameView = new GameView(this);
    }
    private void addBotToPlayerMap(List<T> bots) {
        for (Bot bot : bots) {
            Player player = new Player(bot.getName());
            players.put(bot.getId(),player);
        }
    }


    public HashMap<Integer, Player> getPlayers() { return players; }
    public Player getPlayerById(int id) { return players.get(id); }

    /*
    INFOS RELATIVES AU BOARD
     */
    public Board getGameMap() { return gameMap; }
    public Deck<DestinationCard> getShortDestinationDeck() { return shortDestinationDeck; }
    public Deck<DestinationCard> getLongDestinationDeck() { return longDestinationDeck; }
    public Deck<WagonCard> getWagonDeck() { return wagonDeck; }

    /*
    INFO RELATIVES AUX JOUEURS
     */
    public String getNameForPlayer(T player) {return getPlayerById(player.getId())
            .getName();
    }
    public int getScoreForPlayer(T player) {return getPlayerById(player.getId())
            .getScore();
    }
    public ArrayList<DestinationCard> getDestinationCardsForPlayer(T player) {return getPlayerById(player.getId())
            .getCartesDestination();
    }
    public ArrayList<WagonCard> getWagonCardsForPlayer(T player) {return getPlayerById(player.getId())
            .getCartesWagon();
    }
    public int getWagonsRemainingForPlayer(T player) {return getPlayerById(player.getId())
            .getWagonsRemaining();
    }
    public int getNumberWagonForPlayer(T player) {return getPlayerById(player.getId())
            .getCartesWagon().size();
    }
    public int getNumberDestinationForPlayer (T player) {return getPlayerById(player.getId())
            .getCartesDestination().size();
    }
    public Meeple getMeeplesForPlayer(T player) {return getPlayerById(player.getId())
            .getMeeples();
    }
    public int getNumberOfMeeplesForPlayer(T player) {return getPlayerById(player.getId())
            .getMeeples().getNumber();
    }
    public ArrayList<Connection> getConnectionsForPlayer(T player) {return getPlayerById(player.getId())
            .getConnections();
    }

    public void addDestinationCardToDeck(T player, DestinationCard destinationCard) throws PaquetPleinException {
        getPlayerById(player.getId()).removeDestinationCard(destinationCard);
        if(destinationCard.getType()==TypeOfCards.SHORT_DESTINATION) {
            shortDestinationDeck.addCard(destinationCard);
        }
        else if(destinationCard.getType()==TypeOfCards.LONG_DESTINATION) {
            longDestinationDeck.addCard(destinationCard);
        }
    }

    public void startGame() {
        while (!isGameOver()) {
            players.values().forEach(player -> {
                player.playTurn(gameView);
            });
        }
        players.values().forEach(player -> {
            player.playTurn(gameView);
        });
        calculateMeeplePoints();
        System.out.println("Partie terminée !");
        gameView.displayFinalScores();
    }

    private boolean isGameOver() {
        return players.values().stream().anyMatch(player -> player.getWagonsRemaining() < 3);
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
        List<Player> firstWinners = new ArrayList<>();
        List<Player> secondWinners = new ArrayList<>();

        determineMeepleWinners(meepleColor, firstWinners, secondWinners);
        awardMeeplePoints(firstWinners, secondWinners);
    }

    /**
     * Détermine les joueurs ayant le plus et le deuxième plus grand nombre de meeples d'une couleur donnée.
     */
    private void determineMeepleWinners(Colors meepleColor, List<Player> firstWinners, List<Player> secondWinners) {
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
}
