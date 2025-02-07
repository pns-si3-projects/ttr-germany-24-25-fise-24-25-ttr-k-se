package fr.cotedazur.univ.polytech.teamK.game;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameEngine{

    private final int NUMBER_OF_ROUNDS_WITHOUT_ACTIONS = 5;

    private String mapName;
    private int numberOfRoundsWithoutActions = 0;
    private GameBoard gameMap;
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
    private ScoreGeneralManager scoreManager;
    private ScoreMeepleManager scoreMeepleManager;
    private GamesStatisticsLogger statisticsLogger;
    private LoggerDetailed detailedLogger;
    private StatsAnalyse statsAnalyse;


    public GameEngine(String mapName) {
        this.mapName = mapName;
        this.round = 0;
        this.scoreManager = new ScoreGeneralManager(this);
        this.scoreMeepleManager = new ScoreMeepleManager(this);
        this.statisticsLogger = new GamesStatisticsLogger(this);
        this.detailedLogger = new LoggerDetailed(this);
        initializeBoard("Reich");
    }

    public void initializeBoard(String mapName){
        this.gameMap = new GameBoard(mapName);
        this.shortDestinationDeck = new Deck<>(TypeOfCards.SHORT_DESTINATION, gameMap);
        this.longDestinationDeck = new Deck<>(TypeOfCards.LONG_DESTINATION, gameMap);
        this.wagonDeck = new Deck<>(TypeOfCards.WAGON, gameMap);
    }

    /**
     * Adds bots to the player map and initializes their game views.
     *
     * @param bots the list of bots to be added to the game
     */
    public void addBotsToPlayerMap(List<Bot> bots) {
        players = new HashMap<>();
        viewOfPlayers = new HashMap<>();
        for (Bot bot : bots) {
            Player player = new Player(bot.getName());
            gameView = new GameView(this,bot);
            bot.setGameView(gameView);
            players.put(bot,player);
            viewOfPlayers.put(bot,gameView);
        }
    }


    protected HashMap<Bot, Player> getPlayers() { return players; }
    public int getNumberOfTotalGames() { return this.totalGames; }
    //public Player getPlayerByBot(int id) { return players.get(id); }
    /*
    INFOS RELATIVES AU BOARD
     */
    protected Integer getRound() { return round; }
    protected GameBoard getGameMap() { return gameMap; }
    protected Deck<DestinationCard> getShortDestinationDeck() { return shortDestinationDeck; }
    protected Deck<DestinationCard> getLongDestinationDeck() { return longDestinationDeck; }
    protected Deck<WagonCard> getWagonDeck() { return wagonDeck; }
    protected int getNumberPlayer () {return players.size();}
    protected Set<Bot> getAllBot () {return players.keySet();}
    protected Player getPlayerByBot (Bot bot) {return players.get(bot);}

    public void setCurrentBot (Bot bot) {this.currentBot = bot;}

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

    public DestinationCard drawShortDestination (Bot bot) throws WrongPlayerException {
        if(confirmId(bot)) {
            return shortDestinationDeck.draw();
        }
        return null;
    }

    public DestinationCard drawLongueDestination (Bot bot) throws WrongPlayerException {
        if(confirmId(bot)) {
            return longDestinationDeck.draw();
        }
        return null;
    }

    public WagonCard drawWagonCard () {
        return wagonDeck.draw();
    }

    public WagonCard drawVisibleWagonCard (int i) {
        return wagonDeck.drawVisibleCard(i);
    }

    public boolean buyRail(Bot bot, Connection connection) throws WrongPlayerException {
        if (confirmId(bot))
        {
            GameBoard gameBoard = this.gameMap;
            Colors connectionColor = connection.getColor();
            Integer numberOfColorOwned = this.getPlayerByBot(bot).getNumberColor(connectionColor);
            return getPlayerByBot(bot).buyRail(connection, gameBoard, this.getNumberPlayer());
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
     * @param bot             the bot adding the destination card
     * @param destinationCard the destination card to be added
     * @throws DeckEmptyException   if the deck is empty
     * @throws WrongPlayerException if the bot is not the current bot
     */
    public boolean addDestinationCard(Bot bot, DestinationCard destinationCard) throws DeckEmptyException, WrongPlayerException {
        try {
            if(confirmId(bot) && destinationCard != null) {
                getPlayerByBot(bot).addCardDestination(destinationCard);
            }
        }
        catch (NullPointerException ignored) {
            throw new NullPointerException("You can't add this card");
        }
        return false;
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
    public void startGame() throws WrongPlayerException, CsvValidationException, IOException {
        initializeBoard(mapName);
        detailedLogger.logGameStart();
        totalGames++;
        while (lastPlayer==null) {
            lastPlayer = playRound(null);
            detailedLogger.logRound();
        }
        if (lastPlayer != null)
        {
            lastRound(lastPlayer);
        }        detailedLogger.logPlayerScoresBeforeMeeples();
        scoreMeepleManager.calculateMeeplePoints();
        detailedLogger.logPlayerScoresAfterMeeples();

        scoreMeepleManager.calculateMeeplePoints();
        recordGameResults();
        statsAnalyse = new StatsAnalyse(this, gameView);
        statsAnalyse.analyse();
        displayEndGameMessage();
        lastPlayer = null;
    }

    /**
     * Plays a round of the game.
     *
     * @param lastPlayer the last player to play in the previous round
     * @return the last player to play in the current round
     * @throws WrongPlayerException if the bot is not the current bot
     */
    public Player playRound(Player lastPlayer) throws WrongPlayerException {
        int noMoreActionsCount = 0;

            for (Map.Entry<Bot, Player> entry : players.entrySet()) {
                currentBot = entry.getKey();
                Player currentPlayer = entry.getValue();
                try {
                    if (!currentBot.playTurn()) {
                        noMoreActionsCount++;
                    } else {
                        noMoreActionsCount = 0;
                    }
                }
                catch (IllegalStateException e) {
                    lastPlayer = null;
                    return lastPlayer;
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
        round ++;
        return lastPlayer;
    }

    /**
     * Plays the last round of the game.
     *
     * @param lastPlayer the last player to play in the previous round
     * @throws WrongPlayerException if the bot is not the current bot
     */
    private void lastRound(Player lastPlayer) throws WrongPlayerException, CsvValidationException, IOException {
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();
            currentBot.playTurn();
            if (lastPlayer.equals(currentPlayer)) {
                break;
            }
            for(DestinationCard destinationCard : entry.getValue().getCartesDestination()) {
                entry.getValue().addScore(-destinationCard.getValue());
            }
        }
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
        if(numberOfRoundsWithoutActions==NUMBER_OF_ROUNDS_WITHOUT_ACTIONS+1) {
            //detailedLogger.logFiveNoActionRounds();
        }
        else{
            detailedLogger.logGameEndWagonsCardsLeft(lastPlayer.getName(), lastPlayer.getWagonsRemaining());
        }
        detailedLogger.logGameResults();
    }

    public void resetTotalGames(){
        totalGames = 0;
    }

    public void valideDestination(DestinationCard card, Bot bot) throws WrongPlayerException {
        if(currentBot == bot) {
            City cityOne = card.getStartCity();
            City cityTwo = card.getEndCity();
            if(currentBot.djikstra(cityOne,cityTwo) == null) {
                gameView.getPlayerByBot(currentBot).validDestinationCardBIS(card);
            }
        } else throw new WrongPlayerException("Wrong bot");

    }

    /**
     * Returns the scores of all bots.
     *
     * @return the scores of all bots
     */
    public Map<Player, Integer> getScores() {
        return scoreManager.getScores();
    }

    public Map<String,Integer>getTotalScores() {
        return scoreManager.getTotalScores();
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
        for (Player player : players.values()) {
            if (player.equals(winner)) {
                scoreManager.recordWin(player);
            } else {
                scoreManager.recordLoss(player);
            }
            scoreManager.recordScore(player);
        }
    }

    public ScoreGeneralManager getScoreManager() { return scoreManager;}
}
