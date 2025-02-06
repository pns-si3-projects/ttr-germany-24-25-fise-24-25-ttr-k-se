package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DeckEmptyException;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SteveTest {

    private GameEngine gameEngine;
    private GameView gameView;
    private Steve steve;

    @BeforeEach
    public void setUp() {
        gameEngine = mock(GameEngine.class);
        gameView = mock(GameView.class);
        steve = new Steve("Steve", gameEngine);
        steve.setGameView(gameView);
    }

    @Test
    public void testPlayTurn_DrawDestinationCard() throws WrongPlayerException, DeckEmptyException {
        when(gameView.getMyDestinationCards()).thenReturn(new ArrayList<>());
        when(gameEngine.addDestinationCard(any(Bot.class), any(DestinationCard.class))).thenReturn(true);

        boolean result = steve.playTurn();

        assertTrue(result);
        verify(gameView, times(1)).getMyDestinationCards();
        verify(gameEngine, times(1)).addDestinationCard(any(Bot.class), any(DestinationCard.class));
    }

    @Test
    public void testPlayTurn_BuyConnection() throws WrongPlayerException, DeckEmptyException {
        List<DestinationCard> destinationCards = new ArrayList<>();
        DestinationCard destinationCard = mock(DestinationCard.class);
        City cityA = mock(City.class);
        City cityB = mock(City.class);
        when(destinationCard.getValue()).thenReturn(10);
        when(destinationCard.getStartCity()).thenReturn(cityA);
        when(destinationCard.getEndCity()).thenReturn(cityB);
        destinationCards.add(destinationCard);

        when(gameView.getMyDestinationCards()).thenReturn((ArrayList<DestinationCard>) destinationCards);
        when(gameView.getPlayerByBot(any(Bot.class))).thenReturn(mock(Player.class));
        when(gameEngine.buyRail(any(Bot.class), any(Connection.class))).thenReturn(true);

        ArrayList<Connection> path = new ArrayList<>();
        Connection connection = mock(Connection.class);
        path.add(connection);
        when(gameView.getGameMap()).thenReturn(mock(Board.class));
        when(gameView.getNumberPlayer()).thenReturn(2);
        when(steve.djikstra(anyString(), anyString())).thenReturn(path);

        boolean result = steve.playTurn();

        assertTrue(result);
        verify(gameView, times(1)).getMyDestinationCards();
        verify(gameEngine, times(1)).buyRail(any(Bot.class), any(Connection.class), any(), anyInt());
    }

    @Test
    public void testDrawDestinationCard() throws DeckEmptyException, WrongPlayerException {
        List<DestinationCard> draw = new ArrayList<>();
        DestinationCard card1 = mock(DestinationCard.class);
        DestinationCard card2 = mock(DestinationCard.class);
        DestinationCard card3 = mock(DestinationCard.class);
        DestinationCard card4 = mock(DestinationCard.class);
        draw.add(card1);
        draw.add(card2);
        draw.add(card3);
        draw.add(card4);

        when(steve.drawDestFromNumber(4)).thenReturn(draw);
        when(card1.getValue()).thenReturn(5);
        when(card2.getValue()).thenReturn(10);
        when(card3.getValue()).thenReturn(15);
        when(card4.getValue()).thenReturn(20);

        boolean result = steve.drawDestinationCard();

        assertTrue(result);
        verify(gameEngine, times(2)).addDestinationCard(any(Bot.class), any(DestinationCard.class));
    }

    @Test
    public void testDrawWagonCard() throws DeckEmptyException, WrongPlayerException {
        Deck<WagonCard> wagonDeck = mock(Deck.class);
        List<WagonCard> visibleCards = new ArrayList<>();
        WagonCard wagonCard = mock(WagonCard.class);
        visibleCards.add(wagonCard);

        when(gameView.getWagonDeck()).thenReturn(wagonDeck);
        when(wagonDeck.getVisibleCard()).thenReturn(visibleCards);
        when(wagonCard.getColor()).thenReturn(Colors.RED);
        when(gameEngine.addWagonCard(any(Bot.class), any(WagonCard.class))).thenReturn(true);

        boolean result = steve.drawWagonCard(Colors.RED);

        assertTrue(result);
        verify(gameEngine, times(1)).addWagonCard(any(Bot.class), any(WagonCard.class));
    }

    @Test
    public void testBuyConnection() throws WrongPlayerException {
        ArrayList<Connection> path = new ArrayList<>();
        Connection connection = mock(Connection.class);
        path.add(connection);

        when(gameEngine.buyRail(any(Bot.class), any(Connection.class))).thenReturn(true);
        when(gameView.getGameMap()).thenReturn(mock(Board.class));
        when(gameView.getNumberPlayer()).thenReturn(2);

        boolean result = steve.buyConnection(path);

        assertTrue(result);
        verify(gameEngine, times(1)).buyRail(any(Bot.class), any(Connection.class));
    }
}