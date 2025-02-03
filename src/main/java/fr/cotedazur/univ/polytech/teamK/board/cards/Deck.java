package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.game.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente un paquet de cartes génériques, où les objets manipulés dans cette classe sont des cartes.
 * Cette classe permet de gérer un ensemble de cartes, offrant des fonctionnalités comme le mélange,
 * la distribution, l'ajout ou le retour des cartes dans le paquet.
 * @param <T> Le type des cartes contenues dans le paquet. T doit être une sous-classe de `Card`.
 */
public class Deck<T extends Card> {
    private List<T> cards;
    private List<T> visibleCard;
    private int MAX_CAPACITY;

    public Deck(TypeOfCards type, Board currentMap) {
        if(type==TypeOfCards.LONG_DESTINATION) MAX_CAPACITY = 34;
        if(type == TypeOfCards.SHORT_DESTINATION) MAX_CAPACITY = 55;
        if(type==TypeOfCards.WAGON) {
            MAX_CAPACITY = 110;
            visibleCard = new ArrayList<>(4);
        }
        initialize(type, currentMap);
    }

    /**
     * Creer un paquet de cartes.
     *
     * @param type le type de la carte
     */
    public void initialize(TypeOfCards type, Board currentMap) {
        this.cards = new ArrayList<>(MAX_CAPACITY);
        if (type == TypeOfCards.SHORT_DESTINATION) {
            //DESTINATIONS COURTES
            initializeShortDestination(currentMap);
        }
        if (type == TypeOfCards.LONG_DESTINATION) {
            //DESTINATIONS LONGUES
            initializeLongDestination(currentMap);
        }
        //créer 12 cartes wagons par couleur et 14 cartes locomotives
        if (type == TypeOfCards.WAGON) {
            initializeWagonCards();
        }
        //shuffle();
    }

    private void initializeWagonCards() {
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
        shuffle();
        for (int i=0 ; i<4 ; i++) {
            visibleCard.add(cards.removeFirst());
        }
    }

    private void initializeLongDestination(Board currentMap) {
        this.cards.add((T) new DestinationCard(currentMap.getCity("Niederlande"), currentMap.getCity("Berlin"), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dortmund"), currentMap.getCity("Munchen"), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Leipzig"), currentMap.getCity("Ulm"), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Bremerhaven"), currentMap.getCity("Frankreich"), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Leipzig"), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Schwerin"), currentMap.getCity("Koblenz"), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Dresden"), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dresden"), currentMap.getCity("Augsburg"), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Kiel"), currentMap.getCity("Nurnberg"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Koln"), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Frankfurt"), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Karlsruhe"), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munster"), currentMap.getCity("Munchen"), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Leipzig"), currentMap.getCity("Stuttgart"), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Schwerin"), currentMap.getCity("Frankfurt"), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Dusseldorf"), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankreich"), currentMap.getCity("Leipzig"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Munchen"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munster"), currentMap.getCity("Osterreich"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Mainz"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Stuttgart"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Emden"), currentMap.getCity("Freiburg"), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Emden"), currentMap.getCity("Osterreich"), 19));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Stuttgart"), 18));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Munchen"), 18));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankreich"), currentMap.getCity("Danemark"), 17));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Kiel"), currentMap.getCity("Stuttgart"), 17));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Bremerhaven"), currentMap.getCity("Freiburg"), 16));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dresden"), currentMap.getCity("Saarbrucken"), 16));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Schweiz"), 20));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Rostock"), currentMap.getCity("Konstanz"), 22));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Rostock"), currentMap.getCity("Osterreich"), 22));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Danemark"), currentMap.getCity("Lindau"), 22));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Kiel"), currentMap.getCity("Schweiz"), 20));
    }

    private void initializeShortDestination(Board currentMap) {
        this.cards.add((T) new DestinationCard(currentMap.getCity("Mannheim"), currentMap.getCity("Stuttgart"), 2));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Mainz"), currentMap.getCity("Stuttgart"), 3));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Saarbrucken"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Leipzig"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankfurt"), currentMap.getCity("Stuttgart"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Karlsruhe"), currentMap.getCity("Augsburg"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Rostock"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Frankfurt"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Mannheim"), currentMap.getCity("Wurzburg"), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Bremen"), currentMap.getCity("Dusseldorf"), 5));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munchen"), currentMap.getCity("Stuttgart"), 5));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Emden"), currentMap.getCity("Hamburg"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munchen"), currentMap.getCity("Konstanz"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Erfurt"), currentMap.getCity("Wurzburg"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Bremen"), currentMap.getCity("Kassel"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hannover"), currentMap.getCity("Leipzig"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Chemnitz"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dortmund"), currentMap.getCity("Mannheim"), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munchen"), currentMap.getCity("Wurzburg"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Nurnberg"), currentMap.getCity("Stuttgart"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dortmund"), currentMap.getCity("Erfurt"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Kassel"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Bremerhaven"), currentMap.getCity("Koln"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hannover"), currentMap.getCity("Frankfurt"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Berlin"), currentMap.getCity("Erfurt"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koblenz"), currentMap.getCity("Ulm"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Leipzig"), currentMap.getCity("Nurnberg"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Berlin"), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Nurnberg"), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Niederlande"), currentMap.getCity("Frankfurt"), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankreich"), currentMap.getCity("Munchen"), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munchen"), currentMap.getCity("Freiburg"), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankfurt"), currentMap.getCity("Lindau"), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankfurt"), currentMap.getCity("Munchen"), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Koln"), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Karlsruhe"), currentMap.getCity("Regensburg"), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Niederlande"), currentMap.getCity("Karlsruhe"), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Munster"), currentMap.getCity("Stuttgart"), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dortmund"), currentMap.getCity("Magdeburg"), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Leipzig"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Schweiz"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Leipzig"), currentMap.getCity("Frankfurt"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Danemark"), currentMap.getCity("Berlin"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Koblenz"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Frankfurt"), currentMap.getCity("Osterreich"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Dusseldorf"), currentMap.getCity("Konstanz"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Danemark"), currentMap.getCity("Niederlande"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Bremen"), currentMap.getCity("Berlin"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Kassel"), currentMap.getCity("Freiburg"), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hannover"), currentMap.getCity("Saarbrucken"), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Leipzig"), currentMap.getCity("Munchen"), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Hamburg"), currentMap.getCity("Frankfurt"), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Munchen"), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Magdeburg"), currentMap.getCity("Koln"), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity("Koln"), currentMap.getCity("Freiburg"), 11));
    }

    public List<T> getVisibleCard() {
        if (this.visibleCard.isEmpty()) {
            throw new PaquetVideException("Il n'y a plus de cartes visibles...");
        }
        return visibleCard;
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
    public T draw() throws PaquetVideException {
        if (this.cards.isEmpty()) {
            throw new PaquetVideException("Il n'y a plus de cartes wagons.");
        }
        return cards.removeLast();
    }

    /**
     * tire une carte visible, puis la remplace par une du paquet.
     * @param index la index-ième carte visible
     * @return la carte visible
     * @throws IllegalArgumentException
     */
    public T drawVisibleCard (int index) throws IllegalArgumentException {
        if (index > 3) {
            throw new IllegalArgumentException("Index invalide");
        }
        T res = visibleCard.remove(index);

        if(cards.isEmpty()) return res;

        visibleCard.add(cards.removeFirst());
        return res;
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