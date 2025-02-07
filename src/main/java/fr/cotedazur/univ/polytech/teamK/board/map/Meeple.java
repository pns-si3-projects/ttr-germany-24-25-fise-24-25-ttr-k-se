package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

public class Meeple {
    private static int[] total = {10,10,10,10,10,10}; //black, blue, red, white,yellow, green
    private static int totalMeeples = 60;

    private int [] listOfOwnedMeeples; //black, blue, red,white,yellow, green
    private int number;

    public Meeple(int number, SecureRandom rand) {
        listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0};
        int index;
        this.number = number;
        if(totalMeeples > 0) {
            for (int i = 0; i < number; i++) {
                do {
                    index = rand.nextInt(6);
                } while (total[index] == 0);
                listOfOwnedMeeples[index]++;
                total[index]--;
                totalMeeples--;
            }
        }
    }

    //pour les joueurs
    public Meeple(){
        listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0};
        this.number = 0;
    }

    //pour les city
    public Meeple(int number) {
        this(number, new SecureRandom());
    }

    public int getNumber () {return number;}
    public int[] getListOfOwnedMeeples() {return listOfOwnedMeeples;}
    public int getNumberOfAColor(Colors colors)
    {
        int pos = colors.ordinal();
        if (listOfOwnedMeeples.length > pos)
        {
            return listOfOwnedMeeples[pos];
        }
        else
        {
            return -1;
        }
    }

    public static void resetMeeples () {
        total = new int[]{10, 10, 10, 10, 10, 10};
        totalMeeples = 60;
    }

    public boolean isEmpty() {
        for (int i = 0; i < number; i++) {
            if (listOfOwnedMeeples[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Transfer one meeple of a choosen color from a Meeple class to this one
     * @param cityMeeples the Meeple class where we pick one meeples
     * @param color the color choice, should be a color of a meeples
     */
    public boolean transferMeeples(Meeple cityMeeples, Colors color)  {
        int index = color.ordinal();
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
        return "Black=" + listOfOwnedMeeples[0] + ", Blue=" + listOfOwnedMeeples[1] +
                ", Red=" + listOfOwnedMeeples[2] + ", White=" + listOfOwnedMeeples[3] +
                ", Yellow=" + listOfOwnedMeeples[4] + ", Green=" + listOfOwnedMeeples[5];
    }
}
