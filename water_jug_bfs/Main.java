import java.util.*;

/**
 * Water Jug Problem - BFS (Breadth First Search)
 * Finds the shortest sequence of pours to measure the target amount.
 * Operations: fill, empty, pour between jugs.
 */
public class Main {

    static void printPath(Map<String, String> parent, int goalX, int goalY) {
        List<String> path = new ArrayList<>();
        String cur = goalX + "," + goalY;

        while (!"-1,-1".equals(parent.get(cur))) {
            path.add(cur);
            cur = parent.get(cur);
        }
        path.add("0,0");
        Collections.reverse(path);

        System.out.println("Shortest Solution Path:");
        for (String p : path) {
            String[] parts = p.split(",");
            System.out.println("  J1=" + parts[0] + "L, J2=" + parts[1] + "L");
        }
    }

    static void bfs(int jug1, int jug2, int target) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(new int[]{0, 0});
        visited.add("0,0");
        parent.put("0,0", "-1,-1");

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            if (x == target || y == target) {
                printPath(parent, x, y);
                return;
            }

            // All 6 possible operations
            List<int[]> nextStates = new ArrayList<>();
            nextStates.add(new int[]{jug1, y});             // Fill J1
            nextStates.add(new int[]{x, jug2});             // Fill J2
            nextStates.add(new int[]{0, y});                // Empty J1
            nextStates.add(new int[]{x, 0});                // Empty J2
            int pour = Math.min(x, jug2 - y);
            nextStates.add(new int[]{x - pour, y + pour}); // Pour J1 -> J2
            pour = Math.min(y, jug1 - x);
            nextStates.add(new int[]{x + pour, y - pour}); // Pour J2 -> J1

            for (int[] next : nextStates) {
                String nk = next[0] + "," + next[1];
                if (!visited.contains(nk)) {
                    visited.add(nk);
                    parent.put(nk, x + "," + y);
                    queue.add(next);
                }
            }
        }
        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        int jug1 = 4, jug2 = 3, target = 2;
        System.out.println("=== Water Jug Problem - BFS ===");
        System.out.println("Jug1=" + jug1 + "L, Jug2=" + jug2 + "L, Target=" + target + "L\n");
        bfs(jug1, jug2, target);
    }
}
