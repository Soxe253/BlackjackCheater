import java.util.ArrayList;

public class Dealer {
    public ArrayList<Card> hand;
    public int handValue;
    
    /**
     * Initiializes the dealers hand
     */
    public Dealer(){
        hand = new ArrayList<Card>();
        handValue = 0;
    }

    /**
     * adds a card to the dealers hand
     * @param card the card adding to the hand
     */
    public void hit(Card card){
        hand.add(card);
        handValue = calculateHandValue();
    }

    /**
     * Calculates hand value of cards
     * @return value of cards
     */
    public int calculateHandValue(){
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

        return totalValue;
    }

    /**
     * returns value of hand
     * @return value of hand
     */
    public int getHandValue(){
        return handValue;
    }

    /**
     * returns a boolean if the dealer should hit
     * @return true if yes, false if not
     */
    public boolean shouldHit(){
        return getHandValue() < 17;
    }


    /**
     * resets the dealers hand
     */
    public void resetHand(){
        hand.clear();
    }

    public int revealedCardValue(){
        return hand.get(1).getValue();
    }

    /**
     * prints the dealers hand
     * @return
     */
    public String showHand(){
        StringBuilder tmp = new StringBuilder();
        for(Card card : hand){
            tmp.append(card.toString()).append(" ");
        }
        return "(" + tmp.toString().trim() + ")";
    }

    /**
     * shows the dealers first card as hidden
     * @return a string representation of the dealers cards with one hidden
     */
    public String showFirstCardHidden() {
        if (hand.size() < 2) {
            return "Dealer's hand is incomplete.";
        }
        return "([Hidden] " + hand.get(1).toString() + ")";
    }


    public static void main(String[] args) {
        //BlackjackShoe shoe = new BlackjackShoe(6);
       // Dealer dealer = new Dealer();
        //dealer.playTurn(shoe);
    }
}
