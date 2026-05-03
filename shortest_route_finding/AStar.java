import java.util.*;

/**
 * Shortest Route Finding - A* Search
 * Finds optimal path from node 'A' to node 'G' in a weighted graph.
 * Uses both actual cost (g) and heuristic (h) to guide search.
 */
public class AStar {

    // Graph: adjacency list with edge weights
    static Map<Character, List<int[]>> graph = new HashMap<>();

    // Heuristic values (estimated cost to goal 'G')
    static Map<Character, Integer> h = new HashMap<>();

    static class Node implements Comparable<Node> {
        char vertex;
        int g; // actual cost from start
        int f; // f = g + h

        Node(char v, int g, int f) { vertex = v; this.g = g; this.f = f; }

        @Override
        public int compareTo(Node o) { return Integer.compare(this.f, o.f); }
    }

    static void addEdge(char from, char to, int weight) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, weight});
    }

    public static void main(String[] args) {
        // Build graph (same as original C++ code)
        addEdge('A', 'B', 1); addEdge('A', 'C', 4);
        addEdge('B', 'D', 2); addEdge('B', 'E', 5);
        addEdge('C', 'F', 1);
        addEdge('D', 'G', 7);
        addEdge('E', 'G', 2);
        addEdge('F', 'G', 3);

        // Heuristic estimates to goal 'G'
        h.put('A', 10); h.put('B', 8); h.put('C', 5);
        h.put('D', 7);  h.put('E', 3); h.put('F', 6); h.put('G', 0);

        System.out.println("=== Shortest Route Finding - A* ===");

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Character, Integer> dist = new HashMap<>();
        Map<Character, Character> parent = new HashMap<>();

        pq.add(new Node('A', 0, h.get('A')));
        dist.put('A', 0);
        parent.put('A', null);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.vertex == 'G') {
                // Reconstruct path
                List<Character> path = new ArrayList<>();
                Character step = 'G';
                while (step != null) { path.add(step); step = parent.get(step); }
                Collections.reverse(path);

                System.out.println("Path: " + path);
                System.out.println("Cost: " + cur.g);
                return;
            }

            for (int[] neighbor : graph.getOrDefault(cur.vertex, Collections.emptyList())) {
                char next = (char) neighbor[0];
                int newG = cur.g + neighbor[1];

                if (!dist.containsKey(next) || newG < dist.get(next)) {
                    dist.put(next, newG);
                    parent.put(next, cur.vertex);
                    pq.add(new Node(next, newG, newG + h.getOrDefault(next, 0)));
                }
            }
        }
        System.out.println("Goal not reachable.");
    }
}
