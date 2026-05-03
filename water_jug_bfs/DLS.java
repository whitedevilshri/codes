import java.util.*;

/**
 * Water Jug Problem - Depth Limited Search (DLS)
 */
public class DLS {

    static final int J1 = 4, J2 = 3, TARGET = 2;
    static Set<String> visited = new HashSet<>();

    static String key(int x, int y) { return x + "," + y; }

    static List<int[]> nextStates(int x, int y) {
        List<int[]> states = new ArrayList<>();
        states.add(new int[]{J1, y});
        states.add(new int[]{x, J2});
        states.add(new int[]{0, y});
        states.add(new int[]{x, 0});
        int pour = Math.min(x, J2 - y);
        states.add(new int[]{x - pour, y + pour});
        pour = Math.min(y, J1 - x);
        states.add(new int[]{x + pour, y - pour});
        return states;
    }

    static boolean dls(int x, int y, int depth) {
        if (depth < 0) return false;

        if (x == TARGET || y == TARGET) {
            System.out.println("Goal Reached: (" + x + ", " + y + ")");
            return true;
        }

        visited.add(key(x, y));

        for (int[] next : nextStates(x, y)) {
            String nk = key(next[0], next[1]);
            if (!visited.contains(nk)) {
                if (dls(next[0], next[1], depth - 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int depthLimit = 8;
        System.out.println("=== Water Jug Problem - DLS (limit=" + depthLimit + ") ===");
        boolean found = dls(0, 0, depthLimit);
        if (!found) System.out.println("Goal not found within depth limit.");
    }
}
