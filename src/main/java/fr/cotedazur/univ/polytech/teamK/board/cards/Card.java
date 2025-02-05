package fr.cotedazur.univ.polytech.teamK.board.cards;

public abstract class Card {
    private final TypeOfCards type;
    private static int NUMBER_OF_CARDS = 0; // Compteur partagé et statique
    private final int id;

    protected Card(TypeOfCards type) {
        this.type = type;
        this.id = NUMBER_OF_CARDS++;
    }

    public int getId() { return id; }
    public TypeOfCards getType() { return type; }

    @Override
    public String toString() {
        return getType().toString();
    }
}
