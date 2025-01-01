package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Meeple {
    private static int[] total = {10,10,10,10,10,10}; //black, blue, red,white,yellow, green

    private int [] listOfOwnedMeeples; //black, blue, red,white,yellow, green
    private int number;

    public Meeple(int number, Random rand) {
        listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0};
        int index;
        this.number = number;
        for(int i = 0; i < number; i++){
            do {
                index = rand.nextInt(6);
            } while (total[index] == 0);
            listOfOwnedMeeples[index]++;
            total[index]--;
        }
    }

    //pour les joueurs
    public Meeple(){
        listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0};
        this.number = 0;
    }

    //pour les city
    public Meeple(int number) {
        this(number, new Random());
    }

    public int getNumber () {return number;}
    public int[] getListOfOwnedMeeples() {return listOfOwnedMeeples;}

    /**
     * Transfer one meeple of a choosen color from a Meeple class to this one
     * @param cityMeeples the Meeple class where we pick one meeples
     * @param color the color choice
     */
    public boolean transferMeeples(Meeple cityMeeples, Colors color) {
        int index = color.ordinal();
        if (index > 5) {
            throw new IllegalArgumentException("Couleur de meeples inconnues");
        }
        if (cityMeeples.listOfOwnedMeeples[index] == 0) {
            return false;
        }
        cityMeeples.listOfOwnedMeeples[index]--;
        cityMeeples.number--;
        this.listOfOwnedMeeples[index]++;
        this.number++;
        return true;
        }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Meeple other = (Meeple) obj;
        return Arrays.equals(listOfOwnedMeeples, other.listOfOwnedMeeples);
    }

    @Override
    public int hashCode() {
        // Combine les champs 'number' et 'asign' pour calculer un hash unique
        return Objects.hash(number, Arrays.hashCode(listOfOwnedMeeples));
    }

    @Override
    public String toString() {
        return "Red=" + listOfOwnedMeeples[0] + ", Black=" + listOfOwnedMeeples[1] +
                ", Green=" + listOfOwnedMeeples[2] + ", Yellow=" + listOfOwnedMeeples[3] +
                ", Blue=" + listOfOwnedMeeples[4] + ", White=" + listOfOwnedMeeples[5];
    }
}
