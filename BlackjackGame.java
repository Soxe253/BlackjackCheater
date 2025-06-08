import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {

    Dealer dealer;
    Participant player1;
    BlackjackShoe shoe;
    static Scanner input = new Scanner(System.in);
    int decks;
    int player;
    /**
     * initializes player, dealer and shoe
     * @param decks the amount of decks in the shoe
     */
    public BlackjackGame(int decks, int player){
        dealer = new Dealer();
        this.player = player;
        if(player == 1){
            player1 = new BStratUser();
        }
        else{
            player1 = new HumanPlayer();
        }
        shoe = new BlackjackShoe(decks);
        this.decks = decks;
    }

    public void reshuffle(){
        shoe = new BlackjackShoe(this.decks);
    }
    /**
     * does the initial deal of two cards to each player and dealer
     */
    public void initialDeal(){
        if(shoe.getSize() < (Math.round((decks * 52) * 0.15))){
            System.out.println("reshuffling");
            reshuffle();
        }

        player1.hit(shoe.deal());
        dealer.hit(shoe.deal());
        player1.hit(shoe.deal());
        dealer.hit(shoe.deal());
    }

    public void resetHands(){
        player1.resetHand();
        dealer.resetHand();
    }

    /**
     * plays the players half of the game
     */
    public void playerGame(){
        boolean isPlayerDone = false;
        player1.getDealerCard(dealer.hand.get(1));
        while(!isPlayerDone){
            System.out.println("Dealer Hand: " + dealer.showFirstCardHidden() + " Value: " + dealer.revealedCardValue());
            System.out.println(player1.showHand() +  " Value: " + player1.getHandValue());

            isPlayerDone = !(player1.wantsToHit(input));

            if(!isPlayerDone){
                player1.hit(shoe.deal());
                System.out.println("HIT");
            }
            if(player1.getHandValue() > 21){
                System.out.println("BUST");
                return;
            }
        }
        System.out.println("STAND");
    }

    public void dealerPlayGame(){
        System.out.println("Dealers Hand " + dealer.showFirstCardHidden());
        if(dealer.getHandValue() == 21){
            System.out.println("Dealer has blackjack");
            System.out.println(dealer.showHand());
            return;
        }
        while(dealer.shouldHit()){
            dealer.hit(shoe.deal());
        }
    }

   /**
    * determines winner of game by comparing hand values
    * @return 0 if dealer win, 1 if player win and 2 if tie
    */
    public int determineWinner(){
        int dealerHandValue = dealer.getHandValue();
        System.out.println("Dealer's final hand: " + dealer.showHand() + " Value: " + dealerHandValue);

        int playerHandValue = player1.getHandValue();
        System.out.println(player1.showHand() + " Value: " + playerHandValue);

        if(playerHandValue > 21){
            System.out.println("Player Busts!");
            resetHands();
            return 0;
        } else if(dealerHandValue > 21 || playerHandValue > dealerHandValue){
            System.out.println("Player Wins!");
            resetHands();
            return 1;
        } else if(playerHandValue == dealerHandValue){
            System.out.println("Its a Tie!");
            resetHands();
            return 2;
        } else{
            System.out.println("Player loses to the dealer");
            resetHands();
            return 0;
        }

    }

    public ArrayList<Card> cardsFromHand(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int i = 0; i < dealer.hand.size(); i++){
            cards.add(dealer.hand.get(i));
        }
        for(int i = 0; i < player1.hand.size(); i++){
            cards.add(player1.hand.get(i));
        }
        System.out.println(cards.toString());
        return cards;
    }
    
    public void playGame(){


        initialDeal();

        playerGame();

        dealerPlayGame();

        //cardsFromHand();
        
        determineWinner();
    }


    public static void main (String[] args){
        BlackjackGame game = new BlackjackGame(1,1);
        boolean keepPlaying = true;
        while(keepPlaying){
            game.playGame();
            System.out.println("Play again? Y/N");
            String choice = input.nextLine();
            keepPlaying = choice.equalsIgnoreCase("y");
        }
        
    }
}
