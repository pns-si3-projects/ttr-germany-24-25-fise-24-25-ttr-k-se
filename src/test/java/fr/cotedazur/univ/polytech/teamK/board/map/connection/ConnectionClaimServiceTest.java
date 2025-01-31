package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionClaimServiceTest {
    @BeforeEach
    void setUp(){
        City city1 = new City("Hanover", 4);
        City city2 = new City("Magdeburg", 4);
        Connection connection = new Connection(city1, city2, 3, Colors.BLUE);
        Player player = new Player("Zaynab");
        ConnectionClaimService connectionClaimService = new ConnectionClaimService();
        Board gameMap = new Board("Reich");
    }
}