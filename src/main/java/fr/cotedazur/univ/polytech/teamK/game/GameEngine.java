package fr.cotedazur.univ.polytech.teamK.game;
import java.util.logging.Logger;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine{
    private static final Logger logger = Logger.getLogger(GameEngine.class.getName());

    private final int NUMBER_OF_ROUNDS_WITHOUT_ACTIONS = 5;
    private int numberOfRoundsWithoutActions = 0;
    private int totalGames = 0;
    private int gamesWon = 0;
    private int gamesLost = 0;
    private int gamesEven = 0;
    private Board gameMap;
    private HashMap<Bot,Player> players;
    private HashMap<Bot,GameView> viewOfPlayers;
    private Bot currentBot;
    private Player lastPlayer = null;
    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private GameView gameView;
    private Integer round;


    public GameEngine(String mapName) {
        this.gameMap = new Board(mapName);
        this.players = new HashMap<>();
        this.viewOfPlayers = new HashMap<>();
        //addBotBotoPlayerMap(players);
        this.shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION, gameMap);
        this.longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, gameMap);
        this.wagonDeck = new Deck<>(TypeOfCards.WAGON, gameMap);
    }

    public void addBotsToPlayerMap(List<Bot> bots) {
        for (Bot bot : bots) {
            Player player = new Player(bot.getName());
            gameView = new GameView(this,bot);
            bot.setGameView(gameView);
            players.put(bot,player);
            viewOfPlayers.put(bot,gameView);
        }
    }

    public void logGameStatistics(){
        totalGames++;
        Map<Bot, Integer> botScores = new HashMap<>();
        for(Map.Entry<Bot, Player> entry: players.entrySet()){
            Bot bot = entry.getKey();
            Player player = entry.getValue();
            int score = player.getScore();
            botScores.put(bot, botScores.getOrDefault(bot, 0)+ score);
        }
        logger.info("Total games: " + totalGames);
        if(totalGames > 0) {
            double winPercentage = (double) gamesWon / totalGames * 100;
            double lossPercentage = (double) gamesLost / totalGames * 100;
            double evenPercentage = (double) gamesEven / totalGames * 100;
            logger.info("Games won: " + (gamesWon * 100.0 / totalGames) + "%");
            logger.info("Games lost: " + (gamesLost * 100.0 / totalGames) + "%");
            logger.info("Games even: " + (gamesEven * 100 / totalGames) + "%");
        }else {
            logger.info("Games won : 0%");
            logger.info("Games lost : 0%");
        }
        for(Map.Entry<Bot, Integer> entry : botScores.entrySet()){
            Bot bot = entry.getKey();
            int totalScore = entry.getValue();
            if(totalGames > 0) {
                logger.info("Average score for " + bot.getName() + " is : " + (totalScore / totalGames));
            }else {
                logger.info("Average score for " + bot.getName() + " is : 0");
            }
        }
    }

    protected HashMap<Bot, Player> getPlayers() { return players; }
    //public Player getPlayerByBot(int id) { return players.get(id); }
    /*
    INFOS RELATIVES AU BOARD
     */
    protected Integer getRound() { return round; }
    protected Board getGameMap() { return gameMap; }
    protected Deck<DestinationCard> getShortDestinationDeck() { return shortDestinationDeck; }
    protected Deck<DestinationCard> getLongDestinationDeck() { return longDestinationDeck; }
    protected Deck<WagonCard> getWagonDeck() { return wagonDeck; }
    protected int getNumberPlayer () {return players.size();}
    protected Player getPlayerByBot (Bot bot) {return players.get(bot);}


    public void addDestinationCardToDeck(Bot player, DestinationCard destinationCard) throws DeckFullException {
        getPlayerByBot(player).removeDestinationCard(destinationCard);
        if(destinationCard.getType()==TypeOfCards.SHORT_DESTINATION) {
            shortDestinationDeck.addCard(destinationCard);
        }
        else if(destinationCard.getType()==TypeOfCards.LONG_DESTINATION) {
            longDestinationDeck.addCard(destinationCard);
        }
    }

    public int getNumberColor(Bot bot, Colors color) throws WrongPlayerException {
        if(confirmId(bot)){
            return getPlayerByBot(bot).getNumberColor(color);
        }
        return 0;
    }

    public DestinationCard drawShortDestination () {
        return shortDestinationDeck.draw();
    }

    public DestinationCard drawLongueDestination () {
        return longDestinationDeck.draw();
    }

    public boolean buyRail(Bot bot, Connection connection, Board board, int number) throws DeckEmptyException, WrongPlayerException {
        if(confirmId(bot)) {
            return getPlayerByBot(bot).buyRail(connection, board, number);
        }
        return false;
    }

    public boolean takeMeeples(Bot bot, City city, Colors color) throws WrongPlayerException, PlayerSeenException{
        if(confirmId(bot)){
            return getPlayerByBot(bot).takeMeeples(city, color);
        }
        return false;
    }

    public boolean addWagonCard(Bot bot, WagonCard wagonCard) throws DeckEmptyException, WrongPlayerException {
        try {
            if(confirmId(bot) && wagonCard != null) {
                getPlayerByBot(bot).addCardWagon(wagonCard);
            }
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    public boolean addDestinationCard(Bot bot, DestinationCard destinationCard) throws DeckEmptyException, WrongPlayerException {
        try {
            if(confirmId(bot) && destinationCard != null) {
                getPlayerByBot(bot).addCardDestination(destinationCard);
            }
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    public boolean addScore(Bot bot, int score) throws WrongPlayerException {
        if(confirmId(bot)){
            getPlayerByBot(bot).addScore(score);
        }
        return false;
    }

    private boolean confirmId(Bot bot) throws WrongPlayerException {
        if (bot.getId()!=currentBot.getId()) {
            throw new WrongPlayerException("Wrong player");
        }
        return true;
    }

    public void startGame() throws WrongPlayerException {
        round = 0;
        while (lastPlayer==null) {
            lastPlayer = playRound(lastPlayer);
            round += 1;
        }
        lastRound(lastPlayer);
        calculateMeeplePoints();
        displayEndGameMessage();
        gameView.displayFinalScores();
    }


    private Player playRound(Player lastPlayer) throws WrongPlayerException {
        int noMoreActionsCount = 0;

        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();

            if (!currentBot.playTurn()) {
                noMoreActionsCount++;
            } else {
                noMoreActionsCount = 0;
            }

            if ((lastPlayer == null && gameOver(currentPlayer)) || noMoreActionsCheck(noMoreActionsCount,numberOfRoundsWithoutActions)) {
                lastPlayer = currentPlayer;
            }
        }

        if (noMoreActionsCount == getNumberPlayer()) {
            ++numberOfRoundsWithoutActions;
        } else {
            numberOfRoundsWithoutActions = 0;
        }

        return lastPlayer;
    }


    private void lastRound(Player lastPlayer) throws WrongPlayerException {
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();
            currentBot.playTurn();
            if (lastPlayer.equals(currentPlayer)) {
                break;
            }
        }
    }

    public boolean noMoreActionsCheck(int numberOfPlayerWithoutActions, int numberOfRoundsWithoutActions) {
        return (numberOfPlayerWithoutActions == getNumberPlayer()) && (numberOfRoundsWithoutActions == NUMBER_OF_ROUNDS_WITHOUT_ACTIONS);
    }

    public boolean gameOver(Player player) {
        return player.getWagonsRemaining() < 3;
    }

    public void displayEndGameMessage(){
        if(numberOfRoundsWithoutActions==NUMBER_OF_ROUNDS_WITHOUT_ACTIONS+1){
            System.out.println("La partie est terminée, les bots ne font plus rien depuis " + NUMBER_OF_ROUNDS_WITHOUT_ACTIONS + " rounds");
        }
        else{
            System.out.println("La partie est terminée, il reste à " +lastPlayer.getName() +" " + lastPlayer.getWagonsRemaining() +" wagons.");
        }
    }
    public void displayBotInfo(Bot bot) {
        System.out.println(bot.getName() +
                " Score : " +bot.gameView.getMyScore() +
                " Connections : " +bot.gameView.getMyConnections() +
                " Meeples : " +bot.gameView.getMyMeeples() +
                " WagonCard : " +bot.gameView.getMyWagonCards());
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
