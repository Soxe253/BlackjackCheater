import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Participant{
    
    /**
     * Initializes the player
     */
    public HumanPlayer(){
        hand = new ArrayList<Card>();
        handValue = 0;
    }

    /**
     * prints the players hand
     * @return the players hand
     */
    public String showHand(){
        StringBuilder tmp = new StringBuilder();
        for(Card card : hand){
            tmp.append(card.toString()).append(" ");
        }
        return "(" + tmp.toString().trim() + ")";
    }

    public boolean wantsToHit(Scanner input){
        System.out.println("Hit or Stand?");
        String choice = input.nextLine();
        return choice.equalsIgnoreCase("hit");
    }

    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BlackjackShoe shoe = new BlackjackShoe(6);
        HumanPlayer player = new HumanPlayer();
        player.hit(shoe.deal());
        player.hit(shoe.deal());
        System.out.println(player.showHand());
        while(player.wantsToHit(input)){
            player.hit(shoe.deal());
            System.out.println(player.showHand());
        }
    }
}
