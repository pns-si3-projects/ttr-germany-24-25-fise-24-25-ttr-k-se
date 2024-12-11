package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;

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

    public Deck(TypeOfCards type) { initialize(type); }

    /**
     * Creer un paquet de cartes.
     *
     * @param type le type de la carte
     */
    public void initialize(TypeOfCards type) {
        if (type == TypeOfCards.DESTINATION) {
            this.cards = new ArrayList<>(89);
            //DESTINATIONS COURTES
            this.cards.add((T) new DestinationCard(Cities.MANNHEIM, Cities.STUTTGART, 2));
            this.cards.add((T) new DestinationCard(Cities.MAINZ, Cities.STUTTGART, 3));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.SAARBRUCKEN, 4));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.LEIPZIG, 4));
            this.cards.add((T) new DestinationCard(Cities.FRANKFURT, Cities.STUTTGART, 4));
            this.cards.add((T) new DestinationCard(Cities.KARLSRUHE, Cities.AUGSBURG, 4));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.ROSTOCK, 4));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.FRANKFURT, 4));
            this.cards.add((T) new DestinationCard(Cities.MANNHEIM, Cities.WURZBURG, 4));
            this.cards.add((T) new DestinationCard(Cities.BREMEN, Cities.DUSSELDORF, 5));
            this.cards.add((T) new DestinationCard(Cities.MUNCHEN, Cities.STUTTGART,5));
            this.cards.add((T) new DestinationCard(Cities.EMDEN, Cities.HAMBURG, 6));
            this.cards.add((T) new DestinationCard(Cities.MUNCHEN, Cities.KONSTANZ, 6));
            this.cards.add((T) new DestinationCard(Cities.ERFURT, Cities.WURZBURG, 6));
            this.cards.add((T) new DestinationCard(Cities.BREMEN, Cities.KASSEL, 6));
            this.cards.add((T) new DestinationCard(Cities.HANNOVER, Cities.LEIPZIG, 6));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.CHEMNITZ, 6));
            this.cards.add((T) new DestinationCard(Cities.DORTMUND, Cities.MANNHEIM, 6));
            this.cards.add((T) new DestinationCard(Cities.MUNCHEN, Cities.WURZBURG, 7));
            this.cards.add((T) new DestinationCard(Cities.NURNBERG, Cities.STUTTGART, 7));
            this.cards.add((T) new DestinationCard(Cities.DORTMUND, Cities.ERFURT, 7));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.KASSEL, 7));
            this.cards.add((T) new DestinationCard(Cities.BREMERHAVEN, Cities.KOLN, 7));
            this.cards.add((T) new DestinationCard(Cities.HANNOVER, Cities.FRANKFURT, 7));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.ERFURT, 7));
            this.cards.add((T) new DestinationCard(Cities.KOBLENZ, Cities.ULM, 7));
            this.cards.add((T) new DestinationCard(Cities.LEIPZIG, Cities.NURNBERG, 7));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.BERLIN, 7));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.NURNBERG, 8));
            this.cards.add((T) new DestinationCard(Cities.NIEDERLANDE, Cities.FRANKFURT, 8));
            this.cards.add((T) new DestinationCard(Cities.FRANKREICH, Cities.MUNCHEN, 8));
            this.cards.add((T) new DestinationCard(Cities.MUNCHEN, Cities.FREIBURG, 8));
            this.cards.add((T) new DestinationCard(Cities.FRANKFURT, Cities.LINDAU, 8));
            this.cards.add((T) new DestinationCard(Cities.FRANKFURT, Cities.MUNCHEN, 9));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.KOLN, 9));
            this.cards.add((T) new DestinationCard(Cities.KARLSRUHE, Cities.REGENSBURG, 9));
            this.cards.add((T) new DestinationCard(Cities.NIEDERLANDE, Cities.KARLSRUHE, 9));
            this.cards.add((T) new DestinationCard(Cities.MUNSTER, Cities.STUTTGART, 9));
            this.cards.add((T) new DestinationCard(Cities.DORTMUND, Cities.MAGDEBURG, 9));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.LEIPZIG, 10));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.SCHWEIZ, 10));
            this.cards.add((T) new DestinationCard(Cities.LEIPZIG, Cities.FRANKFURT, 10));
            this.cards.add((T) new DestinationCard(Cities.DANEMARK, Cities.BERLIN, 10));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.KOBLENZ, 10));
            this.cards.add((T) new DestinationCard(Cities.FRANKFURT, Cities.OSTERREICH, 10));
            this.cards.add((T) new DestinationCard(Cities.DUSSELDORF, Cities.KONSTANZ, 10));
            this.cards.add((T) new DestinationCard(Cities.DANEMARK, Cities.NIEDERLANDE, 10));
            this.cards.add((T) new DestinationCard(Cities.BREMEN, Cities.BERLIN, 10));
            this.cards.add((T) new DestinationCard(Cities.KASSEL, Cities.FREIBURG, 10));
            this.cards.add((T) new DestinationCard(Cities.HANNOVER, Cities.SAARBRUCKEN, 11));
            this.cards.add((T) new DestinationCard(Cities.LEIPZIG, Cities.MUNCHEN, 11));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.FRANKFURT, 11));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.MUNCHEN, 11));
            this.cards.add((T) new DestinationCard(Cities.MAGDEBURG, Cities.KOLN, 11));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.REGENSBURG, 11));
            //DESTINATIONS LONGUES
            this.cards.add((T) new DestinationCard(Cities.NIEDERLANDE, Cities.BERLIN, 13));
            this.cards.add((T) new DestinationCard(Cities.DORTMUND, Cities.MUNCHEN, 13));
            this.cards.add((T) new DestinationCard(Cities.LEIPZIG, Cities.ULM, 12));
            this.cards.add((T) new DestinationCard(Cities.BREMERHAVEN, Cities.FRANKREICH, 12));
            this.cards.add((T) new DestinationCard(Cities.KOLN, Cities.LEIPZIG, 12));
            this.cards.add((T) new DestinationCard(Cities.SCHWERIN, Cities.KOBLENZ, 12));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.DRESDEN, 12));
            this.cards.add((T) new DestinationCard(Cities.DRESDEN, Cities.AUGSBURG, 12));
            this.cards.add((T) new DestinationCard(Cities.KIEL, Cities.NURNBERG, 15));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.KOLN, 14));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.KARLSRUHE, 14));
            this.cards.add((T) new DestinationCard(Cities.MUNSTER, Cities.MUNCHEN, 14));
            this.cards.add((T) new DestinationCard(Cities.LEIPZIG, Cities.STUTTGART, 14));
            this.cards.add((T) new DestinationCard(Cities.SCHWERIN, Cities.FRANKFURT, 13));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.DUSSELDORF, 13));
            this.cards.add((T) new DestinationCard(Cities.FRANKREICH, Cities.LEIPZIG, 15));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.MUNCHEN, 15));
            this.cards.add((T) new DestinationCard(Cities.MUNSTER, Cities.OSTERREICH, 15));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.MAINZ, 15));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.STUTTGART, 15));
            this.cards.add((T) new DestinationCard(Cities.EMDEN, Cities.FREIBURG, 15));
            this.cards.add((T) new DestinationCard(Cities.EMDEN, Cities.OSTERREICH, 19));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.STUTTGART, 18));
            this.cards.add((T) new DestinationCard(Cities.HAMBURG, Cities.MUNCHEN, 18));
            this.cards.add((T) new DestinationCard(Cities.FRANKREICH, Cities.DANEMARK, 17));
            this.cards.add((T) new DestinationCard(Cities.KIEL, Cities.STUTTGART, 17));
            this.cards.add((T) new DestinationCard(Cities.BREMERHAVEN, Cities.REGENSBURG, 16));
            this.cards.add((T) new DestinationCard(Cities.DRESDEN, Cities.SAARBRUCKEN, 16));
            this.cards.add((T) new DestinationCard(Cities.BERLIN, Cities.SCHWEIZ, 20));
            this.cards.add((T) new DestinationCard(Cities.ROSTOCK, Cities.KONSTANZ, 22));
            this.cards.add((T) new DestinationCard(Cities.ROSTOCK, Cities.OSTERREICH, 22));
            this.cards.add((T) new DestinationCard(Cities.DANEMARK, Cities.LINDAU, 22));
            this.cards.add((T) new DestinationCard(Cities.KIEL, Cities.SCHWEIZ, 20));
        }
        //créer 12 cartes wagons par couleur et 14 cartes locomotives
        if (type == TypeOfCards.WAGON) {
            this.cards = new ArrayList<>(110);

            for (Colors color : Colors.values()) {
                for (int i = 0; i < 12; i++) {
                    this.cards.add((T) new WagonCard(color));
                }
            }
            for (int i = 0; i < 14; i++) {
                this.cards.add((T) new WagonCard(Colors.RAINBOW));
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
     * Ajoute une carte au paquet.
     *
     * @param card
     */
    public void addCard(T card) {
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
