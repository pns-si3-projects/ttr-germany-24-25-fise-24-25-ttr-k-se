package fr.cotedazur.univ.polytech.teamK.board.cards;

/**
 * Cette classe est une classe abstraite, qui permet de rassembler les attributs et
 * méthodes communes de WagonCard et DestinationCard.
 */
public abstract class Card {
    private TypeOfCards type;
    private static int COUNT = 0; // Compteur partagé et statique
    private final int id;

    public Card(TypeOfCards type) {
        this.type = type;
        this.id = COUNT++;
    }
    /**
     * @return l'identifiant de la carte.
     */
    public int getId() { return id; }

    /**
     * @return le type de la carte.
     */
    public TypeOfCards getType() { return type; }

    @Override
    public String toString() {
        return getType().toString();
    }
}
