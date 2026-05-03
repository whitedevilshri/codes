import java.util.*;

/**
 * 8 Puzzle Problem - Best First Search
 * Uses only heuristic h(n) = misplaced tiles to guide search (greedy)
 * Goal state: "123456780"
 */
public class BestFirst {

    static final String GOAL = "123456780";

    /** Heuristic: misplaced tiles count */
    static int heuristic(String state) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (state.charAt(i) != '0' && state.charAt(i) != GOAL.charAt(i))
                count++;
        }
        return count;
    }

    /** Generate valid neighbor states */
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
                char tmp = tiles[x * 3 + y];
                tiles[x * 3 + y] = tiles[nx * 3 + ny];
                tiles[nx * 3 + ny] = tmp;
                result.add(new String(tiles));
            }
        }
        return result;
    }

    /** Node stores state and heuristic cost */
    static class Node implements Comparable<Node> {
        String state;
        int cost;

        Node(String state, int cost) {
            this.state = state;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    /** Run Best First Search */
    static void bestFirst(String start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        pq.add(new Node(start, heuristic(start)));
        parent.put(start, "");

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited.contains(cur.state)) continue;
            visited.add(cur.state);

            if (cur.state.equals(GOAL)) {
                // Reconstruct path
                List<String> path = new ArrayList<>();
                String t = GOAL;
                while (!t.isEmpty()) {
                    path.add(t);
                    t = parent.get(t);
                }
                Collections.reverse(path);
                System.out.println("Best First Search Solution:");
                for (String s : path) {
                    printBoard(s);
                }
                return;
            }

            for (String next : neighbors(cur.state)) {
                if (!visited.contains(next)) {
                    parent.put(next, cur.state);
                    pq.add(new Node(next, heuristic(next)));
                }
            }
        }
        System.out.println("No solution found.");
    }

    static void printBoard(String state) {
        for (int i = 0; i < 9; i++) {
            System.out.print(state.charAt(i) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String start = "123405678";
        System.out.println("Start state:");
        printBoard(start);
        bestFirst(start);
    }
}
