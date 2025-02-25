package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This gameView class provides an interface for retreiving game-related data.
 * it acts as a bridge between the game engine and the UI components,
 * allowing access to player information, game state, and available ressources.
 */
public class GameView{
    private GameEngine gameEngine;
    private Bot currentBot; 

    public GameView(GameEngine gameEngine, Bot player) {
        this.gameEngine = gameEngine;
        this.currentBot = player;
    }

    public List<String> getPlayerNames() {
        return gameEngine.getPlayers().values()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public GameBoard getGameMap () {
        GameBoard res = new GameBoard("");
        res = gameEngine.getGameMap();
        return res;
    }

    public ArrayList<String> getAllBotName () {
        ArrayList<String> res = new ArrayList<>();
        for(Bot bot : gameEngine.getAllBot()) {
            res.add(bot.getName());
        }
        return res;
    }

    public Deck<DestinationCard> getShortDestination() {
        Deck<DestinationCard> res= new Deck<>(TypeOfCards.SHORT_DESTINATION,gameEngine.getGameMap());
        res.putAll(gameEngine.getShortDestinationDeck());
        return res;
    }



    public Deck<DestinationCard> getLongueDestination() {
        Deck<DestinationCard> res= new Deck<>(TypeOfCards.LONG_DESTINATION,gameEngine.getGameMap());
        res.putAll(gameEngine.getLongDestinationDeck());
        return res;
    }

    public Deck<WagonCard> getWagonDeck() {
        Deck<WagonCard> res = new Deck<>(TypeOfCards.WAGON,gameEngine.getGameMap());
        res.putAll(gameEngine.getWagonDeck());
        return res;
    }

    public Integer getNumberPlayer () {
        return gameEngine.getNumberPlayer();
    }

    public Player getPlayerByBot (Bot bot) {
        //Player res =new Player("");
        Player res = gameEngine.getPlayerByBot(bot);
        return res;
    }

    public Set<Bot> getAllBot () {
        Set<Bot> res = new HashSet<>();
        res =gameEngine.getAllBot();
        return res;
    }

    public Bot getBotByName (String name) {
        for(Bot bot : gameEngine.getAllBot()) {
            if(Objects.equals(bot.getName(), name)) {
                return bot;
            }
        }
        throw new InvalidParameterException();
    }

    public String getMyName() {return gameEngine.getPlayerByBot(currentBot)
            .getName();
    }
    public int getMyScore() {return gameEngine.getPlayerByBot(currentBot)
            .getScore();
    }
    public ArrayList<DestinationCard> getMyDestinationCards() {
        ArrayList<DestinationCard> res = new ArrayList<>();
        res.addAll(gameEngine.getPlayerByBot(currentBot).getCartesDestination());
        return res;
    }
    public ArrayList<WagonCard> getMyWagonCards() {return gameEngine.getPlayerByBot(currentBot)
            .getCartesWagon();
    }
    public int getNumberShortDest () {return gameEngine.getShortDestinationDeck().getRemainingCards();}

    public int getNumberLongueDest () {return gameEngine.getLongDestinationDeck().getRemainingCards();}

    public int getMyWagonsRemaining() {return gameEngine.getPlayerByBot(currentBot)
            .getWagonsRemaining();
    }
    public int getMyNumberWagonCards() {return gameEngine.getPlayerByBot(currentBot)
            .getCartesWagon().size();
    }
    public int getMyNumberDestination () {return gameEngine.getPlayerByBot(currentBot)
            .getCartesDestination().size();
    }
    public Meeple getMyMeeples() {return gameEngine.getPlayerByBot(currentBot)
            .getMeeples();
    }
    public int getMyNumberOfMeeples() {return gameEngine.getPlayerByBot(currentBot)
            .getMeeples().getNumber();
    }
    public ArrayList<Connection> getMyConnections() {return gameEngine.getPlayerByBot(currentBot)
            .getConnections();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(gameEngine.getPlayers().values());
    }

    public Integer getRound()
    {
        return gameEngine.getRound();
    }

    public List<WagonCard> getVisibleWagonCards() {
        return gameEngine.getWagonDeck().getVisibleCard();
    }

}
