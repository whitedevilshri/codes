import java.util.*;

/**
 * 8 Puzzle Problem - BFS (Breadth First Search)
 * Finds the shortest sequence of moves from start to goal state.
 * Goal state: "123456780" (0 = blank tile)
 */
public class Main {

    static final String GOAL = "123456780";

    /** Generate valid neighbor states by sliding the blank tile */
    static List<String> getNext(String state) {
        List<String> result = new ArrayList<>();
        int idx = state.indexOf('0');
        int x = idx / 3, y = idx % 3;

        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] move : moves) {
            int nx = x + move[0], ny = y + move[1];
            if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                char[] tiles = state.toCharArray();
                char tmp = tiles[x * 3 + y];
                tiles[x * 3 + y] = tiles[nx * 3 + ny];
                tiles[nx * 3 + ny] = tmp;
                result.add(new String(tiles));
            }
        }
        return result;
    }

    /** Reconstruct and print the solution path */
    static void printPath(Map<String, String> parent, String end) {
        List<String> path = new ArrayList<>();
        String cur = end;
        while (cur != null) {
            path.add(cur);
            cur = parent.get(cur);
        }
        Collections.reverse(path);

        System.out.println("Shortest Path (BFS):");
        for (String state : path) {
            for (int i = 0; i < 9; i++) {
                System.out.print(state.charAt(i) + " ");
                if (i % 3 == 2) System.out.println();
            }
            System.out.println();
        }
    }

    /** Breadth First Search from start to goal */
    static void bfs(String start) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            if (cur.equals(GOAL)) {
                printPath(parent, cur);
                return;
            }

            for (String next : getNext(cur)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, cur);
                    queue.add(next);
                }
            }
        }
        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        String start = "123405678"; // one blank move from goal
        System.out.println("8 Puzzle BFS. Start state:");
        for (int i = 0; i < 9; i++) {
            System.out.print(start.charAt(i) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();
        bfs(start);
    }
}
