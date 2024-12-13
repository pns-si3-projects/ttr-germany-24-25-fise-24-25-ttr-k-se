package fr.cotedazur.univ.polytech.teamK.board.map;

import java.util.Arrays;

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
        for (int i = 0; i < cityMeeples.listOfOwnedMeeples.length; i++) {
            if (cityMeeples.listOfOwnedMeeples[i] > 0) {
                cityMeeples.listOfOwnedMeeples[i]--;
                cityMeeples.number--;
                this.listOfOwnedMeeples[i]++;
                this.number++;
            }
        }
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
