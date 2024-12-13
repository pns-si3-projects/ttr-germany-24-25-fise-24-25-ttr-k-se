package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeples;

import java.util.ArrayList;

public class Player {
    private int id ;
    private static int COUNT = 0;
    private String name ;
    private int score;
    private Meeples meeples;
    private ArrayList<WagonCard> wagonCards;
    private ArrayList<DestinationCard> destinationCards;


    public Player(String name) {
        this.id = COUNT++;
        this.name = name;
        this.score = 0;
        this.wagonCards = new ArrayList<>();
        this.destinationCards = new ArrayList<>();
        this.meeples = new Meeples();
    }

    public Player(int id, String name, ArrayList<WagonCard> wagonCards, ArrayList<DestinationCard> destinationCards) {
        this(name);
        this.wagonCards = wagonCards;
        this.destinationCards = destinationCards;
    }

    // Getteur and Setteur
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getScore() {return score;}
    public ArrayList<DestinationCard> getCartesDestination() {return destinationCards;}
    public ArrayList<WagonCard> getCartesWagon() {return wagonCards;}
    public Meeples getMeeples() {return meeples;}
    public void setMeeples(Meeples meeples) {this.meeples = meeples;}

    /**
     * Modify the socre of the player by adding a value
     * @param value the value to add to the score
     */
    public void addScore(int value) {
        this.score += value;
    }

    /**
     * Add a WagonCard to the player's hand
     * @param carte the card to add
     */
    public boolean addCardWagon(WagonCard carte) {
        this.wagonCards.add(carte);
        return true;
    }


    public boolean removeCardWagon(Colors color, int number) {
        if (getNumberColor(color) <number) {
            throw new IllegalArgumentException("The player doesn't have enough card");
        }
        for (WagonCard carte : this.wagonCards) {
            if (carte.getColor() == color && number > 0) {
                this.wagonCards.remove(carte);
                number--;
            }
        }
        return true;
    }

    /**
     * Get the number of WagonCard of a specific color
     * @param color the color to count
     * @return the number of WagonCard of the color
     */
    public int getNumberColor(Colors color) {
        int count = 0;
        for (WagonCard carte : this.wagonCards) {
            if (carte.getColor() == color) {
                count++;
            }
        }
        return count;
    }

    /**
     * Add a DestinationCard to the player's hand
     * @param carte the card to add
     * @return true if the card was added, false otherwise
     */
    public boolean addCardDestination(DestinationCard carte) {
        if (destinationCards.contains(carte)) {
            throw new IllegalArgumentException("The player already has this card");
        }
        this.destinationCards.add(carte);
        return true;
    }

    /**
     * Add DestinationCard points and remove it from the player's hand
     * @param carte the card to check
     * @return true if the card was removed, false otherwise
     */
    public boolean validDestinationCard(DestinationCard carte) {
        if (destinationCards.contains(carte)) {
            this.score += carte.getValue();
            this.destinationCards.remove(carte);
            return true;
        }
        throw new IllegalArgumentException("The player doesn't have this card");
    }

    /**
     * Transfer the neeples from a city to the player
     * @param city the city to take the neeples from
     */
    public void takeMeeples(Cities city) {
        try {
            this.meeples.transferMeeples(city.getMeeples());
        } catch (IllegalAccessException e) {
            System.out.println("Il n'y a plus de meeples dans cette ville !!!");
        }

    }

    @Override
    public String toString() {
        return "\nNom: " + getName() + "\nScore: " + getScore() + "\nCartes Destination: " + getCartesDestination() + "\nCartes Wagons: " + getCartesWagon() ;
    }
}

