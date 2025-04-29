import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards;
    /**
     * Creates a full deck of 52 cards shuffled
     */
    public Deck(){
        cards = new ArrayList<Card>();
        Card card;
        for(Card.Suit suit : Card.Suit.values()){
            for(Card.Rank rank : Card.Rank.values()){
                card = new Card(rank, suit);
                cards.add(card);
            }
        }
        Collections.shuffle(cards);
    }
    /**
     * removes and returns the top card from the deck
     * @return the top card from the deck
     */
    public Card Draw(){
        return cards.remove(cards.size() - 1);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public static void main(String[] args) {
       Deck deck = new Deck();
       System.out.println(deck.getCards());
        
    }
}
