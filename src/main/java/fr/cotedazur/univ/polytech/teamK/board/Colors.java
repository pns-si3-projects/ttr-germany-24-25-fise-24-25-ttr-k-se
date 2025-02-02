package fr.cotedazur.univ.polytech.teamK.board;

public enum Colors {
    BLACK,
    BLUE,
    RED,
    WHITE,
    YELLOW,
    GREEN,
    PINK,
    GRAY,
    ORANGE,
    RAINBOW;

    public Colors getColorById (int id) {
        return switch (id) {
            case 0 -> Colors.BLACK;
            case 1 -> Colors.BLUE;
            case 2 -> Colors.RED;
            case 3 -> Colors.WHITE;
            case 4 -> Colors.YELLOW;
            case 5 -> Colors.GREEN;
            case 6 -> Colors.PINK;
            case 7 -> Colors.GRAY;
            case 8 -> Colors.ORANGE;
            default -> Colors.RAINBOW;
        };
    }
}
