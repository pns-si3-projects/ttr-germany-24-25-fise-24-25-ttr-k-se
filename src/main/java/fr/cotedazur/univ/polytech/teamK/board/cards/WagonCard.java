package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

/**
 * Wagon cards represent the resources the player has available to build
 * journeys on the game board. Each wagon card is associated with a color
 * specific or can be multicolor, usable as a substitute for any
 * color.
 */
public class WagonCard extends Card {
    private Colors color;

    public WagonCard(Colors color) {
        super(TypeOfCards.WAGON);
        setColor(color);
    }

    public void setColor(Colors color) {
        this.color = color;
    }
    public Colors getColor() { return color; }

    @Override
    public String toString() {
        return super.toString() + " " + getColor();
    }
}
