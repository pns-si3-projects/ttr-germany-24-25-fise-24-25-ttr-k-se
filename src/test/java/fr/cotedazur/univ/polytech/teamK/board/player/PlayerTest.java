package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Connections connection1;
    private Connections connection2;

    @BeforeEach
    void setUp() {
        player = new Player("Alice", 10); // Player starts with 10 counters

        List<Colors> rails1 = List.of(Colors.RED, Colors.RED, Colors.RED);
        connection1 = new Connections(Cities.NETHERLANDS, Cities.DUSSELDORF, 3, rails1);

        List<Colors> rails2 = List.of(Colors.BLUE, Colors.BLUE);
        connection2 = new Connections(Cities.CHEMNITZ, Cities.NURNBERG, 2, rails2);
    }

    @Test
    void testInitialPlayerState() {
        assertEquals(0, player.getScore());
        assertEquals(10, player.getCounters());
        assertTrue(player.getClaimedConnections().isEmpty());
    }

    @Test
    void testClaimRouteSuccessfully() {
        assertTrue(player.claimRoute(connection1));
        assertEquals(7, player.getCounters()); // 10 - 3
        assertEquals(1, player.getClaimedConnections().size());
        assertTrue(connection1.isClaimed());
    }

    @Test
    void testClaimRouteAlreadyClaimed() {
        connection1.setClaimed();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> player.claimRoute(connection1));
        assertEquals("This route has already been claimed", exception.getMessage());
        assertEquals(10, player.getCounters());
    }

    @Test
    void testClaimRouteInsufficientCounters() {
        player = new Player("Bob", 2); // Player starts with only 2 counters
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> player.claimRoute(connection1));
        assertEquals("Not enough counters to claim this route", exception.getMessage());
        assertEquals(2, player.getCounters());
    }

    @Test
    void testClaimMultipleRoutes() {
        assertTrue(player.claimRoute(connection1));
        assertTrue(player.claimRoute(connection2));

        assertEquals(5, player.getCounters()); // 10 - 3 - 2
        assertEquals(2, player.getClaimedConnections().size());
        assertTrue(connection1.isClaimed());
        assertTrue(connection2.isClaimed());
    }
}
