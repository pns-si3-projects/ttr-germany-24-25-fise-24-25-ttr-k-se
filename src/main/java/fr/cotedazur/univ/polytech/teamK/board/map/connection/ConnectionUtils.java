package fr.cotedazur.univ.polytech.teamK.board.map.connection;

public class ConnectionUtils {
    /**
     * Calculates the points for a given length.
     * @param length the length of the connection
     * @return the points for the given length
     * @throws IllegalArgumentException if the length is invalid
     */
    public static int calculatePoints(int length){
        return switch (length) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 7;
            case 5 -> 10;
            case 6 -> 15;
            case 7 -> 18;
            case 8 -> 21;
            default -> throw new IllegalArgumentException("Invalid route lenght: " + length);
        };
    }
}
