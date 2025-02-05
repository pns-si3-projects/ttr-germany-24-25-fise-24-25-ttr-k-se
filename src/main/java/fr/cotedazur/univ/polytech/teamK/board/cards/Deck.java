package fr.cotedazur.univ.polytech.teamK.board.cards;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.game.GameBoard;

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
    public static final String NIEDERLANDE = "Niederlande";
    public static final String DORTMUND = "Dortmund";
    public static final String LEIPZIG = "Leipzig";
    public static final String BREMERHAVEN = "Bremerhaven";
    public static final String KOLN = "Koln";
    public static final String SCHWERIN = "Schwerin";
    public static final String HAMBURG = "Hamburg";
    public static final String DRESDEN = "Dresden";
    public static final String KIEL = "Kiel";
    public static final String BERLIN = "Berlin";
    public static final String MUNSTER = "Munster";
    public static final String FRANKREICH = "Frankreich";
    public static final String EMDEN = "Emden";
    public static final String ROSTOCK = "Rostock";
    public static final String DANEMARK = "Danemark";
    public static final String MUNCHEN = "Munchen";
    public static final String ULM = "Ulm";
    public static final String KOBLENZ = "Koblenz";
    public static final String AUGSBURG = "Augsburg";
    public static final String NURNBERG = "Nurnberg";
    public static final String FRANKFURT = "Frankfurt";
    public static final String KARLSRUHE = "Karlsruhe";
    public static final String STUTTGART = "Stuttgart";
    public static final String DUSSELDORF = "Dusseldorf";
    public static final String OSTERREICH = "Osterreich";
    public static final String MAINZ = "Mainz";
    public static final String FREIBURG = "Freiburg";
    public static final String SAARBRUCKEN = "Saarbrucken";
    public static final String SCHWEIZ = "Schweiz";
    public static final String KONSTANZ = "Konstanz";
    public static final String LINDAU = "Lindau";
    public static final String MANNHEIM = "Mannheim";
    public static final String WURZBURG = "Wurzburg";
    public static final String BREMEN = "Bremen";
    public static final String ERFURT = "Erfurt";
    public static final String KASSEL = "Kassel";
    public static final String HANNOVER = "Hannover";
    public static final String CHEMNITZ = "Chemnitz";
    public static final String REGENSBURG = "Regensburg";
    public static final String MAGDEBURG = "Magdeburg";
    private List<T> cards;
    private List<T> visibleCard;
    private int MAX_DECK_CAPACITY;


    public Deck(TypeOfCards type, GameBoard currentMap) {
        if(type==TypeOfCards.LONG_DESTINATION) MAX_DECK_CAPACITY = 34;
        if(type == TypeOfCards.SHORT_DESTINATION) MAX_DECK_CAPACITY = 55;
        if(type==TypeOfCards.WAGON) {
            MAX_DECK_CAPACITY = 110;
            visibleCard = new ArrayList<>(4);
        }
        initialize(type, currentMap);
    }

    /**
     * Create a deck of cards
     * @param type the cards type
     */
    public void initialize(TypeOfCards type, GameBoard currentMap) {
        this.cards = new ArrayList<>(MAX_DECK_CAPACITY);
        if (type == TypeOfCards.SHORT_DESTINATION) {
            initializeShortDestination(currentMap);
        }
        if (type == TypeOfCards.LONG_DESTINATION) {
            initializeLongDestination(currentMap);
        }
        if (type == TypeOfCards.WAGON) {
            initializeWagonCards();
        }
    }

    /**
     * Create the deck of 12 wagons cards per color and 14 locomotives cards
     */
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

    /**
     * Create longe destination deck
     * @param currentMap the game map
     */
    private void initializeLongDestination(GameBoard currentMap) {
        this.cards.add((T) new DestinationCard(currentMap.getCity(NIEDERLANDE), currentMap.getCity(BERLIN), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DORTMUND), currentMap.getCity(MUNCHEN), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity(LEIPZIG), currentMap.getCity(ULM), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BREMERHAVEN), currentMap.getCity(FRANKREICH), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(LEIPZIG), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity(SCHWERIN), currentMap.getCity(KOBLENZ), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(DRESDEN), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DRESDEN), currentMap.getCity(AUGSBURG), 12));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KIEL), currentMap.getCity(NURNBERG), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(KOLN), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(FRANKFURT), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(KARLSRUHE), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNSTER), currentMap.getCity(MUNCHEN), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity(LEIPZIG), currentMap.getCity(STUTTGART), 14));
        this.cards.add((T) new DestinationCard(currentMap.getCity(SCHWERIN), currentMap.getCity(FRANKFURT), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(DUSSELDORF), 13));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKREICH), currentMap.getCity(LEIPZIG), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(MUNCHEN), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNSTER), currentMap.getCity(OSTERREICH), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(MAINZ), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(STUTTGART), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(EMDEN), currentMap.getCity(FREIBURG), 15));
        this.cards.add((T) new DestinationCard(currentMap.getCity(EMDEN), currentMap.getCity(OSTERREICH), 19));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(STUTTGART), 18));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(MUNCHEN), 18));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKREICH), currentMap.getCity(DANEMARK), 17));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KIEL), currentMap.getCity(STUTTGART), 17));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BREMERHAVEN), currentMap.getCity(FREIBURG), 16));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DRESDEN), currentMap.getCity(SAARBRUCKEN), 16));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(SCHWEIZ), 20));
        this.cards.add((T) new DestinationCard(currentMap.getCity(ROSTOCK), currentMap.getCity(KONSTANZ), 22));
        this.cards.add((T) new DestinationCard(currentMap.getCity(ROSTOCK), currentMap.getCity(OSTERREICH), 22));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DANEMARK), currentMap.getCity(LINDAU), 22));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KIEL), currentMap.getCity(SCHWEIZ), 20));
    }

    /**
     * short destination
     * @param currentMap the game map
     */
    private void initializeShortDestination(GameBoard currentMap) {
        this.cards.add((T) new DestinationCard(currentMap.getCity(MANNHEIM), currentMap.getCity(STUTTGART), 2));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MAINZ), currentMap.getCity(STUTTGART), 3));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(SAARBRUCKEN), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(LEIPZIG), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKFURT), currentMap.getCity(STUTTGART), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KARLSRUHE), currentMap.getCity(AUGSBURG), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(ROSTOCK), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(FRANKFURT), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MANNHEIM), currentMap.getCity(WURZBURG), 4));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BREMEN), currentMap.getCity(DUSSELDORF), 5));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNCHEN), currentMap.getCity(STUTTGART), 5));
        this.cards.add((T) new DestinationCard(currentMap.getCity(EMDEN), currentMap.getCity(HAMBURG), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNCHEN), currentMap.getCity(KONSTANZ), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(ERFURT), currentMap.getCity(WURZBURG), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BREMEN), currentMap.getCity(KASSEL), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HANNOVER), currentMap.getCity(LEIPZIG), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(CHEMNITZ), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DORTMUND), currentMap.getCity(MANNHEIM), 6));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNCHEN), currentMap.getCity(WURZBURG), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(NURNBERG), currentMap.getCity(STUTTGART), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DORTMUND), currentMap.getCity(ERFURT), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(KASSEL), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BREMERHAVEN), currentMap.getCity(KOLN), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HANNOVER), currentMap.getCity(FRANKFURT), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BERLIN), currentMap.getCity(ERFURT), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOBLENZ), currentMap.getCity(ULM), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(LEIPZIG), currentMap.getCity(NURNBERG), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(BERLIN), 7));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(NURNBERG), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity(NIEDERLANDE), currentMap.getCity(FRANKFURT), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKREICH), currentMap.getCity(MUNCHEN), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNCHEN), currentMap.getCity(FREIBURG), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKFURT), currentMap.getCity(LINDAU), 8));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKFURT), currentMap.getCity(MUNCHEN), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(KOLN), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KARLSRUHE), currentMap.getCity(REGENSBURG), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity(NIEDERLANDE), currentMap.getCity(KARLSRUHE), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MUNSTER), currentMap.getCity(STUTTGART), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DORTMUND), currentMap.getCity(MAGDEBURG), 9));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(LEIPZIG), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(SCHWEIZ), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(LEIPZIG), currentMap.getCity(FRANKFURT), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DANEMARK), currentMap.getCity(BERLIN), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(KOBLENZ), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(FRANKFURT), currentMap.getCity(OSTERREICH), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DUSSELDORF), currentMap.getCity(KONSTANZ), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(DANEMARK), currentMap.getCity(NIEDERLANDE), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(BREMEN), currentMap.getCity(BERLIN), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KASSEL), currentMap.getCity(FREIBURG), 10));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HANNOVER), currentMap.getCity(SAARBRUCKEN), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity(LEIPZIG), currentMap.getCity(MUNCHEN), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity(HAMBURG), currentMap.getCity(FRANKFURT), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(MUNCHEN), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity(MAGDEBURG), currentMap.getCity(KOLN), 11));
        this.cards.add((T) new DestinationCard(currentMap.getCity(KOLN), currentMap.getCity(FREIBURG), 11));
    }

    /**
     * Get all the wagon visible wagon
     * @return a list of wagons cards
     */
    public List<T> getVisibleCard() {
        if (this.visibleCard.isEmpty()) {
            throw new DeckEmptyException("There is no visible cards remaining");
        }
        return visibleCard;
    }

    public boolean isEmpty()
    {
        return this.cards.isEmpty();
    }

    /**
     * Shuffle a deck of cards
     */
    public void shuffle() { Collections.shuffle(cards); }

    /**
     * draw a card form a deck
     * @return the top cards of a deck
     */
    public T draw() throws DeckEmptyException {
        if (this.cards.isEmpty()) {
            throw new DeckEmptyException("There is no wagons or deck cards remaining");
        }
        return cards.removeLast();
    }

    /**
     * Draw a card from the visible cards
     * @param index the position of the card
     * @return the card we wanted
     * @throws IllegalArgumentException if the position isn't right
     */
    public T drawVisibleCard (int index) throws IllegalArgumentException {
        if (index > 3) {
            throw new IllegalArgumentException("Invalide index");
        }
        T res = visibleCard.remove(index);

        if(cards.isEmpty()) return res;

        visibleCard.add(cards.removeFirst());
        return res;
    }

    /**
     * Add a card to a deck
     * @param card the card to add
     * @throws DeckFullException if the deck is already full
     */
    public void addCard(T card) throws DeckFullException {
        if (cards.size() == MAX_DECK_CAPACITY) {
            throw new DeckFullException("The deck is full");
        }
        cards.add(card);
    }
    /**
     * @return the number of cards remaining in a deck
     */
    public int getRemainingCards() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}