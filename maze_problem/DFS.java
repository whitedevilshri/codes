import java.util.*;

/**
 * Maze Problem - Depth First Search (DFS)
 * Finds a path from (0,0) to (3,3) in a 4x4 grid.
 * 0 = open, 1 = wall
 */
public class DFS {

    static final int N = 4, M = 4;
    static int[][] maze = {
        {0, 1, 0, 0},
        {0, 0, 0, 1},
        {1, 0, 1, 0},
        {0, 0, 0, 0}
    };

    static boolean[][] visited = new boolean[N][M];
    static Map<String, String> parent = new HashMap<>();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static String key(int x, int y) { return x + "," + y; }

    /** DFS from (x,y) to (goalX, goalY) */
    static boolean dfs(int x, int y, int goalX, int goalY) {
        if (x == goalX && y == goalY) return true;

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M
                    && maze[nx][ny] == 0 && !visited[nx][ny]) {
                parent.put(key(nx, ny), key(x, y));
                if (dfs(nx, ny, goalX, goalY)) return true;
            }
        }
        return false;
    }

    /** Print the path from start to goal using parent map */
    static void printPath(int goalX, int goalY) {
        List<String> path = new ArrayList<>();
        String cur = key(goalX, goalY);
        while (parent.containsKey(cur)) {
            path.add(cur);
            cur = parent.get(cur);
        }
        path.add("0,0");
        Collections.reverse(path);

        System.out.println("DFS Path from (0,0) to (" + goalX + "," + goalY + "):");
        for (String p : path) {
            String[] parts = p.split(",");
            System.out.println("  (" + parts[0] + ", " + parts[1] + ")");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Maze Problem - DFS ===");
        if (dfs(0, 0, 3, 3)) {
            printPath(3, 3);
        } else {
            System.out.println("No path found.");
        }
    }
}
