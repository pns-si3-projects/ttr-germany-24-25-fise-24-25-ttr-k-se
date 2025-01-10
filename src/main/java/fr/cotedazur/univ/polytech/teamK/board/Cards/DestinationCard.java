package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.City;

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
        super(TypeOfCards.DESTINATION);
        setStartCity(startCity);
        setEndCity(endCity);
        setValue(value);
        this.isComplete = false;
    }

    /**
     * Attribue une ville de départ.
     *
     * @param startCity
     */
    private void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    /**
     * Attribue une ville d'arrivée.
     *
     * @param endCity
     */
    private void setEndCity(City endCity) {
        this.endCity = endCity;
    }

    /**
     * Attribue une valeur de carte destination.
     *
     * @param value cette valeur déterminera
     *              le nombre de points que gagnera,
     *              ou perdera le joueur en fin de partie
     */
    private void setValue(int value) {
        this.value = value;
    }

    /**
     * Mets l'état de complétion de la carte en vrai.
     */
    public void setComplete() { this.isComplete = true; }

    /**
     * @return la ville de départ de la carte destination.
     */
    public City getStartCity() { return startCity; }

    /**
     * @return la ville d'arrivé de la carte destination.
     */
    public City getEndCity() { return endCity; }

    /**
     * @return la valeur de la carte destination.
     */
    public int getValue() { return value; }

    /**
     * @return l'état de complétion de la carte destination.
     */
    public boolean isComplete() { return isComplete; }

    @Override
    public String toString() {
        //return "johnny";
        return super.toString() +" "+ getStartCity()+"->"+getEndCity() +" ("+getValue()+" points)";
    }
}
