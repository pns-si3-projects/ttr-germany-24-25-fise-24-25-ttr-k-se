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
    private GamesStatisticsLogger statisticsLogger;
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
        this.statisticsLogger = new GamesStatisticsLogger(this);
    }

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

    public boolean buyRail(Bot bot, Connection connection) throws WrongPlayerException {
        if (confirmId(bot))
        {
            Board gameBoard = this.gameMap;
            Colors connectionColor = connection.getColor();
            Integer numberOfColorOwned = this.getPlayerByBot(bot).getNumberColor(connectionColor);
            return getPlayerByBot(bot).buyRail(connection, gameBoard, this.getNumberPlayer());
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

    public boolean confirmId(Bot bot) throws WrongPlayerException {
        if (bot.getId()!=currentBot.getId()) {
            throw new WrongPlayerException("Wrong player");
        }
        return true;
    }

    public void startGame() throws WrongPlayerException, CsvValidationException, IOException {
        round = 0;
        totalGames++;
        while (lastPlayer==null) {
            lastPlayer = playRound(lastPlayer);
            round += 1;
        }
        if (lastPlayer != null)
        {
            lastRound(lastPlayer);
        }
        scoreManager.calculateMeeplePoints();
        recordGameResults();
        displayEndGameMessage();
        gameView.displayFinalScores();
    }


    private Player playRound(Player lastPlayer) throws WrongPlayerException {
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

        return lastPlayer;
    }


    private void lastRound(Player lastPlayer) throws WrongPlayerException, CsvValidationException, IOException {
        for (Map.Entry<Bot, Player> entry : players.entrySet()) {
            currentBot = entry.getKey();
            Player currentPlayer = entry.getValue();
            currentBot.playTurn();
            if (lastPlayer.equals(currentPlayer)) {
                break;
            }
        }
        //statsAnalyse.analyse();
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

    public Map<Bot, Integer> getScores() {
        return scoreManager.getScores();
    }

    public void logGameStatistics() {
        statisticsLogger.logGameStatistics();
    }

    public Map.Entry<Player, Integer> getHighestScoreAndWinner() {
        return scoreManager.getHighestScoreAndWinner();
    }

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

    public ScoreManager getScoreManager() { return scoreManager;}
}
