package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.map.City;

import java.util.Objects;

/**
 * Destination cards have the information the player needs to score points.
 * These are the "secret" routes that the player must complete to earn points. If the player
 * does not finish the destination before the end of the game, he will lose the amount of points announced by
 * the map
 */
public class DestinationCard extends Card {
    private final City startCity;
    private final City endCity;
    private final int value;
    private boolean isComplete;

    public DestinationCard(City startCity, City endCity, int value) {
        super((value > 11)? TypeOfCards.LONG_DESTINATION : TypeOfCards.SHORT_DESTINATION);
        this.startCity = startCity;
        this.endCity = endCity;
        this.value = value;
        this.isComplete = false;
    }

    public City getStartCity() { return startCity; }
    public City getEndCity() { return endCity; }
    public int getValue() { return value; }

    /**
     * @return The completion state of a card
     */
    public boolean isComplete() { return isComplete; }

    /**
     * Set the completion state to true
     */
    public void setComplete() { this.isComplete = true; }


    @Override
    public String toString() {
        return super.toString() +" "+ getStartCity()+"->"+getEndCity() +" ("+getValue()+" points)";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final DestinationCard other = (DestinationCard) obj;
        return this.value == other.value &&
                Objects.equals(this.startCity, other.startCity) &&
                Objects.equals(this.endCity, other.endCity);}

    @Override
    public int hashCode() {
        return Objects.hash(startCity,endCity, value, isComplete);
    }
}
