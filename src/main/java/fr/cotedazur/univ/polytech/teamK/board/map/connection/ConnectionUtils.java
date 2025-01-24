package fr.cotedazur.univ.polytech.teamK.board.map.connection;

public class ConnectionUtils {
    /**
     * Calculates the points for a given length.
     *
     * @param length the length of the connection
     * @return the points for the given length
     * @throws IllegalArgumentException if the length is invalid
     */
    public static int calculatePoints(int length){
        switch(length){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 5:
                return 10;
            case 6:
                return 15;
            case 7:
                return 18;
            case 8:
                return 21;
            default:
                throw new IllegalArgumentException("Invalid route lenght: "+ length);
        }
    }
}
