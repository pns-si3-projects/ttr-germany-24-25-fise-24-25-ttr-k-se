package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.DumbBot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine <T extends Bot> {
    private Board gameMap;
    private HashMap<T,Player> players;
    private HashMap<T,GameView> viewOfPlayers;
    private T currentBot;
    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private GameView gameView;

    public GameEngine(String mapName) {
        this.gameMap = new Board(mapName);
        this.players = new HashMap<>();
        this.viewOfPlayers = new HashMap<>();
        //addBotToPlayerMap(players);
        this.shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION, gameMap);
        this.longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, gameMap);
        this.wagonDeck = new Deck<>(TypeOfCards.WAGON, gameMap);
    }

    public void addBotsToPlayerMap(List<T> bots) {
        for (T bot : bots) {
            Player player = new Player(bot.getName());
            gameView = new GameView(this,bot);
            players.put(bot,player);
            viewOfPlayers.put(bot,gameView);
        }
    }

    public HashMap<T, Player> getPlayers() { return players; }
    //public Player getPlayerByBot(int id) { return players.get(id); }
    /*
    INFOS RELATIVES AU BOARD
     */
    public Board getGameMap() { return gameMap; }
    public Deck<DestinationCard> getShortDestinationDeck() { return shortDestinationDeck; }
    public Deck<DestinationCard> getLongDestinationDeck() { return longDestinationDeck; }
    public Deck<WagonCard> getWagonDeck() { return wagonDeck; }
    public int getNumberPlayer () {return players.size();}
    public Player getPlayerByBot (T bot) {return players.get(bot);}


    public void addDestinationCardToDeck(T player, DestinationCard destinationCard) throws PaquetPleinException {
        getPlayerByBot(player).removeDestinationCard(destinationCard);
        if(destinationCard.getType()==TypeOfCards.SHORT_DESTINATION) {
            shortDestinationDeck.addCard(destinationCard);
        }
        else if(destinationCard.getType()==TypeOfCards.LONG_DESTINATION) {
            longDestinationDeck.addCard(destinationCard);
        }
    }

    public ArrayList<DestinationCard> getDestinationCard(T bot) throws WrongPlayerException {
        if(confirmId(bot)){
            ArrayList<DestinationCard> res = getPlayerByBot(bot).getCartesDestination();
            return res;
        }
        return null;
    }

    public int getNumberColor(T bot, Colors color) throws WrongPlayerException {
        if(confirmId(bot)){
            return getPlayerByBot(bot).getNumberColor(color);
        }
        return 0;
    }

    public boolean buyRail(T bot, Connection connection, Board board, int number) throws PaquetVideException, WrongPlayerException {
        if(confirmId(bot)) {
            getPlayerByBot(bot).buyRail(connection, board, number);
            return true;
        }
        return false;
    }

    public boolean takeMeeples(T bot, City city, Colors color) throws WrongPlayerException {
        if(confirmId(bot)){
            getPlayerByBot(bot).takeMeeples(city, color);
            return true;
        }
        return false;
    }

    public boolean addWagonCard(T bot, WagonCard wagonCard) throws PaquetVideException, WrongPlayerException {
        if(confirmId(bot)){
            getPlayerByBot(bot).addCardWagon(wagonCard);
        }
        return false;
    }

    public boolean addDestinationCard(T bot, DestinationCard destinationCard) throws PaquetVideException, WrongPlayerException {
        if(confirmId(bot)){
            getPlayerByBot(bot).addCardDestination(destinationCard);
        }
        return false;
    }

    public boolean addScore(T bot, int score) throws WrongPlayerException {
        if(confirmId(bot)){
            getPlayerByBot(bot).addScore(score);
        }
        return false;
    }

    private boolean confirmId(T bot) throws WrongPlayerException {
        if (bot.getId()!=currentBot.getId()) {
            throw new WrongPlayerException("Wrong player");
        }
        return true;
    }

    public void startGame() throws WrongPlayerException {
        Player lastPlayer = null;

        while (lastPlayer==null) {
            lastPlayer = playRound(lastPlayer);
        }
        lastRound(lastPlayer);
        calculateMeeplePoints();
        System.out.println("Partie terminée !");
        gameView.displayFinalScores();
    }

    private Player playRound(Player lastPlayer) throws WrongPlayerException {
        for (Map.Entry<T, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();
            currentBot.playTurn(gameView);

            if (lastPlayer == null && gameOver(currentPlayer)) {
                lastPlayer = currentPlayer;
            }
        }
        return lastPlayer;
    }

    private void lastRound(Player lastPlayer) throws WrongPlayerException {
        for (Map.Entry<T, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();
            currentBot.playTurn(gameView);
            if (lastPlayer.equals(currentPlayer)) {
                break;
            }
        }
    }

    public boolean gameOver(Player player) {
        return player.getWagonsRemaining() < 3;
    }




    //A METTRE DANS GAMESCORE
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
