import java.util.*;

/**
 * 8 Queens Problem - Depth Limited Search (DLS)
 * Restricts queen placement to a given depth limit.
 */
public class DLS {

    static final int N = 8;
    static int[] board = new int[N];

    /** Safety check for queen placement */
    static boolean safe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    /**
     * Depth Limited Search for N-Queens
     * @param row current row being filled
     * @param depth remaining allowed depth
     */
    static boolean dls(int row, int depth) {
        if (depth < 0) return false;

        if (row == N) {
            System.out.println("Solution Found!");
            return true;
        }

        for (int col = 0; col < N; col++) {
            if (safe(row, col)) {
                board[row] = col;
                if (dls(row + 1, depth - 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Arrays.fill(board, -1);
        int depthLimit = 8; // Need full depth to place all 8 queens

        System.out.println("8 Queens - Depth Limited Search (limit=" + depthLimit + ")");
        boolean found = dls(0, depthLimit);

        if (found) {
            System.out.println("Board:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print((board[i] == j ? "Q" : ".") + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution within depth limit.");
        }
    }
}
