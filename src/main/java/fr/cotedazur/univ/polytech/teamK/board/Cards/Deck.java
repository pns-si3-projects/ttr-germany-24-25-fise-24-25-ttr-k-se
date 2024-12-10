package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

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

    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Ajoute au paquet des cartes.
     *
     * @param initialCards une liste de carte
     */
    public void initialize(List<T> initialCards) {
        if (initialCards == null || initialCards.isEmpty()) throw new IllegalArgumentException("Cards cannot be empty");
        this.cards.addAll(initialCards);
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
