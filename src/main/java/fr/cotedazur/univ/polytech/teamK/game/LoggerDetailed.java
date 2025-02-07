package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * This class logs the detailed information of the game including the player's names,
 * the number of players, the round, and the actions of the players.
 */
public class LoggerDetailed {
    private static final Logger logger = Logger.getLogger(LoggerDetailed.class.getName());
    private GameEngine gameEngine;
    private Map<Player, Integer> scoresBeforeMeeples = new HashMap<>();

    public LoggerDetailed(GameEngine gameEngine) {this.gameEngine = gameEngine; }

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new LoggerColoredFormatter());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
    }

    public void logGameStart(){
        logger.fine("All aboard the train !");
        logger.fine("Number of Players "+ gameEngine.getPlayers().size());
        StringBuilder playerName = new StringBuilder();
        for (Player player : gameEngine.getPlayers().values()) {
            playerName.append(player.getName()).append(" - ");
        }
        logger.fine("Players Names: " + playerName);
    }

    public void logRound(){
        logger.fine("Round: " + gameEngine.getRound());
        for (Bot bot : gameEngine.getPlayers().keySet()) {
            logger.fine(bot.getName() +
                    "\nScore : " +bot.gameView.getMyScore() +
                    "\nConnections : " +bot.gameView.getMyConnections() +
                    "\nMeeples : " +bot.gameView.getMyMeeples() +
                    "\nWagonCard : " +bot.gameView.getMyWagonCards() +
                    "\nDestinationCard : " + bot.gameView.getMyDestinationCards());
        }

    }


    //Called by GameEngine
    public void logFiveNoActionRounds(){
        logger.finer("No actions have been done by the bots for the last 5 rounds. Bots can no longer buy connections with their wagons. Game Over.");
    }

    public void logGameEndWagonsCardsLeft(String lastPlayer, int wagonCards){
        logger.fine("The game is over, " + lastPlayer +" has " + wagonCards +" wagons left.");
    }

    //Called by Bot
    public void logDrawDestinationCard(Bot bot){
        logger.finer(bot.getName() + " draws a destination card " + "( " + bot.gameView.getMyDestinationCards().getLast()+" )");
    }

    public void logDrawWagonCard(Bot bot){
        logger.finer(bot.getName() + " draws a wagon card !" + "( " + bot.gameView.getMyWagonCards().getLast()+" )");
    }

    public void logBuyConnection(Bot bot){
        logger.finer(bot.getName() + " buys the connection " + bot.gameView.getMyConnections().getLast());
    }

    //Used by the GameEngine to log the game details
    public void logPlayerScoresBeforeMeeples(){
        logger.finest("The game has ended.");
        logger.finest("The scores before Meeple bonus are : ");
        for(Player player : gameEngine.getPlayers().values()){
            logger.finest(player.getName() + " : " + player.getScore());
            scoresBeforeMeeples.put(player, player.getScore());
        }
    }

    public void logPlayerScoresAfterMeeples(){
        logger.finer("The scores after Meeple bonus are : ");
        for(Player player : scoresBeforeMeeples.keySet()){
            int gainedScore = 0;
            gainedScore = player.getScore() - scoresBeforeMeeples.get(player);
            logger.finer(player.getName() + " gained " + gainedScore + " bonus meeple points. His score now is " + player.getScore());
        }
    }

    public void logGameResults(){
        logger.fine("Game Results: ");
        logger.fine("The game lasted " + gameEngine.getRound() + " rounds.");
        logger.fine("The final scores are : ");
        for(Player player: gameEngine.getPlayers().values()){
            logger.fine(player.getName() + " : " + player.getScore() + player.getAchieveDestination());
        }
        logger.fine("The winner is : " + gameEngine.getHighestScoreAndWinner().getKey().getName());
    }
}
