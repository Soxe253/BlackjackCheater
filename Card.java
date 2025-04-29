public class Card {
    public enum Rank{
        ACE(11),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private final int value;

        private Rank(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public enum Suit{
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
        
    }
    
    public Suit suit;
    public Rank rank;

    public Card(){
        this.rank = Rank.ACE;
        this.suit = Suit.SPADES;
    }

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue(){
        return rank.getValue();
    }

    public String toString(){
        String tmp = "[" + this.rank + ", " + this.suit + "]";
        return tmp;
    }

    public static void main(String[] args) {
        Card card1 = new Card();
        Card card2 = new Card(Rank.TEN, Suit.HEARTS);
        System.out.println(card1.toString());
        System.out.println(card2.toString());

    }
}
