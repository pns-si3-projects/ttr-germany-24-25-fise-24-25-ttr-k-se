package fr.cotedazur.univ.polytech.teamK.board.Cards;

/**
 * Cette classe est une classe abstraite, qui permet de rassembler les attributs et
 * méthodes communes de WagonCard et DestinationCard.
 */
public abstract class Card {
    private TypeOfCards type;
    private int id;

    public Card(TypeOfCards type, int id) {
        this.type = type;
        setId(id);
    }

    /**
     * Attribue un identifiant pour pouvoir identifier les cartes.
     *
     * @param id
     */
    private void setId(int id){
        if(id <= 0) throw new IllegalArgumentException("Id must be greater than 0");
        this.id = id;
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
