import java.util.*;

/**
 * 8 Puzzle Problem - Depth First Search (DFS)
 * Explores states recursively using DFS strategy.
 * Goal state: "123456780"
 */
public class DFS {

    static final String GOAL = "123456780";
    static Set<String> visited = new HashSet<>();
    static Map<String, String> parent = new HashMap<>();

    /** Generate valid neighbor states by sliding blank */
    static List<String> moves(String state) {
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

    /** Recursive DFS */
    static boolean dfs(String current) {
        if (visited.contains(current)) return false;
        visited.add(current);

        if (current.equals(GOAL)) return true;

        for (String next : moves(current)) {
            if (!visited.contains(next)) {
                parent.put(next, current);
                if (dfs(next)) return true;
            }
        }
        return false;
    }

    /** Print path from start to goal using parent map */
    static void printPath(String goal) {
        List<String> path = new ArrayList<>();
        String cur = goal;
        while (parent.containsKey(cur)) {
            path.add(cur);
            cur = parent.get(cur);
        }
        path.add(cur); // add start
        Collections.reverse(path);

        System.out.println("DFS Solution Path:");
        for (String s : path) {
            for (int i = 0; i < 9; i++) {
                System.out.print(s.charAt(i) + " ");
                if (i % 3 == 2) System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String start = "123405678";
        System.out.println("DFS on 8 Puzzle. Start:");
        for (int i = 0; i < 9; i++) {
            System.out.print(start.charAt(i) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();

        if (dfs(start)) {
            printPath(GOAL);
        } else {
            System.out.println("No solution found.");
        }
    }
}
