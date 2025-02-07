package fr.cotedazur.univ.polytech.teamK.bot.overlap;


import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
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

import static org.junit.jupiter.api.Assertions.*;

class PathManagerTest {
    GameView gameView;
    GameEngine gameEngine;
    Bot owner;
    DestinationCard destinationCardDusselErfurt;
    DestinationCard destinationCardHannoverDresden;

    private DestinationCard setUpCityToCity(String startName, String endName, Integer value){
        City start = gameView.getGameMap().getCity(startName);
        City end = gameView.getGameMap().getCity(endName);
        return new DestinationCard(start, end, value);

    }
    private void addWagonCardsToOwner(Colors color, Integer number) throws WrongPlayerException {
        for (int added = 0; added < number; added++)
        {
            WagonCard wagonCardToAdd = new WagonCard(color);
            gameView.getPlayerByBot(owner).addCardWagon(wagonCardToAdd);
        }
    }
    private void buyRail(Connection tobuy) throws WrongPlayerException {
        Colors colorToAdd = tobuy.getColor();
        Integer numberToAdd = tobuy.getLength();
        addWagonCardsToOwner(colorToAdd, numberToAdd);
        gameEngine.setCurrentBot(owner);
        gameEngine.buyRail(owner, tobuy);
    }
    private Connection findConnection(String cityOne, String cityTwo, Colors color)
    {
        List<Connection> neighbours = gameView.getGameMap().getCitiesConnections(cityOne);
        for (Connection connection : neighbours)
        {
            if ((connection.getCityTwo().getName().equals(cityTwo) || connection.getCityOne().getName().equals(cityTwo)) && (connection.getColor().equals(color)))
            {
                return connection;
            }
        }
        return null;
    }

    @BeforeEach
    public void setUp() throws WrongPlayerException {
        Meeple.resetMeeples();
        gameEngine = new GameEngine("Reich");
        List<Bot> bots = new ArrayList<>();
        owner = new BotOverlap("TestingBotNumberOne", gameEngine);
        bots.add(owner);
        gameEngine.addBotsToPlayerMap(bots);
        gameView = owner.gameView;
        destinationCardDusselErfurt = setUpCityToCity("Dusseldorf", "Erfurt", 10);
        destinationCardHannoverDresden = setUpCityToCity("Hannover", "Dresden", 10);
    }

    @Test
    public void testPathManager() throws WrongPlayerException {
        PathManager testInstance = new PathManager(destinationCardDusselErfurt, owner, owner.gameView);
        assertNotNull(testInstance);
        assertTrue(destinationCardDusselErfurt.equals(testInstance.getDestCardOfpath()));

    }
    @Test
    public void testConnectionToPurchaseSingleNormalColor() throws WrongPlayerException {
        PathManager pathManager = new PathManager(destinationCardDusselErfurt, owner, owner.gameView);
        addWagonCardsToOwner(Colors.RED, 1);
        List<Connection> dusselNeigh = gameView.getGameMap().getCitiesConnections("Dusseldorf");

        Connection expected = null;
        Connection connToBuy = pathManager.connectionToPurchase();

        for (Connection c : dusselNeigh) {
            if ((c.getCityOne().getName().equals("Dortmund") || c.getCityTwo().getName().equals("Dortmund")) && c.getIsFree())  {
                expected = c;
                break;
            }
        }
        assertNotNull(expected);
        assertTrue(connToBuy.equals(expected));
    }
    @Test
    public void testConnectionToPurchasNoColorsOwned() throws WrongPlayerException {
        PathManager pathManager = new PathManager(destinationCardDusselErfurt, owner, owner.gameView);
        Connection connToBuy = pathManager.connectionToPurchase();
        assertNull(connToBuy);
    }
    @Test
    public void testConnectionToPurchaseTripleNormalColor() throws WrongPlayerException {
        PathManager pathManager = new PathManager(destinationCardDusselErfurt, owner, owner.gameView);
        addWagonCardsToOwner(Colors.GREEN, 3);
        List<Connection> dusselNeigh = gameView.getGameMap().getCitiesConnections("Dusseldorf");

        Connection expected = null;
        Connection connToBuy = pathManager.connectionToPurchase();

        for (Connection c : dusselNeigh) {
            if ((c.getCityOne().getName().equals("Dortmund") || c.getCityTwo().getName().equals("Dortmund")) && c.getIsFree())  {
                expected = c;
                break;
            }
        }
        assertNotNull(expected);
        assertTrue(connToBuy.equals(expected));
    }
    @Test
    public void testConnectionToPurchaseNonGrey() throws WrongPlayerException {
        PathManager pathManager = new PathManager(destinationCardDusselErfurt, owner, owner.gameView);
        addWagonCardsToOwner(Colors.GREEN, 4);
        List<Connection> dusselNeigh = gameView.getGameMap().getCitiesConnections("Dusseldorf");

        Connection expected = null;
        Connection connToBuy = pathManager.connectionToPurchase();

        for (Connection c : dusselNeigh) {
            if ((c.getCityOne().getName().equals("Dortmund") || c.getCityTwo().getName().equals("Dortmund")) && c.getIsFree())  {
                expected = c;
                break;
            }
        }
        assertNotNull(expected);
        assertTrue(connToBuy.equals(expected));
    }

    @Test
    public void testColorsToDraw() throws WrongPlayerException {
        PathManager pathManager = new PathManager(destinationCardHannoverDresden, owner, owner.gameView);
        Colors colorToDrawFirst = pathManager.colorsToDraw().getFirst();
        Colors colorToDrawSecond = pathManager.colorsToDraw().getLast();
        assertEquals(Colors.YELLOW, colorToDrawFirst);
        assertEquals(Colors.BLACK, colorToDrawSecond);

        addWagonCardsToOwner(Colors.ORANGE, 3);
        addWagonCardsToOwner(Colors.GREEN, 3);

        colorToDrawSecond = pathManager.colorsToDraw().getLast();

        assertEquals(Colors.YELLOW, colorToDrawFirst);
        assertTrue((Colors.ORANGE == colorToDrawSecond) || (Colors.GREEN == colorToDrawSecond));
    }

    @Test
    public void testFindTotalCostRemaining() throws WrongPlayerException {
        buyRail(findConnection("Dresden", "Leipzig", Colors.BLACK));
        PathManager pathManager = new PathManager(destinationCardHannoverDresden, owner, owner.gameView);

        Integer remainingCost = pathManager.findTotalCostRemaining();
        assertEquals(6, remainingCost);
    }
}
