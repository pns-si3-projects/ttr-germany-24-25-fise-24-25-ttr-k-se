package fr.cotedazur.univ.polytech.teamK.bot.overlap;


import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeepleSelectorManagerTest {
    GameView gameView;
    GameEngine gameEngine;
    Bot owner;

    @BeforeEach
    public void setUp() throws WrongPlayerException {
        Meeple.resetMeeples();
        gameEngine = new GameEngine("Reich");
        List<Bot> bots = new ArrayList<>();
        owner = new BotOverlap("TestingBotNumberOne", gameEngine);
        bots.add(owner);
        gameEngine.addBotsToPlayerMap(bots);
        gameView = owner.gameView;
        gameEngine.setCurrentBot(owner);
    }

    @Test
    public void testMeepleSelectorManager() throws WrongPlayerException {
        MeepleSelectorManager testInstance = new MeepleSelectorManager(owner, gameView);
        assertNotNull(testInstance);
    }

    @Test
    public void testMeepleSelectorManagerNoMeeples() throws WrongPlayerException {
        MeepleSelectorManager meepleSelectorManager = new MeepleSelectorManager(owner, gameView);
        Meeple newMeeples = new Meeple();

        City connStart = gameView.getGameMap().getCity("Berlin");
        City connEnd = gameView.getGameMap().getCity("Frankfurt");
        Connection testConn = new Connection(connStart, connEnd, 5, Colors.GRAY);
        int[] startMeeples = {2,1,0,  0,0,0};
        int[] endMeeples = {0,1,0,  2,0,0};
        connStart.getMeeples().setMeeples(startMeeples);
        connEnd.getMeeples().setMeeples(endMeeples);

        int[] myMeeples = {4,0,0,5,0,0};
        gameView.getPlayerByBot(owner).getMeeples().setMeeples(myMeeples);

        meepleSelectorManager.pickMeeplesFromConnection(testConn);

        int[] expectedMeeples = {5,0,0,6,0,0};

        for (int i = 0; i < 6; i++) {
            if (expectedMeeples[i] != gameView.getPlayerByBot(owner).getMeeples().getListOfOwnedMeeples()[i])
            {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }
}
