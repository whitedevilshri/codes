import java.util.*;

/**
 * 8 Queens Problem - BFS (Breadth First Search)
 * Builds queen placements level by level using BFS.
 * Each state is a list of column positions for queens placed so far.
 */
public class Main {

    /** Safety check: is (row, col) safe given previous queens in state? */
    static boolean isSafe(List<Integer> state, int row, int col) {
        for (int i = 0; i < row; i++) {
            int c = state.get(i);
            if (c == col) return false;                        // Same column
            if (Math.abs(c - col) == Math.abs(i - row)) return false; // Diagonal
        }
        return true;
    }

    /** Print board from a state list */
    static void printBoard(List<Integer> state) {
        System.out.println("Solution:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print((state.get(i) == j ? "Q" : ".") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /** BFS over partial placements */
    static void bfs() {
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>()); // Start with empty placement

        while (!queue.isEmpty()) {
            List<Integer> state = queue.poll();
            int row = state.size();

            if (row == 8) {
                printBoard(state);
                return; // Print first solution and stop
            }

            for (int col = 0; col < 8; col++) {
                if (isSafe(state, row, col)) {
                    List<Integer> next = new ArrayList<>(state);
                    next.add(col);
                    queue.add(next);
                }
            }
        }
        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        System.out.println("8 Queens - BFS");
        bfs();
    }
}
