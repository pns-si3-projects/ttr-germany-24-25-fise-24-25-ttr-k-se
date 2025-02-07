package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
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

class DjikstraTest {

    MidBot bot;
    GameView gameView;
    GameEngine gameEngine;
    City kiel;
    City freiburg;
    Player player1 ;

    @BeforeEach
    public void setUp () {
        Meeple.resetMeeples();
        gameEngine = new GameEngine("Reich");
        List<Bot> bots = new ArrayList<>();
        bot = new MidBot("Mid", gameEngine);
        bots.add(bot);
        gameEngine.addBotsToPlayerMap(bots);
        gameView = bot.gameView;

        kiel =  gameView.getGameMap().getCity("Kiel");
        freiburg = gameView.getGameMap().getCity("Freiburg");

        player1 = gameView.getPlayerByBot(bot);
    }

    @Test
    public void testFindBestPath() {

        ArrayList<Connection> way = Djikstra.djikstra(kiel, freiburg,bot);
        assert way != null;
        assertEquals(11, way.size());
    }

    @Test
    public void testFindOtherWay () {
        City karlsruhe = gameView.getGameMap().getCity("Karlsruhe");
        gameView.getGameMap().getNeighbourConnection(freiburg,karlsruhe).setFree(false);
        ArrayList<Connection> way = Djikstra.djikstra(kiel, freiburg,bot,false);
        assert way != null;
        assertEquals(11, way.size());
    }

    @Test
    public void testFindWayWithOwnedConnection() {
        City hannover = gameView.getGameMap().getCity("Hannover");
        Connection connection1 = gameView.getGameMap().getNeighbourConnection(kiel, gameView.getGameMap().getCity("Hamburg"));
        Connection connection2 = gameView.getGameMap().getNeighbourConnection(hannover,gameView.getGameMap().getCity("Hamburg"));

        player1.addCardWagon(new WagonCard(Colors.BLACK));
        player1.addCardWagon(new WagonCard(Colors.BLACK));

        for(int i=0 ; i<4 ; i++) {
            player1.addCardWagon(new WagonCard(Colors.RED));
        }
        assertTrue(player1.buyRail(connection1,gameView.getGameMap(),5));
        assertTrue(player1.buyRail(connection2,gameView.getGameMap(),5));
        ArrayList<Connection> way = Djikstra.djikstra(kiel, freiburg,bot);
        assert way != null;
        assertEquals(7,way.size());
    }

}