package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameView{
    private GameEngine<? extends Bot> gameEngine;
    private Bot currentBot; 
    
    public GameView(GameEngine<? extends Bot> gameEngine, Bot player) {
        this.gameEngine = gameEngine;
        this.currentBot = player;
    }

    public Board getGameMap() {
        return gameEngine.getGameMap();
    }

    public HashMap<Bot, Player> getPlayers() {
        return gameEngine.getPlayers();
    }

    public String getMyName() {return gameEngine.getPlayerById(currentBot.getId())
            .getName();
    }
    public int getMyScore() {return gameEngine.getPlayerById(currentBot.getId())
            .getScore();
    }
    public ArrayList<DestinationCard> getMyDestinationCards() {return gameEngine.getPlayerById(currentBot.getId())
            .getCartesDestination();
    }
    public ArrayList<WagonCard> getMyWagonCards() {return gameEngine.getPlayerById(currentBot.getId())
            .getCartesWagon();
    }
    public int getMyWagonsRemaining() {return gameEngine.getPlayerById(currentBot.getId())
            .getWagonsRemaining();
    }
    public int getMyNumberWagon() {return gameEngine.getPlayerById(currentBot.getId())
            .getCartesWagon().size();
    }
    public int getMyNumberDestination () {return gameEngine.getPlayerById(currentBot.getId())
            .getCartesDestination().size();
    }
    public Meeple getMyMeeples() {return gameEngine.getPlayerById(currentBot.getId())
            .getMeeples();
    }
    public int getMyNumberOfMeeples() {return gameEngine.getPlayerById(currentBot.getId())
            .getMeeples().getNumber();
    }
    public ArrayList<Connection> getMyConnections() {return gameEngine.getPlayerById(currentBot.getId())
            .getConnections();
    }

    public void displayFinalScores() {
        System.out.println("Scores finaux :");
        gameEngine.getPlayers().values().forEach(player -> System.out.println(player.getName() + " : " + player.getScore() + " points"));
    }

    /**
     * savoir le score des autres
     * savoir le nombre de carte
     */
}
