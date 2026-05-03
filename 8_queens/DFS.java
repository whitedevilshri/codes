import java.util.*;

/**
 * 8 Queens Problem - Depth First Search (DFS)
 * Explores queen placements row by row using DFS.
 */
public class DFS {

    static final int N = 8;
    static int[] board = new int[N];

    /** Check if placing a queen at (row, col) is safe */
    static boolean safe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    /** DFS: try placing queens row by row */
    static boolean dfs(int row) {
        if (row == N) return true;

        for (int col = 0; col < N; col++) {
            if (safe(row, col)) {
                board[row] = col;
                if (dfs(row + 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Arrays.fill(board, -1);
        if (dfs(0)) {
            System.out.println("8 Queens DFS Solution:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print((board[i] == j ? "Q" : ".") + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
