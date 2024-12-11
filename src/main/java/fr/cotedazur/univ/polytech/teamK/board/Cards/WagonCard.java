package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

/**
 * Les cartes wagons représentent les ressources dont le joueur dispose pour construire
 * des trajets sur le plateau de jeu. Chaque carte wagon est associée à une couleur
 * spécifique ou peut être multicolor, utilisable comme substitut pour n'importe quelle
 * couleur.
 */
public class WagonCard extends Card {
    private Colors color;

    public WagonCard(Colors color) {
        super(TypeOfCards.WAGON);
        setColor(color);
    }

    /**
     * Attribue une couleur à la carte wagon.
     *
     * @param color la couleur peut être RAINBOW.
     */
    public void setColor(Colors color) {
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        this.color = color;
    }

    /**
     * @return la couleur de la carte wagon.
     */
    public Colors getColor() { return color; }

    @Override
    public String toString() {
        return super.toString() + " " + getColor();
    }
}
