import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter size of board: ");
        int size = input.nextInt();
        char[][] array = new char[size][size];
        boolean plusOne = false;
        boolean plusDown = false;

        // Initializing the board with the stars
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = '*';
            }
        }

        // Set the starting and ending points
        array[3][0] = 'X';
        array[0][2] = 'X';
        array[1][2] = 'X';
        array[2][2] = 'X';
        array[2][3] = 'X';
        array[0][0] = 'A';
        // If a solution has been found print it out, else print out Invalid.
        if (solve(size, array, 0, 0, plusOne, plusDown)) {
            System.out.println("Here is the solution: ");
            printBoard(array, size);
        } else {
            System.out.print("Invalid");
        }
    }

    public static boolean solve(int size, char board[][], int row, int col, boolean plusOne, boolean plusDown) {
        // Base case: reached the end of the board
        if (row == size - 1 && col == size - 1) {
            board[row][col] = 'A';
            return true;
        }

        // Check if the current cell is valid
        if (isValid(row, col, board, size, plusOne, plusDown)) {
            board[row][col] = 'A';

            // Try moving down
            if (solve(size, board, row + 1, col, true, false)) {
                return true;
            }

            // Try moving straight
            if (solve(size, board, row, col + 1, false, true)) {
                return true;
            }

            // If neither of the above options work, backtrack
            board[row][col] = '*';
        }
        return false;
    }

    public static boolean isValid(int row, int col, char board[][], int size, boolean plusOne, boolean plusDown) {
        // Check if the cell is within the board
        if (row >= size || col >= size) {
            return false;
        }

        // Check if the cell is a boundary
        if (board[row][col] == 'X') {
            return false;
        }

        // Check if the mouse is moving straight when it should move down
        if (plusOne && board[row - 1][col] == '*') {
            return false;
        }

        // Check if the mouse is moving down when it should move straight
        if (plusDown && board[row][col - 1] == '*') {
            return false;
        }

        // The cell is valid
        return true;
    }

    public static void printBoard (char board[][], int size)
    {
        for (int i = 0; i < size; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
