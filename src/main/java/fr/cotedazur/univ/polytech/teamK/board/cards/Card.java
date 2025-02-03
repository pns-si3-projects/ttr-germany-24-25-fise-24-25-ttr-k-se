package fr.cotedazur.univ.polytech.teamK.board.cards;

public abstract class Card {
    private final TypeOfCards type;
    private static int COUNT = 0; // Compteur partagé et statique
    private final int id;

    public Card(TypeOfCards type) {
        this.type = type;
        this.id = COUNT++;
    }

    public int getId() { return id; }
    public TypeOfCards getType() { return type; }

    @Override
    public String toString() {
        return getType().toString();
    }
}
