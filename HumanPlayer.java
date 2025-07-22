import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Participant{
    
    /**
     * Initializes the player
     */
    public HumanPlayer(){
        hand = new ArrayList<Card>();
        handValue = 0;
        bank = 100;
        soft = false;
    }

    // /**
    //  * prints the players hand
    //  * @return the players hand
    //  */
    // public String showHand(){
    //     StringBuilder tmp = new StringBuilder();
    //     for(Card card : hand){
    //         tmp.append(card.toString()).append(" ");
    //     }
    //     return "(" + tmp.toString().trim() + ")";
    // }

    public boolean wantsToHit(Scanner input){
        System.out.println("Hit or Stand?");
        String choice = input.nextLine();
        return choice.equalsIgnoreCase("hit");
    }

    /**
     * Asks user to input bet amount
     * @param input scanner
     * @return bet amount
     */
    public int bet(Scanner input){
        System.out.println("Your bank is $"+bank +"\n Please type your bet");
        int betAmount = input.nextInt();
        input.nextLine();
        while(betAmount <= 0 || betAmount > bank){
            System.out.println("Bet must be less than or equal to bank and positive");
            betAmount = input.nextInt();
            input.nextLine();
        }
        bank = bank - betAmount;
        return betAmount;
    }

    

    

    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        // BlackjackShoe shoe = new BlackjackShoe(6);
        HumanPlayer player = new HumanPlayer();
        // player.hit(shoe.deal());
        // player.hit(shoe.deal());
        // System.out.println(player.showHand());
        // player.bet(input);
        // while(player.wantsToHit(input)){
        //     player.hit(shoe.deal());
        //     System.out.println(player.showHand());
        // }
        Card card1 = new Card();
        Card card2 = new Card(Card.Rank.TEN, Card.Suit.HEARTS);
        player.hit(card1);
        player.hit(card2);
        System.out.println(player.showHand());
       

    }
}
