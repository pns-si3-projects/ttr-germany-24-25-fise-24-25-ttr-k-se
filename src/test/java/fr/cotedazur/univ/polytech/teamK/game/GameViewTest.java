package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.InvalidParameterException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameViewTest {
    private GameEngine mockGameEngine;
    private Bot mockBot;
    private GameView gameView;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockGameEngine = mock(GameEngine.class);
        mockBot = mock(Bot.class);
        mockPlayer = mock(Player.class);
        gameView = new GameView(mockGameEngine, mockBot);

        when(mockGameEngine.getPlayerByBot(mockBot)).thenReturn(mockPlayer);
    }

    @Test
    void testGetPlayerNames() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player1.getName()).thenReturn("Alice");
        when(player2.getName()).thenReturn("Bob");

        HashMap<Bot, Player> players = new HashMap<>();
        players.put(mock(Bot.class), player1);
        players.put(mock(Bot.class), player2);

        when(mockGameEngine.getPlayers()).thenReturn(players);

        List<String> playerNames = gameView.getPlayerNames();
        assertEquals(Arrays.asList("Alice", "Bob"), playerNames);
    }

    @Test
    void testGetNumberPlayer() {
        when(mockGameEngine.getNumberPlayer()).thenReturn(4);
        assertEquals(4, gameView.getNumberPlayer());
    }

    @Test
    void testGetAllBot() {
        Set<Bot> bots = new HashSet<>(Arrays.asList(mock(Bot.class), mock(Bot.class)));
        when(mockGameEngine.getAllBot()).thenReturn(bots);

        assertEquals(2, gameView.getAllBot().size());
    }

    @Test
    void testGetBotByName() {
        Bot bot1 = mock(Bot.class);
        when(bot1.getName()).thenReturn("Bot1");

        Set<Bot> bots = new HashSet<>(Collections.singletonList(bot1));
        when(mockGameEngine.getAllBot()).thenReturn(bots);

        assertEquals(bot1, gameView.getBotByName("Bot1"));
        assertThrows(InvalidParameterException.class, () -> gameView.getBotByName("UnknownBot"));
    }

    @Test
    void testGetMyName() {
        when(mockPlayer.getName()).thenReturn("TestPlayer");
        assertEquals("TestPlayer", gameView.getMyName());
    }

    @Test
    void testGetNumberShortDest() {
        Deck<DestinationCard> shortDeck = mock(Deck.class);
        when(shortDeck.getRemainingCards()).thenReturn(5);
        when(mockGameEngine.getShortDestinationDeck()).thenReturn(shortDeck);

        assertEquals(5, gameView.getNumberShortDest());
    }

    @Test
    void testGetNumberLongDest() {
        Deck<DestinationCard> longDeck = mock(Deck.class);
        when(longDeck.getRemainingCards()).thenReturn(3);
        when(mockGameEngine.getLongDestinationDeck()).thenReturn(longDeck);

        assertEquals(3, gameView.getNumberLongueDest());
    }

    @Test
    void testGetMyWagonsRemaining() {
        when(mockPlayer.getWagonsRemaining()).thenReturn(7);
        assertEquals(7, gameView.getMyWagonsRemaining());
    }

    @Test
    void testGetMyNumberWagon() {
        List<WagonCard> wagonCards = Arrays.asList(mock(WagonCard.class), mock(WagonCard.class));
        when(mockPlayer.getCartesWagon()).thenReturn(new ArrayList<>(wagonCards));

        assertEquals(2, gameView.getMyNumberWagon());
    }

    @Test
    void testGetMyNumberDestination() {
        List<DestinationCard> destinationCards = Arrays.asList(mock(DestinationCard.class), mock(DestinationCard.class));
        when(mockPlayer.getCartesDestination()).thenReturn(new ArrayList<>(destinationCards));

        assertEquals(2, gameView.getMyNumberDestination());
    }

    @Test
    void testGetMyNumberMeeple() {
        Meeple mockMeeple = mock(Meeple.class);
        when(mockMeeple.getNumber()).thenReturn(5);
        when(mockPlayer.getMeeples()).thenReturn(mockMeeple);

        assertEquals(5, gameView.getMyNumberOfMeeples());
    }

    @Test
    void testGetPlayers() {
        HashMap<Bot, Player> players = new HashMap<>();
        players.put(mock(Bot.class), mock(Player.class));
        players.put(mock(Bot.class), mock(Player.class));

        when(mockGameEngine.getPlayers()).thenReturn(players);

        assertEquals(2, gameView.getPlayers().size());
    }

    @Test
    void testGetRound() {
        when(mockGameEngine.getRound()).thenReturn(10);
        assertEquals(10, gameView.getRound());
    }

    @Test
    void testGetVisibleWagonCards() {
        List<WagonCard> visibleCards = Arrays.asList(mock(WagonCard.class), mock(WagonCard.class));
        Deck<WagonCard> wagonDeck = mock(Deck.class);
        when(wagonDeck.getVisibleCard()).thenReturn(visibleCards);
        when(mockGameEngine.getWagonDeck()).thenReturn(wagonDeck);

        assertEquals(2, gameView.getVisibleWagonCards().size());
    }
}

