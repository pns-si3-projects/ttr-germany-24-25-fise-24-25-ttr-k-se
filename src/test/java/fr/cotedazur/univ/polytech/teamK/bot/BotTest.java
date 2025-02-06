package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    Bot bot;
    List<Bot> listBot ;
    GameEngine gameEngine = new GameEngine("Reich");
    GameView gameView ;
/*
    @BeforeEach
    void setUp () {
        Meeple.resetMeeples();
        gameEngine = new GameEngine("Reich");
        bot = new B("Dumb", gameEngine);
        listBot = new ArrayList<>();
        listBot.add(bot);
        gameEngine.addBotsToPlayerMap(listBot);
        gameView = new GameView(gameEngine,bot);
    }*/

}