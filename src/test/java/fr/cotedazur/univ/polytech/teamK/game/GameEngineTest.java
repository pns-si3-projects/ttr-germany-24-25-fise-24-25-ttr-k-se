package fr.cotedazur.univ.polytech.teamK.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

public class GameEngineTest {

    private GameEngine gameEngine;
    private Bot mockBot;
    private Player mockPlayer;
    private DestinationCard mockDestinationCard;
    private WagonCard mockWagonCard;
    private Connection mockConnection;
    private City mockCity;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine("TestMap");
        mockBot = mock(Bot.class);
        mockPlayer = mock(Player.class);
        mockDestinationCard = mock(DestinationCard.class);
        mockWagonCard = mock(WagonCard.class);
        mockConnection = mock(Connection.class);
        mockCity = mock(City.class);

        HashMap<Bot, Player> players = new HashMap<>();
        players.put(mockBot, mockPlayer);

        gameEngine.addBotsToPlayerMap(List.of(mockBot));
    }

    @Test
    void testAddBotsToPlayerMap() {
        assertEquals(1, gameEngine.getNumberPlayer());
        assertTrue(gameEngine.getAllBot().contains(mockBot));
    }

    @Test
    void testGetNumberColor() throws Exception {
        Player realPlayer = new Player("TestPlayer");

        realPlayer.addCardWagon(new WagonCard(Colors.RED));
        realPlayer.addCardWagon(new WagonCard(Colors.RED));
        realPlayer.addCardWagon(new WagonCard(Colors.RED));

        when(mockBot.getId()).thenReturn(1);
        gameEngine.setCurrentBot(mockBot);

        gameEngine.getPlayers().put(mockBot, realPlayer);

        assertEquals(3, gameEngine.getNumberColor(mockBot, Colors.RED));
    }
}
