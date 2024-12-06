package fr.cotedazur.univ.polytech.teamK.board.Cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WagonCards {
    private String wagon_type;
    private String wagon_color;
    private List<Colors> cards;

    public WagonCards(String wagon_type, String wagon_color, List<Colors> cards) {
        this.wagon_type = wagon_type;
        this.wagon_color = wagon_color;
        this.cards = cards;
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        for (Colors color : Colors.values()) {
            if (color != Colors.RAINBOW) {
                for (int i = 0; i < 12; i++) {
                    cards.add(color);
                }
            } else {
                for (int i = 0; i < 14; i++) {
                    cards.add(color);
                }
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Colors drawCard() {
        if (cards.isEmpty()) {
            return null; // or throw an exception
        }
        return cards.remove(cards.size() - 1);
    }

    public void addCard(Colors card) {
        cards.add(card);
    }

    public int getRemainingCards() {
        return cards.size();
    }
}
