import java.util.Scanner;
public class ConnectFour {
    //assigns a size to the game board
    public static void printBoard(char[][]array){
        for (int i = array.length-1; i >= 0; i--) {
            for(int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }
    //assigns dashes to each element of the board to initialize game
    public static void initializeBoard(char[][]array){
        for (int i = array.length-1; i >= 0; i--) {
            for(int j = 0; j < array[i].length; j++) {
                array[i][j] = '-';
            }
        }
        printBoard(array);
    }
    //inserts chip when Player 1 and Player 2 choose a column to make a move in
    public static int insertChip(char[][]array, int col, char chipType){
        int noChip = 0;
        while (noChip >= 0) {
            if ('x' == array[noChip][col] || 'o' == array[noChip][col]) {
                noChip++;
            } else
                break;
        }
        array[noChip][col] = chipType;
        printBoard(array);
        System.out.println("");
        return noChip;
    }
    //checks to see if either Player 1 of Player 2 won the game
    //checkWinner = true if either win, false if either don't
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        boolean checkWinner = false;
        int counter = 1;
        int counterRow = 1;
        for(int i = 1; i < array.length; i++){
            if(array[i-1][col] == chipType && array[i][col] == chipType)
                counterRow++;
            if (counterRow == 4)
                checkWinner = true;
        }
        for(int j = 1; j < array[row].length; j++){
            if(array[row][j-1] == chipType && array[row][j] == chipType)
                counter++;
            if (counter == 4)
                checkWinner = true;
        }
        return checkWinner;
    }
    //main method responsible for user input and printing winner or tie, it also calls methods
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        int height = scan.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int len = scan.nextInt();
        char[][] array = new char [height][len];
        initializeBoard(array);
        System.out.println("\nPlayer 1: x\nPlayer 2: o\n");
        boolean checkWinner1 = false;
        boolean checkWinner2 = false;
        boolean checkWinner3 = false;
        while(checkWinner1 == false && checkWinner2 == false && checkWinner3 == false) {
                    //must call methods separately for each player since they have different chip types
                    //must call methods separately for each player since want to differentiate winner
                    System.out.print("Player 1: Which column would you like to choose? ");
                    int col1 = scan.nextInt();
                    char chipType1 = 'x';
                    int noChip = insertChip(array, col1, chipType1);
                    checkWinner1 = checkIfWinner(array, col1, noChip, chipType1);
                    if (checkWinner1 == true){
                        break;
                    }
                    else {
                        System.out.print("Player 2: Which column would you like to choose? ");
                        int col2 = scan.nextInt();
                        char chipType2 = 'o';
                        int noChip2 = insertChip(array, col2, chipType2);
                        checkWinner2 = checkIfWinner(array, col2, noChip2, chipType2);
                    }
                    if (checkWinner2 == true){
                        break;
                    }
                    //check for tie -- counter adds each time an 'x' or 'o' is found element by element.
                    //if counter equals the board size, no more moves can be made and a tie is declared.
                    int counter = 1;
                    for (int i = 0; i < array.length; i++) {
                        for(int j = 0; j < array[i].length; j++) {
                            if ((array[i][j] == 'x' || array[i][j] == 'o')){
                                counter++;
                            }
                            if(counter == len*height){
                                checkWinner3 = true;
                                System.out.println("Draw. Nobody wins.");
                            }
                        }
                    }
        }
        //print who won the game
        if(checkWinner1 == true)
            System.out.println("Player 1 won the game!");
        else if(checkWinner2 == true)
            System.out.println("Player 2 won the game!");
    }
}