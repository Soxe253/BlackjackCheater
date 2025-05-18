import java.util.Scanner;

public class CardCounter extends Participant {
    int count;

    public CardCounter(){
        count = 0;

    }

    public boolean wantsToHit(Scanner input){
        return true;
    }
}
