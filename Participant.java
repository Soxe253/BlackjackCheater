import java.util.ArrayList;
import java.util.Scanner;

public abstract class Participant {
    public ArrayList<Card> hand;
    public int handValue;

    public Participant() {
        hand = new ArrayList<Card>();
        handValue = 0;
    }

    public void hit(Card card) {
        hand.add(card);
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

    public int calculateHandValue() {
        int totalValue = 0;
        int totalAces = 0;

        for(Card card : hand){
            int cardValue = card.getValue();
            totalValue += cardValue;

            if(card.rank == Card.Rank.ACE){
                totalAces++;
            }
        }

        while(totalValue > 21 && totalAces > 0){
            totalValue -= 10;
            totalAces--;
        }
        handValue = totalValue;
        return totalValue;
    }

    public int getHandValue(){
        return calculateHandValue();
    }

    /**
     * resets the players hand
     */
    public void resetHand(){
        hand.clear();
    }

    public abstract boolean wantsToHit(Scanner scanner);
}
