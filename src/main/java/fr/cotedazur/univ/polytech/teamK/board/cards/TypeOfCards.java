package fr.cotedazur.univ.polytech.teamK.board.cards;

public enum TypeOfCards {
    SHORT_DESTINATION("Short Destination"),
    LONG_DESTINATION("Long Destination"),
    WAGON("Wagon");

    private final String description;

    TypeOfCards(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
