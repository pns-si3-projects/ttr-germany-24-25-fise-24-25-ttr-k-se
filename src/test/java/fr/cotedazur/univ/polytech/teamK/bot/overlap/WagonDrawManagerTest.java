package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WagonDrawManagerTest {
    private GameEngine gameEngine;
    private GameView gameView;
    private BotOverlap owner;
    private WagonDrawManager wagonDrawManager;
    @BeforeEach
    void setUp() {
        gameEngine = mock(GameEngine.class);
        gameView = mock(GameView.class);
        owner = mock(BotOverlap.class);
        when(owner.getGameEngine()).thenReturn(gameEngine);
        wagonDrawManager = new WagonDrawManager(gameView, owner);
    }

    @Test
    void testLookForSingleVisible() throws WrongPlayerException {
        WagonCard redCard = new WagonCard(Colors.RED);
        when(gameView.getVisibleWagonCards()).thenReturn(Arrays.asList(redCard));
        when(gameEngine.drawVisibleWagonCard(0)).thenReturn(redCard);
        boolean result = wagonDrawManager.drawWagonsOnList(Arrays.asList(Colors.RED));
        assertTrue(result);
    }

    @Test
    void testLookFOrSingleColorInVisible() throws WrongPlayerException {
        WagonCard redCard = new WagonCard(Colors.RED);
        when(gameView.getVisibleWagonCards()).thenReturn(Arrays.asList(redCard));
        when(gameEngine.drawVisibleWagonCard(0)).thenReturn(redCard);
        boolean result = wagonDrawManager.drawWagonsOnList(Arrays.asList(Colors.RED));
        assertTrue(result);
    }

    @Test
    void testLookForTwoOfSame() throws WrongPlayerException {
        WagonCard redCard = new WagonCard(Colors.RED);
        when(gameView.getVisibleWagonCards()).thenReturn(Arrays.asList(redCard, redCard));
        when(gameEngine.drawVisibleWagonCard(0)).thenReturn(redCard);
        boolean result = wagonDrawManager.drawWagonsOnList(Arrays.asList(Colors.RED));
        assertTrue(result);
        verify(gameEngine, times(2)).drawVisibleWagonCard(0);
        verify(gameEngine, times(2)).addWagonCard(owner, redCard);
    }

    @Test
    void testLookForTwoDifferentColors() throws WrongPlayerException {
        WagonCard redCard = new WagonCard(Colors.RED);
        WagonCard blueCard = new WagonCard(Colors.BLUE);
        when(gameView.getVisibleWagonCards()).thenReturn(Arrays.asList(redCard, blueCard));
        when(gameEngine.drawVisibleWagonCard(0)).thenReturn(redCard);
        when(gameEngine.drawVisibleWagonCard(1)).thenReturn(blueCard);

        boolean result = wagonDrawManager.drawWagonsOnList(Arrays.asList(Colors.RED, Colors.BLUE));

        assertTrue(result);
        verify(gameEngine, times(1)).drawVisibleWagonCard(0);
        verify(gameEngine, times(1)).drawVisibleWagonCard(1);
        verify(gameEngine, times(1)).addWagonCard(owner, redCard);
        verify(gameEngine, times(1)).addWagonCard(owner, blueCard);
    }

    @Test
    void testDrawWagonsOnListInvalidInput() throws WrongPlayerException {
        boolean result = wagonDrawManager.drawWagonsOnList(Arrays.asList());

        assertFalse(result);
    }
}