package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.MidBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameViewTest {
    private GameEngine gameEngine;
    GameView gameView;
    private Player mockPlayer;
    MidBot bot1;
    MidBot bot2;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine("Reich");
        bot1 = new MidBot("Alice", gameEngine);
        bot2 = new MidBot("Bob", gameEngine);
        List<Bot> bots = new ArrayList<>();
        bots.add(bot1);
        bots.add(bot2);
        gameEngine.addBotsToPlayerMap(bots);
        /*mockGameEngine = mock(GameEngine.class);

        mockBot = mock(Bot.class);
        mockPlayer = mock(Player.class);
        gameView = new GameView(mockGameEngine, mockBot);

        //when(mockGameEngine.getPlayerByBot(mockBot)).thenReturn(mockPlayer);*/
    }

    @Test
    void testGetPlayerNames() {
        /*Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player1.getName()).thenReturn("Alice");
        when(player2.getName()).thenReturn("Bob");

        HashMap<Bot, Player> players = new HashMap<>();
        players.put(mock(Bot.class), player1);
        players.put(mock(Bot.class), player2);

        when(mockGameEngine.getPlayers()).thenReturn(players);*/



        List<String> playerNames = bot1.gameView.getPlayerNames();
        assertTrue(playerNames.contains("Bob"));
        assertTrue(playerNames.contains("Alice"));
    }

    @Test
    void testGetNumberPlayer() {
        assertEquals(2, bot1.gameView.getNumberPlayer());
        /*when(mockGameEngine.getNumberPlayer()).thenReturn(4);
        assertEquals(4, gameView.getNumberPlayer());*/
    }

    @Test
    void testGetAllBot() {
        Set<MidBot> botSet = new HashSet<>();
        botSet.add(bot1);
        botSet.add(bot2);

        /*Set<Bot> bots = new HashSet<>(Arrays.asList(mock(Bot.class), mock(Bot.class)));
        when(mockGameEngine.getAllBot()).thenReturn(bots);*/

        assertEquals(2, bot1.gameView.getAllBot().size());
    }

    @Test
    void testGetBotByName() {
        assertEquals(bot1, bot2.gameView.getBotByName("Alice"));
        assertThrows(InvalidParameterException.class, () -> bot1.gameView.getBotByName("UnknownBot"));
        /*
        Bot bot1 = mock(Bot.class);
        when(bot1.getName()).thenReturn("Bot1");

        Set<Bot> bots = new HashSet<>(Collections.singletonList(bot1));
        when(mockGameEngine.getAllBot()).thenReturn(bots);

        assertEquals(bot1, gameView.getBotByName("Bot1"));
        assertThrows(InvalidParameterException.class, () -> gameView.getBotByName("UnknownBot"));*/
    }

    @Test
    void testGetMyName() {
        assertEquals("Alice", bot1.gameView.getMyName());
        /*when(mockPlayer.getName()).thenReturn("TestPlayer");
        assertEquals("TestPlayer", gameView.getMyName());*/
    }

    @Test
    void testGetNumberShortDest() throws WrongPlayerException {
        gameEngine.setCurrentBot(bot2);
        assertEquals(55, bot1.gameView.getNumberShortDest());
        gameEngine.drawShortDestination(bot2);
        assertEquals(54, bot1.gameView.getNumberShortDest());
        /*Deck<DestinationCard> shortDeck = mock(Deck.class);
        when(shortDeck.getRemainingCards()).thenReturn(5);
        when(mockGameEngine.getShortDestinationDeck()).thenReturn(shortDeck);

        assertEquals(5, gameView.getNumberShortDest());*/
    }

    @Test
    void testGetNumberLongDest() throws WrongPlayerException {
        gameEngine.setCurrentBot(bot2);
        assertEquals(34, bot1.gameView.getNumberLongueDest());
        gameEngine.drawLongueDestination(bot2);
        assertEquals(33, bot1.gameView.getNumberLongueDest());
        /*Deck<DestinationCard> longDeck = mock(Deck.class);
        when(longDeck.getRemainingCards()).thenReturn(3);
        when(mockGameEngine.getLongDestinationDeck()).thenReturn(longDeck);

        assertEquals(3, gameView.getNumberLongueDest());*/
    }

    @Test
    void testGetMyWagonsAndWagonsCardsRemaining() throws WrongPlayerException {
        City cityOne = bot1.gameView.getGameMap().getCity("Kiel");
        gameEngine.setCurrentBot(bot1);

        assertEquals(45, bot1.gameView.getMyWagonsRemaining());
        assertEquals(0, bot1.gameView.getMyNumberWagonCards());
        assertEquals(0,bot1.gameView.getMyNumberOfMeeples());
        assertEquals(0,bot2.gameView.getMyNumberOfMeeples());

        Connection connection1 = bot2.gameView.getGameMap().getNeighbourConnection(cityOne, bot1.gameView.getGameMap().getCity("Hamburg"));
        ArrayList<Connection> list = new ArrayList<>();
        list.add(connection1);

        bot1.gameView.getPlayerByBot(bot1).addCardWagon(new WagonCard(Colors.BLACK));
        bot1.gameView.getPlayerByBot(bot1).addCardWagon(new WagonCard(Colors.BLACK));

        assertEquals(2, bot1.gameView.getMyNumberWagonCards());
        assertEquals(0, bot2.gameView.getMyNumberWagonCards());

        assertTrue(bot1.buyConnection(list));

        assertEquals(43, bot1.gameView.getMyWagonsRemaining());
        assertEquals(45, bot2.gameView.getMyWagonsRemaining());

        assertEquals(0, bot1.gameView.getMyNumberWagonCards());
        assertEquals(1,bot1.gameView.getMyNumberOfMeeples());

        /*when(mockPlayer.getWagonsRemaining()).thenReturn(7);
        assertEquals(7, gameView.getMyWagonsRemaining());*/
    }
