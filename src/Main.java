import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Player 1, what's your name? ");
        String playerOneName = in.nextLine();
        System.out.print("Player 2, what's your name? ");
        String playerTwoName = in.nextLine();

        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        boolean isPlayer1 = true;

        boolean gameEnded = false;


        while (!gameEnded) {
            drawBoard(board);

            //track of what symbol we are using to play
            char symbol = ' ';
            if (isPlayer1) {
                symbol = 'x';
            } else {
                symbol = 'o';
            }

            //Print out the player's turn
            if (isPlayer1) {
                System.out.println(playerOneName + "'s turn (x)");
            } else {
                System.out.println(playerTwoName + "'s turn (o)");
            }

            int row = 0;
            int col = 0;

            while (true) {
                //get row and col from user
                System.out.print("Enter a row (0, 1, or 2): ");
                row = in.nextInt();

                System.out.print("Enter a column (0, 1, or 2): ");
                col = in.nextInt();

                //check if row and col are valid
                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    //row and col are out of bounds
                    System.out.println("Your row and column are out of bounds!");
                } else if (board[row][col] != '-') {
                    System.out.println("Someone has already made a move there!");
                } else {
                    break;
                }
            }

            //Setting the position on the board
            board[row][col] = symbol;

            //Check if a player has won
            if (hasWon(board) == 'x') {
                System.out.println(playerOneName + " has won!");
                gameEnded = true;
            } else if (hasWon(board) == 'o') {
                System.out.println(playerTwoName + " has won!");
                gameEnded = true;
            } else {
                if (hasTied(board)) {
                    System.out.println("It's a tie!");
                } else {
                    isPlayer1 = !isPlayer1;
                }
            }
        }
        drawBoard(board);

    }

    //printing the board
    public static void drawBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static char hasWon(char[][] board) {
        //Row
        for (int i = 0; i<3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
        }
        //Columns
        for (int j = 0; j<3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
                return board[0][j];
            }
        }
        //Diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0]!='-') {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0]!='-') {
            return board[2][0];
        }
        //Nobody won
        return '-';
    }

    //Check if the board is full
    public static boolean hasTied(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}