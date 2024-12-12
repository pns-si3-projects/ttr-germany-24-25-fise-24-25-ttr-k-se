package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.Colors;

import java.util.ArrayList;

public class Neeples {
    static int[] total = {10,10,10,10,10,10}; //red, black, green, yellow, blue, white
    int [] asign ; //red, black, green, yellow, blue, white

    public Neeples(){
        asign = new int[]{0, 0, 0, 0, 0, 0};
    }

    public Neeples (int number)  {
        asign = new int[]{0, 0, 0, 0, 0, 0};
        int index;
        for(int i = 0; i < number; i++){
            do {
                index = (int) (Math.random() * 6);
            } while (total[index] == 0);
            asign[index]++;
            total[index]--;
        }
    }

    public void transferNeeples(Neeples neeples){
        for(int i = 0; i < 6; i++){
            this.asign[i] += neeples.asign[i];
            neeples.asign[i] = 0;
        }
    }

    private boolean stillNeeples(){
        for(int i = 0; i < 6; i++){
            if(total[i] != 0){
                return true;
            }
        }
        return false;
    }
}
