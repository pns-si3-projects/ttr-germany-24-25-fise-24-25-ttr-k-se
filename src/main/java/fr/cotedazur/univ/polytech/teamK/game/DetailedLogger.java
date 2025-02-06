package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * This class logs the detailed information of the game including the player's names,
 * the number of players, the round, and the actions of the players.
 */
public class DetailedLogger {
    private static final Logger logger = Logger.getLogger(DetailedLogger.class.getName());
    private GameView gameView;

    public DetailedLogger(GameView gameView) {this.gameView = gameView; }

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColoredFormatter());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
    }

    //Used by the GameEngine to log the game details
    public void logGameDetails(){
        logPlayerInfo();
        logRound();
    }

    private void logPlayerInfo(){
        logger.fine("Players Names: " + gameView.getPlayerNames());
        logger.fine("Number of Players "+ gameView.getPlayerNames().size());
    }

    private void logRound(){
        logger.info("Round: " + gameView.getRound());
        for (Player player : gameView.getPlayers()){
            Bot bot = gameView.getBotByName(player.getName());
            logger.fine(bot.getName() +
                    " Score : " +bot.gameView.getMyScore() +
                    " Connections : " +bot.gameView.getMyConnections() +
                    " Meeples : " +bot.gameView.getMyMeeples() +
                    " WagonCard : " +bot.gameView.getMyWagonCards());
        }

    }

    //Called by GameEngine
    public void logNoMoreWagons(){
        logger.fine("The game is over, the bots have ran out of wagon cards.");
    }

    public void logGameEndWagonsCardsLeft(String lastPlayer, int wagonCards){
        logger.fine("The game is over, " + lastPlayer +" has " + wagonCards +" wagons left.");
    }

    //Called by Bot
    public void logDrawDestinationCard(Bot bot){
        logger.finer(bot.getName() + " draws a destination card " + "( " + gameView.getMyDestinationCards().getLast()+" )");
    }
    public void logDrawWagonCard(Bot bot){
        logger.finer(bot.getName() + " draws a wagon card !" + "( " + gameView.getMyWagonCards().getLast()+" )");
    }

    public void buyConnection(Bot bot){
        logger.finer(bot.getName() + " buys the connection " + gameView.getMyConnections().getLast());
    }
}
