import java.util.*;

/**
 * Shortest Route Finding - Best First Search
 * Uses only heuristic h(n) to navigate toward the goal (greedy).
 * Does NOT guarantee optimal cost but explores promising nodes first.
 */
public class BestFirstSearch {

    static Map<Character, List<int[]>> graph = new HashMap<>();
    static Map<Character, Integer> h = new HashMap<>();

    static class Node implements Comparable<Node> {
        char vertex;
        int hValue;

        Node(char v, int hv) { vertex = v; hValue = hv; }

        @Override
        public int compareTo(Node o) { return Integer.compare(this.hValue, o.hValue); }
    }

    static void addEdge(char from, char to, int weight) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, weight});
    }

    public static void main(String[] args) {
        // Graph (unweighted traversal, heuristic guides direction)
        addEdge('A', 'B', 1); addEdge('A', 'C', 1);
        addEdge('B', 'D', 1); addEdge('B', 'E', 1);
        addEdge('C', 'F', 1);
        addEdge('E', 'G', 1);
        addEdge('F', 'G', 1);

        // Heuristic: estimated distance to goal 'G'
        h.put('A', 10); h.put('B', 8); h.put('C', 5);
        h.put('D', 7);  h.put('E', 3); h.put('F', 6); h.put('G', 0);

        System.out.println("=== Shortest Route Finding - Best First Search ===");
        System.out.print("Traversal order: ");

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Character> visited = new HashSet<>();
        pq.add(new Node('A', h.get('A')));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited.contains(cur.vertex)) continue;
            visited.add(cur.vertex);
            System.out.print(cur.vertex + " ");

            if (cur.vertex == 'G') {
                System.out.println("\nGoal reached!");
                return;
            }

            for (int[] neighbor : graph.getOrDefault(cur.vertex, Collections.emptyList())) {
                char next = (char) neighbor[0];
                if (!visited.contains(next)) {
                    pq.add(new Node(next, h.getOrDefault(next, 0)));
                }
            }
        }
        System.out.println("\nGoal not reachable.");
    }
}
