
public class BasicStrategy {
    private static final boolean[][] hardTotals = new boolean[10][10];
    private static final boolean[][] softTotals = new boolean[7][10];

    public BasicStrategy(){
        initializeHard();
        initializeSoft();

        System.out.println("Hard totals");
        for (int row = 0; row < hardTotals.length; row++) {
            for (int col = 0; col < hardTotals[row].length; col++) {
                System.out.print(hardTotals[row][col] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
        System.out.println();
        System.out.println("soft totals");
        for (int row = 0; row < softTotals.length; row++) {
            for (int col = 0; col < softTotals[row].length; col++) {
                System.out.print(softTotals[row][col] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    private void initializeHard(){
        for(int p = 0; p < 10; p++){
            for(int d = 0; d < 10; d++){
                if(p < 4){
                    hardTotals[p][d] = true;
                }
                else if(p == 4){
                    if(d < 2 || d > 5){
                        hardTotals[p][d] = true;
                    }
                    else{
                        hardTotals[p][d] = false;
                    }
                }
                else if(p > 4 && p < 9){
                    if(d < 5){
                        hardTotals[p][d] = false;
                    }
                    else{
                        hardTotals[p][d] = true;
                    }
                }
                else if(p == 9){
                    hardTotals[p][d] = false;
                }
            }
        }
    }

    private void initializeSoft(){
        for(int p = 0; p < 7; p++){
            for(int d = 0; d < 10; d++){
                if(p < 5){
                    softTotals[p][d] = true;
                }
                else if(p == 5){
                    if(d < 7){
                        softTotals[p][d] = false;
                    }
                    else{
                        softTotals[p][d] = true;
                    }
                }
                else if(p == 6){
                    softTotals[p][d] = false;
                }
            }
        }
    }

    public boolean shouldHit(boolean soft, int pVal, int dVal){
        int playervalue;
        if(pVal < 9){
            playervalue = 0;
        }
        else{
            playervalue = pVal - 8;
        }
        if(soft){
            return shouldHitSoft(playervalue, dVal);
        }
        return hardTotals[playervalue][dVal];
    }

    public boolean shouldHitSoft(int playerValue, int dVal){
        return softTotals[playerValue][dVal];
    }

    public static void main(String args[]){
        BasicStrategy test = new BasicStrategy();
        
        System.out.println(test.shouldHit(false, 12, 4));
    }
}
