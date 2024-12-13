package fr.cotedazur.univ.polytech.teamK.board.map;

public class Meeples {
    private static int[] total = {10,10,10,10,10,10}; //red, black, green, yellow, blue, white
    private int [] asign ; //red, black, green, yellow, blue, white
    private int number;

    public Meeples(){
        asign = new int[]{0, 0, 0, 0, 0, 0};
        this.number = 0;
    }

    public int getNumber () {return number;}

    public Meeples(int number)  {
        asign = new int[]{0, 0, 0, 0, 0, 0};
        int index;
        this.number = number;
        for(int i = 0; i < number; i++){
            do {
                index = (int) (Math.random() * 6);
            } while (total[index] == 0);
            asign[index]++;
            total[index]--;
        }
    }

    public void transferMeeples(Meeples meeples) throws IllegalAccessException {
        int index;
        if (meeples.number == 0) {
            throw new IllegalAccessException();
        }
        do {
            index = (int) (Math.random() * 6);
        } while (asign[index] == 0);
        meeples.asign[index] --;
        asign[index] ++;
    }

    private boolean stillMeeples(){
        for(int i = 0; i < 6; i++){
            if(total[i] != 0){
                return true;
            }
        }
        return false;
    }
}
