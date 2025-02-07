package fr.cotedazur.univ.polytech.teamK.board.cards;

public abstract class Card {
    private final TypeOfCards type;
    private static int numberOfCards = 0; // Compteur partagé et statique
    private final int id;

    /**
     * Represents an abstract card in the game.
     * Each card has a specific type and a unique ID.
     */
    protected Card(TypeOfCards type) {
        this.type = type;
        this.id = numberOfCards++;
    }

    public int getId() { return id; }

    public TypeOfCards getType() { return type; }

    @Override
    public String toString() {
        return getType().toString();
    }
}
