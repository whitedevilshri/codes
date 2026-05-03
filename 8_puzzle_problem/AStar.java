import java.util.*;

/**
 * 8 Puzzle Problem - A* Search Algorithm
 * Uses heuristic: number of misplaced tiles (excluding blank)
 * Goal state: "123456780" (0 = blank tile)
 */
public class AStar {

    static final String GOAL = "123456780";

    /** Heuristic: count misplaced tiles (ignoring the blank '0') */
    static int heuristic(String state) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (state.charAt(i) != '0' && state.charAt(i) != GOAL.charAt(i))
                count++;
        }
        return count;
    }

    /** Generate all valid neighbor states by sliding the blank tile */
    static List<String> neighbors(String state) {
        List<String> result = new ArrayList<>();
        int idx = state.indexOf('0');
        int x = idx / 3, y = idx % 3;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k], ny = y + dy[k];
            if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                char[] tiles = state.toCharArray();
                // Swap blank with neighbor
                char tmp = tiles[x * 3 + y];
                tiles[x * 3 + y] = tiles[nx * 3 + ny];
                tiles[nx * 3 + ny] = tmp;
                result.add(new String(tiles));
            }
        }
        return result;
    }

    /** Node for priority queue: stores state, g-cost, f-cost */
    static class Node implements Comparable<Node> {
        String state;
        int g, f;

        Node(String state, int g, int f) {
            this.state = state;
            this.g = g;
            this.f = f;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f); // min-heap by f
        }
    }

    /** Run A* from the given start state */
    static void aStar(String start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        pq.add(new Node(start, 0, heuristic(start)));
        dist.put(start, 0);
        parent.put(start, "");

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.state.equals(GOAL)) {
                // Reconstruct and print path
                List<String> path = new ArrayList<>();
                String t = GOAL;
                while (!t.isEmpty()) {
                    path.add(t);
                    t = parent.get(t);
                }
                Collections.reverse(path);
                System.out.println("A* Solution Path:");
                for (String s : path) {
                    printBoard(s);
                }
                return;
            }

            for (String next : neighbors(cur.state)) {
                int newG = cur.g + 1;
                if (!dist.containsKey(next) || newG < dist.get(next)) {
                    dist.put(next, newG);
                    parent.put(next, cur.state);
                    pq.add(new Node(next, newG, newG + heuristic(next)));
                }
            }
        }
        System.out.println("No solution found.");
    }

    /** Print the 3x3 board from a state string */
    static void printBoard(String state) {
        for (int i = 0; i < 9; i++) {
            System.out.print(state.charAt(i) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String start = "123405678"; // Example: one move away from goal
        System.out.println("Start state:");
        printBoard(start);
        aStar(start);
    }
}
