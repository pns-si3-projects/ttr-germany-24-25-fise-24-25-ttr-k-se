package fr.cotedazur.univ.polytech.teamK.board.map.connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionUtilsTest {
    @Test
    void testCalculatePointsValidLengths() {
        assertEquals(1, ConnectionUtils.calculatePoints(1));
        assertEquals(2, ConnectionUtils.calculatePoints(2));
        assertEquals(4, ConnectionUtils.calculatePoints(3));
        assertEquals(10, ConnectionUtils.calculatePoints(5));
        assertEquals(15, ConnectionUtils.calculatePoints(6));
        assertEquals(18, ConnectionUtils.calculatePoints(7));
        assertEquals(21, ConnectionUtils.calculatePoints(8));
    }

    @Test
    void testCalculatePointsInvalidLength() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ConnectionUtils.calculatePoints(0);
        });
        assertEquals("Invalid route lenght: 0", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            ConnectionUtils.calculatePoints(11);
        });
        assertEquals("Invalid route lenght: 11", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            ConnectionUtils.calculatePoints(9);
        });
        assertEquals("Invalid route lenght: 9", exception.getMessage());
    }
}