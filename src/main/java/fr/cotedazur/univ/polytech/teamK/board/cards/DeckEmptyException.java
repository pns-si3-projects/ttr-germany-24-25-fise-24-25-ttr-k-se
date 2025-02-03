package fr.cotedazur.univ.polytech.teamK.board.cards;

public class DeckEmptyException extends RuntimeException {
    public DeckEmptyException(String message) {
        super(message);
    }
}