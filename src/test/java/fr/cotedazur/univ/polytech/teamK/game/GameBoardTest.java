package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    private GameBoard gameBoard;
    private GameBoard testGameBoard;
    private City city1 = new City("Rostock" , 1);
    private City city2 = new City("Berlin" , 5);
    private City city3 = new City("Hamburg", 4);
    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard("Reich");
        testGameBoard = new GameBoard("testMap");
    }

    @Test
    public void testCountConnectionBetweenCities() {
        int count = gameBoard.countConnectionsBetweenCities(city1, city2);
        int count2 = gameBoard.countConnectionsBetweenCities(city1, city3);
        assertTrue(count > 0);
        assertTrue(count2 == 0);
    }
}