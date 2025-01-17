package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.map.City;

import java.util.Objects;

/**
 * Les cartes destinations possèdent les informations que le joueur a besoin pour marquer des points.
 * Il s'agit des trajets "secrets" que le joueur doit accomplir pour gagner des points. Si le joueur
 * ne finit pas la destination avant la fin de la partie, il perdra la quantité de point annoncé par
 * la carte.
 */
public class DestinationCard extends Card {
    private City startCity;
    private City endCity;
    private int value;
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
     * @return l'état de complétion de la carte destination.
     */
    public boolean isComplete() { return isComplete; }
    /**
     * Mets l'état de complétion de la carte en vrai.
     */
    public void setComplete() { this.isComplete = true; }


    @Override
    public String toString() {
        //return "johnny";
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
