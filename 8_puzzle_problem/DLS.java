import java.util.*;

/**
 * 8 Puzzle Problem - Depth Limited Search (DLS)
 * Like DFS but stops searching beyond a given depth limit.
 * Goal state: "123456780"
 */
public class DLS {

    static final String GOAL = "123456780";
    static Set<String> visited = new HashSet<>();

    /** Generate valid neighbor states */
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

    /**
     * Depth Limited Search
     * @param current current state
     * @param depth remaining depth limit
     * @return true if goal found within depth
     */
    static boolean dls(String current, int depth) {
        if (depth < 0) return false;

        if (current.equals(GOAL)) {
            System.out.println("Goal Found!");
            return true;
        }

        visited.add(current);

        for (String next : moves(current)) {
            if (!visited.contains(next)) {
                if (dls(next, depth - 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String start = "123405678";
        int depthLimit = 5;

        System.out.println("Depth Limited Search on 8 Puzzle (limit=" + depthLimit + ")");
        System.out.println("Start state:");
        for (int i = 0; i < 9; i++) {
            System.out.print(start.charAt(i) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();

        boolean found = dls(start, depthLimit);
        if (!found) {
            System.out.println("Goal not found within depth limit of " + depthLimit);
        }
    }
}
