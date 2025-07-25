import java.util.ArrayList;
import java.util.Scanner;

public class BStratUser extends Participant{
    public BasicStrategy strat;

    public BStratUser(){
        strat = new BasicStrategy();
        hand = new ArrayList<Card>();
        handValue = 0;
        betAmount = 0;
        bank = 100;

    }

    @Override
    public boolean wantsToHit(Scanner s){
        int dealerValue = dealersCard.getValue();
        
        return strat.shouldHit(this.soft, handValue, dealerValue);
    }

    /**
     * uses old logic from wantsToHit to decide to hit or stand
     */
    @Override
    public String getAction(Scanner input) {
        boolean hit = wantsToHit(input);
        if(hit){
            return "h";
        }
        else{
            return "s";
        }
    }


    public static void main(String[] args) {
        BStratUser robot = new BStratUser();
        Dealer dealer = new Dealer();
        BlackjackShoe shoe = new BlackjackShoe(6);

        robot.hit(shoe.deal());
        dealer.hit(shoe.deal());
        robot.hit(shoe.deal());
        dealer.hit(shoe.deal());
        robot.getDealerCard(dealer.hand.get(1));
        System.out.println(dealer.showFirstCardHidden());
        System.out.println(robot.showHand());
        while(robot.getHandValue() < 21 && robot.wantsToHit(null)){
            System.out.println("HIT");
            robot.hit(shoe.deal());
            System.out.println(robot.showHand());
        }
        System.out.println("STAND");

    }

    /**
     * Currently just going to bet 10 every time
     */
    @Override
    public int bet(Scanner input) {
        bank = bank - 10;
        betAmount = 10;
        return betAmount;
    }

    @Override
    public void doubleDown() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
