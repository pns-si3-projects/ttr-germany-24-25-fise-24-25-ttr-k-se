package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WagonDrawManagerTest {

    private GameView gameView;
    private Bot owner;
    private GameEngine gameEngine;
    private WagonDrawManager wagonDrawManager;

    @BeforeEach
    void setUp() {
        gameView = Mockito.mock(GameView.class);
        owner = Mockito.mock(Bot.class);
        gameEngine = Mockito.mock(GameEngine.class);
        when(owner.getGameEngine()).thenReturn(gameEngine);
        wagonDrawManager = new WagonDrawManager(gameView, owner);
    }

    @Test
    void testDrawWagonsOnListSingleColor() throws WrongPlayerException {
        WagonCard blueCard = new WagonCard(Colors.BLUE);
        when(gameView.getVisibleWagonCards()).thenReturn(Collections.singletonList(blueCard));
        when(gameEngine.drawVisibleWagonCard(anyInt())).thenReturn(blueCard);

        assertTrue(wagonDrawManager.drawWagonsOnList(Collections.singletonList(Colors.BLUE)));
        verify(gameEngine, times(2)).addWagonCard(owner, blueCard);
    }

    @Test
    void testDrawWagonsOnListTwoDifferentColors() throws WrongPlayerException {
        WagonCard redCard = new WagonCard(Colors.RED);
        WagonCard greenCard = new WagonCard(Colors.GREEN);
        when(gameView.getVisibleWagonCards()).thenReturn(Arrays.asList(redCard, greenCard));
        when(gameEngine.drawVisibleWagonCard(anyInt())).thenReturn(redCard, greenCard);

        assertTrue(wagonDrawManager.drawWagonsOnList(Arrays.asList(Colors.RED, Colors.GREEN)));
        verify(gameEngine, times(2)).addWagonCard(eq(owner), any(WagonCard.class));
        verify(owner, times(2)).displayDrawWagonCardAction();
    }

    @Test
    void testDrawWagonsOnListInvalidInput() throws WrongPlayerException {
        assertFalse(wagonDrawManager.drawWagonsOnList(Collections.emptyList()));
        assertFalse(wagonDrawManager.drawWagonsOnList(Arrays.asList(Colors.RED, Colors.BLUE, Colors.GREEN)));
    }
}