import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {

    Dealer dealer;
    Participant player1;
    BlackjackShoe shoe;
    static Scanner input = new Scanner(System.in);
    int decks;
    //int player;
    /**
     * initializes player, dealer and shoe
     * @param decks the amount of decks in the shoe
     */
    public BlackjackGame(int decks, int player){
        dealer = new Dealer();
        //this.player = player;
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
     * checks if dealer has bj
     * @return true if dealer bj
     */
    public boolean dealerBj(){
        if(dealer.revealedCardValue() == 10 || dealer.revealedCardValue() == 11){
            System.out.println("Checking dealer Blackjack");
            if(dealer.getHandValue() == 21){
                System.out.println("Dealer has BlackJack!");
                return true;
            }
            System.out.println("Dealer does not have blackjack");
        }
        return false;
    }
    
    /**
     * Deals two cards to each player and dealer, reshuffles if shoe gets too low
     * @return true/false if it had to reshuffle
     */
    public boolean initialDeal(){
        boolean shuffled = false;
        if(shoe.getSize() < ((int) (Math.round((decks * 52) * 0.2)))){
            System.out.println("reshuffling");
            reshuffle();
            shuffled = true;
        }
        player1.hit(shoe.deal());
        dealer.hit(shoe.deal());
        player1.hit(shoe.deal());
        dealer.hit(shoe.deal());
        return shuffled;
    }

    public void resetHands(){
        player1.resetHand();
        dealer.resetHand();
    }

     /**
     * plays players half of the game
     * @return 0 for stand, 1 for bust, 2 for bj, 3 for dd, -1 for error
     */
    public int playerGame(){
        boolean isTurnOver = false;
        player1.getDealerCard(dealer.hand.get(1));
        if(player1.getHandValue() == 21){
            System.out.println("Blackjack!");
            return 2;
        }

        while(!isTurnOver){
            System.out.println("Dealer Hand: " + dealer.showFirstCardHidden() + " Value: " + dealer.revealedCardValue());
            System.out.println(player1.showHand() +  " Value: " + player1.getHandValue());
            
            String action = player1.getAction(input);

            if(action.equals("h")){
                player1.hit(shoe.deal());
                System.out.println("HIT");
            }
            if(action.equals("d") &&  player1.doubleDown()){
                player1.hit(shoe.deal());
                System.out.println("DOUBLE");
                return 3;
            }
            if(player1.getHandValue() > 21){
                System.out.println("BUST");
                return 1;
            }
            if(action.equals("s")){
                System.out.println("STAND");
                return 0;
            }
        }
        System.out.println("Broke out of turn loop somehow");
        return -1;
    }

    /**
     * plays dealers half of turn
     * @return true if bj
     */
    public boolean dealerPlayGame(int playerResult){
        if(playerResult == 1){
            return false;
        }
        if(playerResult == 2){
            if(dealer.getHandValue() == 21){
                return true;
            }
            return false;
        }
        System.out.println("Dealers Hand " + dealer.showFirstCardHidden());
        if(dealer.getHandValue() == 21){
            System.out.println("Dealer has blackjack");
            System.out.println(dealer.showHand());
            return true;
        }
        while(dealer.shouldHit()){
            dealer.hit(shoe.deal());
        }
        return false;
    }

   /**
    * determines who wins the game
    * @param blackjackOrTie 0 if no bj/tie, 1 if player bj and 2 if both have bj, 3 if dealer bj and player no -1 if error
    * @param betAmount the amount wagered
    * @return 0 if dealer win, 1 if player win and 2 if tie, -1 if error
    */
    public int determineWinner(int blackjackOrTie, int betAmount){
        if(blackjackOrTie == -1){
            System.out.println("error from input");
            return -1;
        }
        int winnings = 0;
        int dealerHandValue = dealer.getHandValue();
        System.out.println("Dealer's final hand: " + dealer.showHand() + " Value: " + dealerHandValue);
        int playerHandValue = player1.getHandValue();
        System.out.println(player1.showHand() + " Value: " + playerHandValue);
        if(blackjackOrTie == 3){
            System.out.println("dealer blackjack!");
            resetHands();
            return 0;
        }
        //bj tie
        if(blackjackOrTie == 2){
            winnings = betAmount;
            System.out.println("Both have blackjack! oof its a tie");
            player1.betWin(winnings);
            resetHands();
            return 2;
        }
        //player blackjack
        if(blackjackOrTie == 1){
            winnings = betAmount + ((int) Math.round(betAmount * (1.2)));
            System.out.println("Player has blackjack!");
            System.out.println("Won $"+winnings);
            player1.betWin(winnings);
            resetHands();
            return 1;
        }
        if(blackjackOrTie == 0){
            if(playerHandValue > 21){
                System.out.println("Player Busts!");
                resetHands();
                return 0;
            } else if(dealerHandValue > 21 || playerHandValue > dealerHandValue){
                winnings = betAmount * 2;
                System.out.println("Player Wins!");
                System.out.println("Player wins $"+winnings);
                player1.betWin(winnings);
                resetHands();
                return 1;
            } else if(playerHandValue == dealerHandValue){
                winnings = betAmount;
                System.out.println("Its a Tie!");
                player1.betWin(winnings);
                resetHands();
                return 2;
            } else{
                System.out.println("Player loses to the dealer");
                resetHands();
                return 0;
            }
        }
        System.out.println("bottom of method no return");
        return -1;
    }
    /**
     * This is for counting cards later, puts all played cards from round into an array
     * @return the array of played cards
     */
    public ArrayList<Card> cardsFromHand(){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i = 0; i < dealer.hand.size(); i++){
            cards.add(dealer.hand.get(i));
        }
        for(int i = 0; i < player1.hand.size(); i++){
            cards.add(player1.hand.get(i));
        }
        System.out.println(cards.toString());
        return cards;
    }
    
    public boolean playGame(){
        if(player1.getBank() < 1){
            System.out.println("Out of money");
            return false;
        }
        resetHands();
        int bjOrTie = 0;
        player1.bet(input);

        initialDeal();

        if(!dealerBj()){
            int playerBj = playerGame();

            boolean dealerBj = dealerPlayGame(playerBj);

            //cardsFromHand();

            //tie
            if(playerBj == 2 && dealerBj){
                bjOrTie = 2;
            }
            //player bj win
            if(playerBj == 2 && !dealerBj){
                bjOrTie = 1;
            }
            //dealer bj win
            if(playerBj != 2 && dealerBj){
                bjOrTie = 3;
            }
        }
        determineWinner(bjOrTie, player1.getBetAmount());

        System.out.println("Current bank amount is $"+player1.getBank());
        return true;
    }


    public static void main (String[] args){
        if (args.length < 1) {
            System.out.println("Usage: java BlackjackGame <playerType>" + "\n 0 = humand player and 1 = robot");
            return;
        }
        int playerType = Integer.parseInt(args[0]);

        BlackjackGame game = new BlackjackGame(1,playerType);
        boolean keepPlaying = true;
        while(keepPlaying){
            if(!game.playGame()){
                return;
            }
            System.out.println("Play again? Y/N");
            String choice = input.nextLine();
            keepPlaying = choice.equalsIgnoreCase("y");
        }
        
    }
}
