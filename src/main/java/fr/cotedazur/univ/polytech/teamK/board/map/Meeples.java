package fr.cotedazur.univ.polytech.teamK.board.map;

import java.util.Arrays;
import java.util.Objects;

public class Meeples {
    private static int[] total = {10,10,10,10,10,10}; //red, black, green, yellow, blue, white
    private int [] listOfOwnedMeeples; //red, black, green, yellow, blue, white
    private int number;

    //pour les joueurs
    public Meeples(){
        listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0};
        this.number = 0;
    }

    public int getNumber () {return number;}

    //pour les city
    public Meeples(int number)  {
        listOfOwnedMeeples = new int[]{0, 0, 0, 0, 0, 0};
        int index;
        this.number = number;
        for(int i = 0; i < number; i++){
            do {
                index = (int) (Math.random() * 6);
            } while (total[index] == 0);
            listOfOwnedMeeples[index]++;
            total[index]--;
        }
    }
    public void transferMeeples(Meeples cityMeeples) throws IllegalAccessException {
        if (cityMeeples.number == 0) {
            throw new IllegalAccessException();
        }
        int i;
        for (i = 0; cityMeeples.listOfOwnedMeeples[i] > 0; i++) ;
        cityMeeples.listOfOwnedMeeples[i]--;
        cityMeeples.number--;
        this.listOfOwnedMeeples[i]++;
        this.number++;

    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Meeples other = (Meeples) obj;
        return Arrays.equals(listOfOwnedMeeples, other.listOfOwnedMeeples);
    }

    @Override
    public int hashCode() {
        // Combine les champs 'number' et 'asign' pour calculer un hash unique
        return Objects.hash(number, Arrays.hashCode(listOfOwnedMeeples));
    }

    private boolean stillMeeples(){
        for(int i = 0; i < 6; i++){
            if(total[i] != 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Red=" + listOfOwnedMeeples[0] + ", Black=" + listOfOwnedMeeples[1] +
                ", Green=" + listOfOwnedMeeples[2] + ", Yellow=" + listOfOwnedMeeples[3] +
                ", Blue=" + listOfOwnedMeeples[4] + ", White=" + listOfOwnedMeeples[5];
    }
}
