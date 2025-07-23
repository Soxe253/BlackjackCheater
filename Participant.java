import java.util.ArrayList;
import java.util.Scanner;
/**
 * A base abstract class for a participant in blackjack
 */
public abstract class Participant {
    public ArrayList<Card> hand;
    public int handValue;
    public Card dealersCard;
    public boolean soft;
    public int bank;
    public int betAmount;

    public Participant() {
        hand = new ArrayList<Card>();
        handValue = 0;
        soft = false;
        bank = 100;
        betAmount = 0;
    }

    public void hit(Card card) {
        hand.add(card);
        calculateHandValue();
    }

    public void getDealerCard(Card card){
        dealersCard = card;
    }

     /**
     * prints the players hand
     * @return the players hand
     */
    public String showHand(){
        StringBuilder tmp = new StringBuilder();
        String bSoft = "";
        if(soft){
            bSoft = " {soft}";
        }
        for(Card card : hand){
            tmp.append(card.toString()).append(" ");
        }
        return "(" + tmp.toString().trim() + bSoft + ")";
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
        if(totalAces > 0 && totalValue < 21){
            soft = true;
        }
        else{
            soft = false;
        }
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
        dealersCard = null;
        soft = false;
        betAmount = 0;
    }

     /**
     * Adds new winnings to bank total
     * @param winnings amount won
     */
    public void betWin(int winnings){
        bank = bank + winnings;
        System.out.println("New bank total is: " + bank);
    }

    /**
     * Get the bank total of player
     * @return bank
     */
    public int getBank(){
        return bank;
    }

    /**
     * Reurns current bet amount
     * @return betAmount
     */
    public int getBetAmount(){
        return betAmount;
    }

    /**
     * checks if hand is soft
     * @return true if soft
     */
    public boolean isSoft(){
        return soft;
    }

    public abstract boolean wantsToHit(Scanner scanner);

    public abstract String getAction(Scanner input);

    public abstract int bet(Scanner input);

    public abstract boolean doubleDown();

    
    
}