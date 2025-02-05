package fr.cotedazur.univ.polytech.teamK.game;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.loggers.DetailedLogger;
import fr.cotedazur.univ.polytech.teamK.game.loggers.GamesStatisticsLogger;
import fr.cotedazur.univ.polytech.teamK.game.scores.MeeplePointsManager;
import fr.cotedazur.univ.polytech.teamK.game.scores.ScoreManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameEngine{

    private int numberOfRoundsWithoutActions = 0;
    private Board gameMap;
    private int totalGames;
    private HashMap<Bot,Player> players;
    private HashMap<Bot,GameView> viewOfPlayers;
    private Bot currentBot;
    private Player lastPlayer = null;
    private Deck<DestinationCard> shortDestinationDeck;
    private Deck<DestinationCard> longDestinationDeck;
    private Deck<WagonCard> wagonDeck;
    private GameView gameView;
    private Integer round;
    private ScoreManager scoreManager;
    private MeeplePointsManager meeplePointsManager;
    private GamesStatisticsLogger statisticsLogger;
    private DetailedLogger detailedLogger;
    private StatsAnalyse statsAnalyse;


    public GameEngine(String mapName) {
        this.gameMap = new Board(mapName);
        this.players = new HashMap<>();
        this.viewOfPlayers = new HashMap<>();
        this.round = 0;
        this.shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION, gameMap);
        this.longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, gameMap);
        this.wagonDeck = new Deck<>(TypeOfCards.WAGON, gameMap);
        this.scoreManager = new ScoreManager(this);
        this.meeplePointsManager = new MeeplePointsManager(this);
        this.statisticsLogger = new GamesStatisticsLogger(this);
        this.detailedLogger = new DetailedLogger(gameView);
    }

    /**
     * Adds bots to the player map and initializes their game views.
     *
     * @param bots the list of bots to be added to the game
     */
    public void addBotsToPlayerMap(List<Bot> bots) {
        for (Bot bot : bots) {
            Player player = new Player(bot.getName());
            gameView = new GameView(this,bot);
            bot.setGameView(gameView);
            players.put(bot,player);
            viewOfPlayers.put(bot,gameView);
        }
        statsAnalyse = new StatsAnalyse(gameView,scoreManager);
    }


    public int getNumberOfTotalGames() { return this.totalGames; }
    public HashMap<Bot, Player> getPlayers() { return players; }
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
    protected Set<Bot> getAllBot () {return players.keySet();}
    protected Player getPlayerByBot (Bot bot) {return players.get(bot);}

    /**
     * Adds a destination card to the deck.
     *
     * @param player the bot adding the destination card
     * @param destinationCard the destination card to be added
     * @throws DeckFullException if the deck is full
     */
    public void addDestinationCardToDeck(Bot player, DestinationCard destinationCard) throws DeckFullException {
        getPlayerByBot(player).removeDestinationCard(destinationCard);
        if(destinationCard.getType()==TypeOfCards.SHORT_DESTINATION) {
            shortDestinationDeck.addCard(destinationCard);
        }
        else if(destinationCard.getType()==TypeOfCards.LONG_DESTINATION) {
            longDestinationDeck.addCard(destinationCard);
        }
    }

    /**
     * Returns the number of cards of the specified color that the bot has.
     *
     * @param bot the bot whose cards are to be counted
     * @param color the color of the cards to be counted
     * @return the number of cards of the specified color that the bot has
     * @throws WrongPlayerException if the bot is not the current bot
     */
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

    public boolean buyRail(Bot bot, Connection connection) throws WrongPlayerException {
        if (confirmId(bot))
        {
            Board gameBoard = this.gameMap;
            Colors connectionColor = connection.getColor();
            Integer numberOfColorOwned = this.getPlayerByBot(bot).getNumberColor(connectionColor);
            return getPlayerByBot(bot).buyRail(connection, gameBoard, numberOfColorOwned);
        }
        return false;
    }

    /**
     * Takes meeples for the specified bot.
     *
     * @param bot the bot taking the meeples
     * @param city the city from which the meeples are to be taken
     * @param color the color of the meeples to be taken
     * @return true if the meeples were successfully taken, false otherwise
     * @throws WrongPlayerException if the bot is not the current bot
     * @throws PlayerSeenException if the player has already been seen
     */

    public boolean takeMeeples(Bot bot, City city, Colors color) throws WrongPlayerException, PlayerSeenException{
        if(confirmId(bot)){
            return getPlayerByBot(bot).takeMeeples(city, color);
        }
        return false;
    }

    /**
     * Adds a wagon card to the specified bot's hand.
     *
     * @param bot the bot adding the wagon card
     * @param wagonCard the wagon card to be added
     * @return true if the wagon card was successfully added, false otherwise
     * @throws DeckEmptyException if the deck is empty
     * @throws WrongPlayerException if the bot is not the current bot
     */
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

    /**
     * Adds a destination card to the specified bot's hand.
     *
     * @param bot the bot adding the destination card
     * @param destinationCard the destination card to be added
     * @return true if the destination card was successfully added, false otherwise
     * @throws DeckEmptyException if the deck is empty
     * @throws WrongPlayerException if the bot is not the current bot
     */
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

    /**
     * Confirms the ID of the specified bot.
     *
     * @param bot the bot whose ID is to be confirmed
     * @return true if the bot's ID is confirmed, false otherwise
     * @throws WrongPlayerException if the bot is not the current bot
     */
    public boolean confirmId(Bot bot) throws WrongPlayerException {
        if (bot.getId()!=currentBot.getId()) {
            throw new WrongPlayerException("Wrong player");
        }
        return true;
    }

    /**
     * Starts the game and manages the game rounds.
     *
     * @throws WrongPlayerException if the bot is not the current bot
     */
    public void startGame() throws WrongPlayerException {
        round = 1;
        totalGames++;
        while (lastPlayer==null) {
            lastPlayer = playRound(lastPlayer);
            round ++;
        }
        lastRound(lastPlayer);
        meeplePointsManager.calculateMeeplePoints();
        recordGameResults();
        displayEndGameMessage();
        gameView.displayFinalScores();
    }

    /**
     * Plays a round of the game.
     *
     * @param lastPlayer the last player to play in the previous round
     * @return the last player to play in the current round
     * @throws WrongPlayerException if the bot is not the current bot
     */
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

    /**
     * Plays the last round of the game.
     *
     * @param lastPlayer the last player to play in the previous round
     * @throws WrongPlayerException if the bot is not the current bot
     */
    private void lastRound(Player lastPlayer) throws WrongPlayerException {
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();
            currentBot.playTurn();
            if (lastPlayer.equals(currentPlayer)) {
                break;
            }
        }
        statsAnalyse.analyse();
    }

    /**
     * Checks if there are no more actions to be performed.
     *
     * @param numberOfPlayerWithoutActions the number of players without actions
     * @param numberOfRoundsWithoutActions the number of rounds without actions
     * @return true if there are no more actions to be performed, false otherwise
     */
    public boolean noMoreActionsCheck(int numberOfPlayerWithoutActions, int numberOfRoundsWithoutActions) {
        int NUMBER_OF_ROUNDS_WITHOUT_ACTIONS = 5;
        return (numberOfPlayerWithoutActions == getNumberPlayer()) && (numberOfRoundsWithoutActions == NUMBER_OF_ROUNDS_WITHOUT_ACTIONS);
    }

    /**
     * Checks if the game is over for the specified player.
     *
     * @param player the player to be checked
     * @return true if the game is over for the player, false otherwise
     */
    public boolean gameOver(Player player) {
        return player.getWagonsRemaining() < 3;
    }

    public void displayEndGameMessage(){
        int NUMBER_OF_ROUNDS_WITHOUT_ACTIONS = 5;
        if(numberOfRoundsWithoutActions==NUMBER_OF_ROUNDS_WITHOUT_ACTIONS+1){
            detailedLogger.logNoMoreWagons();
        }
        else{
            detailedLogger.logGameEndWagonsCardsLeft(lastPlayer.getName(), lastPlayer.getWagonsRemaining());
        }
    }

    /**
     * Returns the scores of all bots.
     *
     * @return the scores of all bots
     */
    public Map<Bot, Integer> getScores() {
        return scoreManager.getScores();
    }

    public void logGameStatistics() {
        statisticsLogger.logGameStatistics();
    }

    public Map.Entry<Player, Integer> getHighestScoreAndWinner() {
        return scoreManager.getHighestScoreAndWinner();
    }

    /**
     * Records the game results by updating the win/loss records for each bot.
     */
    public void recordGameResults() {
        Map.Entry<Player, Integer> highestScoreAndWinner = scoreManager.getHighestScoreAndWinner();
        Player winner = highestScoreAndWinner.getKey();
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            Bot bot = entry.getKey();
            Player player = entry.getValue();
            if (player.equals(winner)) {
                scoreManager.recordWin(bot);
            } else {
                scoreManager.recordLoss(bot);
            }
        }
    }


    public ScoreManager getScoreManager() {
        return scoreManager;}
}
