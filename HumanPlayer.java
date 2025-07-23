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
        betAmount = 0;
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
    @Override
    public boolean wantsToHit(Scanner input){
        System.out.println("Hit or Stand?");
        String choice = input.nextLine();
        return choice.equalsIgnoreCase("hit");
    }

    /**
     * Returns the users decision to take
     * @param input scanner 
     * @return h s or d for hit stand double
     */
    @Override
    public String getAction(Scanner input){
        System.out.println("Hit or Stand or Double?");
        boolean decision = false;
        while(!decision){
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("hit") || choice.equalsIgnoreCase("h")){
                return "h";
            }
            else if(choice.equalsIgnoreCase("stand") || choice.equalsIgnoreCase("s")){
                return "s";
            }
            else if(choice.equalsIgnoreCase("double") || choice.equalsIgnoreCase("d")){
                return "d";
            }
            else{
                System.out.println("Please type hit, stand, double or h, s, d");
            }
        }
        return "";
    }

    /**
     * Asks user to input bet amount
     * @param input scanner
     * @return bet amount
     */
    @Override
    public int bet(Scanner input){
        System.out.println("Your bank is $"+bank +"\n Please type your bet");
        int betA = input.nextInt();
        input.nextLine();
        while(betA <= 0 || betA > bank){
            System.out.println("Bet must be less than or equal to bank and positive");
            betA = input.nextInt();
            input.nextLine();
        }
        bank = bank - betA;
        betAmount = betA;
        return betAmount;
    }

    @Override
    public boolean doubleDown(){
        if(betAmount > bank){
            System.out.println("Not enough to double");
            return false;
        }
        else{
            bank = bank - betAmount;
            betAmount = betAmount + betAmount;
            return true;
        }
    }

    

    

    public static void main(String[] args) {
        HumanPlayer player = new HumanPlayer();
        Scanner input = new Scanner(System.in);

        String temp = player.getAction(input);

        System.out.println(temp);
        
       

    }
}
