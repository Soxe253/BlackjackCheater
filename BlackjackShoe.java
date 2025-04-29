import java.util.ArrayList;
import java.util.Collections;

public class BlackjackShoe{
    public int decks;
    ArrayList<Card> shoe;

    /**
     * Creates a blackjack shoe of the amount of given decks
     * @param decks the amount of decks in the shoe
     */
    public BlackjackShoe(int decks){
        this.decks = decks;
        shoe = new ArrayList<Card>();
        for(int i = 1; i < this.decks; i++){
            Deck deck = new Deck();
            shoe.addAll(deck.getCards());
        }
        Collections.shuffle(shoe);
    }

    /**
     * deals a single card from the shoe
     * @return the card to deal
     */
    public Card deal(){
        return shoe.remove(shoe.size() - 1);
    }
    /**
     * gets amount of cards in shoe
     * @return amount of cards in shoe
     */
    public int getSize(){
        return shoe.size();
    }

    /**
     * shuffles the shoe
     */
    public void shuffle(){
        Collections.shuffle(shoe);
    }
}