/*
    @Test
    void testGetMyNumberWagonCards() {
        assertEquals(0, bot1.gameView.getMyNumberWagonCards());

        List<WagonCard> wagonCards = Arrays.asList(mock(WagonCard.class), mock(WagonCard.class));
        when(mockPlayer.getCartesWagon()).thenReturn(new ArrayList<>(wagonCards));

        assertEquals(2, gameView.getMyNumberWagonCards());
    }*/

    @Test
    void testGetMyNumberDestination() throws WrongPlayerException {
        gameEngine.setCurrentBot(bot1);
        assertEquals(0, bot1.gameView.getMyNumberDestination());
        assertEquals(0, bot2.gameView.getMyNumberDestination());
        bot1.drawDestinationCard();
        assertEquals(2, bot1.gameView.getMyNumberDestination());
        assertEquals(0, bot2.gameView.getMyNumberDestination());
        /*
        List<DestinationCard> destinationCards = Arrays.asList(mock(DestinationCard.class), mock(DestinationCard.class));
        when(mockPlayer.getCartesDestination()).thenReturn(new ArrayList<>(destinationCards));

        assertEquals(2, gameView.getMyNumberDestination());*/
    }
/*
    @Test
    void testGetMyNumberMeeple() {
        Meeple mockMeeple = mock(Meeple.class);
        when(mockMeeple.getNumber()).thenReturn(5);
        when(mockPlayer.getMeeples()).thenReturn(mockMeeple);

        assertEquals(5, gameView.getMyNumberOfMeeples());
    }*/

    @Test
    void testGetPlayers() {
        List<Player> players = bot1.gameView.getPlayers();
        assertEquals(2, players.size());
        List<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }
        assertTrue(names.contains("Bob"));
        assertTrue(names.contains("Alice"));

        /*HashMap<Bot, Player> players = new HashMap<>();
        players.put(mock(Bot.class), mock(Player.class));
        players.put(mock(Bot.class), mock(Player.class));

        when(mockGameEngine.getPlayers()).thenReturn(players);

        assertEquals(2, gameView.getPlayers().size());*/
    }

    @Test
    void testGetRound() throws WrongPlayerException {
        assertEquals(0, bot1.gameView.getRound());
        gameEngine.playRound(null);
        assertEquals(1,bot2.gameView.getRound());
        assertFalse(bot1.gameView.getMyDestinationCards().isEmpty());
        gameEngine.playRound(null);
        assertEquals(2,bot2.gameView.getRound());
        assertEquals(2,bot1.gameView.getRound());

        /*
        when(mockGameEngine.getRound()).thenReturn(10);
        assertEquals(10, gameView.getRound());*/
    }

    @Test
    void testGetVisibleWagonCards() {
        assertEquals(4, bot1.gameView.getVisibleWagonCards().size());
    }
}

