import java.util.*;

/**
 * Water Jug Problem - DFS
 * Two jugs: J1 (capacity 4L), J2 (capacity 3L)
 * Goal: measure exactly 2L in either jug
 * State: (x, y) = current water in J1 and J2
 */
public class DFS {

    static final int J1 = 4, J2 = 3, TARGET = 2;
    static Set<String> visited = new HashSet<>();
    static Map<String, String> parent = new HashMap<>();

    static String key(int x, int y) { return x + "," + y; }

    /** Generate all possible next states from (x, y) */
    static List<int[]> nextStates(int x, int y) {
        List<int[]> states = new ArrayList<>();
        // Fill J1
        states.add(new int[]{J1, y});
        // Fill J2
        states.add(new int[]{x, J2});
        // Empty J1
        states.add(new int[]{0, y});
        // Empty J2
        states.add(new int[]{x, 0});
        // Pour J1 -> J2
        int pour = Math.min(x, J2 - y);
        states.add(new int[]{x - pour, y + pour});
        // Pour J2 -> J1
        pour = Math.min(y, J1 - x);
        states.add(new int[]{x + pour, y - pour});
        return states;
    }

    /** DFS from (x,y) until TARGET found in either jug */
    static boolean dfs(int x, int y) {
        String cur = key(x, y);
        if (visited.contains(cur)) return false;
        visited.add(cur);

        if (x == TARGET || y == TARGET) return true;

        for (int[] next : nextStates(x, y)) {
            String nk = key(next[0], next[1]);
            if (!visited.contains(nk)) {
                parent.put(nk, cur);
                if (dfs(next[0], next[1])) return true;
            }
        }
        return false;
    }

    static void printPath(int goalX, int goalY) {
        List<String> path = new ArrayList<>();
        String cur = key(goalX, goalY);
        while (parent.containsKey(cur)) {
            path.add(cur);
            cur = parent.get(cur);
        }
        path.add("0,0");
        Collections.reverse(path);

        System.out.println("DFS Path:");
        for (String p : path) {
            System.out.println("  " + p.replace(",", " | ") + "  (J1, J2)");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Water Jug Problem - DFS ===");
        System.out.println("J1 capacity: " + J1 + "L, J2 capacity: " + J2 + "L, Target: " + TARGET + "L");

        if (dfs(0, 0)) {
            // Find which goal state was reached
            for (String k : visited) {
                String[] p = k.split(",");
                int x = Integer.parseInt(p[0]), y = Integer.parseInt(p[1]);
                if (x == TARGET || y == TARGET) {
                    printPath(x, y);
                    break;
                }
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
