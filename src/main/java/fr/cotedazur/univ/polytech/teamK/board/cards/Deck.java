package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.game.MapHash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente un paquet de cartes génériques, où les objets manipulés dans cette classe sont des cartes.
 * Cette classe permet de gérer un ensemble de cartes, offrant des fonctionnalités comme le mélange,
 * la distribution, l'ajout ou le retour des cartes dans le paquet.
 *
 * @param <T> Le type des cartes contenues dans le paquet. T doit être une sous-classe de `Card`.
 */
public class Deck<T extends Card> {
    private List<T> cards;
    private int MAX_CAPACITY;

    public Deck(TypeOfCards type, MapHash currentMap) {
        if(type==TypeOfCards.LONG_DESTINATION) MAX_CAPACITY = 34;
        if(type == TypeOfCards.SHORT_DESTINATION) MAX_CAPACITY = 55;
        if(type==TypeOfCards.WAGON) MAX_CAPACITY = 110;
        initialize(type, currentMap);
    }

    /**
     * Creer un paquet de cartes.
     *
     * @param type le type de la carte
     */
    public void initialize(TypeOfCards type, MapHash currentMap) {
        this.cards = new ArrayList<>(MAX_CAPACITY);
        if (type == TypeOfCards.SHORT_DESTINATION) {
            //DESTINATIONS COURTES
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Mannheim"), currentMap.getCities().get("Stuttgart"), 2));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Mainz"), currentMap.getCities().get("Stuttgart"), 3));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Saarbrucken"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Leipzig"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankfurt"), currentMap.getCities().get("Stuttgart"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Karlsruhe"), currentMap.getCities().get("Augsburg"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Rostock"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Frankfurt"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Mannheim"), currentMap.getCities().get("Wurzburg"), 4));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Bremen"), currentMap.getCities().get("Dusseldorf"), 5));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munchen"), currentMap.getCities().get("Stuttgart"), 5));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Emden"), currentMap.getCities().get("Hamburg"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munchen"), currentMap.getCities().get("Konstanz"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Erfurt"), currentMap.getCities().get("Wurzburg"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Bremen"), currentMap.getCities().get("Kassel"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hannover"), currentMap.getCities().get("Leipzig"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Chemnitz"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dortmund"), currentMap.getCities().get("Mannheim"), 6));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munchen"), currentMap.getCities().get("Wurzburg"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Nurnberg"), currentMap.getCities().get("Stuttgart"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dortmund"), currentMap.getCities().get("Erfurt"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Kassel"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Bremerhaven"), currentMap.getCities().get("Koln"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hannover"), currentMap.getCities().get("Frankfurt"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Erfurt"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koblenz"), currentMap.getCities().get("Ulm"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Leipzig"), currentMap.getCities().get("Nurnberg"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Berlin"), 7));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Nurnberg"), 8));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Niederlande"), currentMap.getCities().get("Frankfurt"), 8));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankreich"), currentMap.getCities().get("Munchen"), 8));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munchen"), currentMap.getCities().get("Freiburg"), 8));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankfurt"), currentMap.getCities().get("Lindau"), 8));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankfurt"), currentMap.getCities().get("Munchen"), 9));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Koln"), 9));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Karlsruhe"), currentMap.getCities().get("Regensburg"), 9));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Niederlande"), currentMap.getCities().get("Karlsruhe"), 9));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munster"), currentMap.getCities().get("Stuttgart"), 9));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dortmund"), currentMap.getCities().get("Magdeburg"), 9));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Leipzig"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Schweiz"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Leipzig"), currentMap.getCities().get("Frankfurt"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Danemark"), currentMap.getCities().get("Berlin"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Koblenz"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankfurt"), currentMap.getCities().get("Osterreich"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dusseldorf"), currentMap.getCities().get("Konstanz"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Danemark"), currentMap.getCities().get("Niederlande"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Bremen"), currentMap.getCities().get("Berlin"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Kassel"), currentMap.getCities().get("Freiburg"), 10));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hannover"), currentMap.getCities().get("Saarbrucken"), 11));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Leipzig"), currentMap.getCities().get("Munchen"), 11));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Frankfurt"), 11));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Munchen"), 11));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Magdeburg"), currentMap.getCities().get("Koln"), 11));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Freiburg"), 11));
        }else if (type == TypeOfCards.LONG_DESTINATION) {
            //DESTINATIONS LONGUES
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Niederlande"), currentMap.getCities().get("Berlin"), 13));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dortmund"), currentMap.getCities().get("Munchen"), 13));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Leipzig"), currentMap.getCities().get("Ulm"), 12));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Bremerhaven"), currentMap.getCities().get("Frankreich"), 12));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Koln"), currentMap.getCities().get("Leipzig"), 12));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Schwerin"), currentMap.getCities().get("Koblenz"), 12));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Dresden"), 12));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dresden"), currentMap.getCities().get("Augsburg"), 12));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Kiel"), currentMap.getCities().get("Nurnberg"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Koln"), 14));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Frankfurt"), 14));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Karlsruhe"), 14));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munster"), currentMap.getCities().get("Munchen"), 14));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Leipzig"), currentMap.getCities().get("Stuttgart"), 14));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Schwerin"), currentMap.getCities().get("Frankfurt"), 13));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Dusseldorf"), 13));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankreich"), currentMap.getCities().get("Leipzig"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Munchen"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Munster"), currentMap.getCities().get("Osterreich"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Mainz"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Stuttgart"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Emden"), currentMap.getCities().get("Freiburg"), 15));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Emden"), currentMap.getCities().get("Osterreich"), 19));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Stuttgart"), 18));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Hamburg"), currentMap.getCities().get("Munchen"), 18));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Frankreich"), currentMap.getCities().get("Danemark"), 17));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Kiel"), currentMap.getCities().get("Stuttgart"), 17));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Bremerhaven"), currentMap.getCities().get("Freiburg"), 16));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Dresden"), currentMap.getCities().get("Saarbrucken"), 16));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Berlin"), currentMap.getCities().get("Schweiz"), 20));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Rostock"), currentMap.getCities().get("Konstanz"), 22));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Rostock"), currentMap.getCities().get("Osterreich"), 22));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Danemark"), currentMap.getCities().get("Lindau"), 22));
            this.cards.add((T) new DestinationCard(currentMap.getCities().get("Kiel"), currentMap.getCities().get("Schweiz"), 20));
        }
        //créer 12 cartes wagons par couleur et 14 cartes locomotives
        if (type == TypeOfCards.WAGON) {
            for (Colors color : Colors.values()) {
                if(color==Colors.RAINBOW) {
                    for (int i = 0; i < 14; i++) {
                        this.cards.add((T) new WagonCard(Colors.RAINBOW));
                    }
                }
                if(color!=Colors.RAINBOW && color!=Colors.GRAY) {
                    for (int i = 0; i < 12; i++) {
                        this.cards.add((T) new WagonCard(color));
                    }
                }
            }

        }
        shuffle();
    }

    /**
     * Mélange le paquet.
     */
    public void shuffle() { Collections.shuffle(cards); }

    /**
     * Retire le dernier élément du paquet, equivalent du pop() dans les piles.
     *
     * @return le dernier élément du paquet.
     */
    public T draw() {
        if (cards.isEmpty()) return null;
        return cards.removeLast();
    }

    /**
     * Ajoute une carte au paquet seulement si la capacité maximale n'est pas atteinte.
     *
     * @param card La carte à ajouter
     */
    public void addCard(T card) throws PaquetPleinException {
        if (cards.size() == MAX_CAPACITY) {
            throw new PaquetPleinException("Le paquet est plein");
        }
        cards.add(card);
    }
    /**
     * @return le nombre de cartes restantes dans le paquet.
     */
    public int getRemainingCards() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
