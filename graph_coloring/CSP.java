import java.util.*;

/**
 * Graph Coloring - CSP (Constraint Satisfaction Problem)
 * Assigns colors to graph vertices such that no two adjacent vertices share a color.
 * Uses backtracking search.
 */
public class CSP {

    static final int N = 4; // Number of vertices
    static final int M = 3; // Number of colors

    // Adjacency matrix of the graph
    static int[][] graph = {
        {0, 1, 1, 1},
        {1, 0, 1, 0},
        {1, 1, 0, 1},
        {1, 0, 1, 0}
    };

    static int[] color = new int[N]; // color[i] = color assigned to vertex i

    /**
     * Check if assigning color c to 'node' is safe
     * (no adjacent vertex has the same color)
     */
    static boolean isSafe(int node, int c) {
        for (int i = 0; i < N; i++) {
            if (graph[node][i] == 1 && color[i] == c) return false;
        }
        return true;
    }

    /** Backtracking solver: assign colors to vertices one by one */
    static boolean solve(int node) {
        if (node == N) return true; // All vertices colored

        for (int c = 1; c <= M; c++) {
            if (isSafe(node, c)) {
                color[node] = c;
                if (solve(node + 1)) return true;
                color[node] = 0; // Backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Graph Coloring (CSP) ===");
        System.out.println("Graph has " + N + " vertices and " + M + " colors available.\n");

        if (solve(0)) {
            System.out.println("Color Assignment:");
            String[] colorNames = {"", "Red", "Green", "Blue"};
            for (int i = 0; i < N; i++) {
                System.out.println("  Vertex " + i + " -> Color " + color[i]
                    + " (" + (color[i] <= colorNames.length - 1 ? colorNames[color[i]] : color[i]) + ")");
            }
        } else {
            System.out.println("No valid coloring found with " + M + " colors.");
        }
    }
}
