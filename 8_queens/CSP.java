import java.util.*;

/**
 * 8 Queens Problem - CSP (Constraint Satisfaction Problem) with Backtracking
 * Places 8 queens on an 8x8 chessboard such that no two queens attack each other.
 */
public class CSP {

    static final int N = 8;
    static int[] board = new int[N]; // board[row] = column of queen in that row

    /** Check if placing a queen at (row, col) is safe */
    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            // Same column or diagonal attack
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    /** Recursive backtracking solver */
    static boolean solve(int row) {
        if (row == N) return true; // All queens placed

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                if (solve(row + 1)) return true;
                board[row] = -1; // Backtrack
            }
        }
        return false;
    }

    /** Print the chessboard with queen positions */
    static void printBoard() {
        System.out.println("Solution (Q = Queen, . = Empty):");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i] == j ? "Q" : ".") + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Arrays.fill(board, -1);
        if (solve(0)) {
            printBoard();
        } else {
            System.out.println("No solution found.");
        }
    }
}
