import java.util.*;

/**
 * Maze Problem - Depth Limited Search (DLS)
 * Searches for a path in a 4x4 maze with a depth limit.
 * 0 = open, 1 = wall
 */
public class DLS {

    static final int N = 4, M = 4;
    static int[][] maze = {
        {0, 1, 0, 0},
        {0, 0, 0, 1},
        {1, 0, 1, 0},
        {0, 0, 0, 0}
    };

    static boolean[][] visited = new boolean[N][M];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    /** Depth Limited Search */
    static boolean dls(int x, int y, int goalX, int goalY, int depth) {
        if (depth < 0) return false;

        if (x == goalX && y == goalY) {
            System.out.println("Goal reached at (" + x + ", " + y + ")");
            return true;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M
                    && maze[nx][ny] == 0 && !visited[nx][ny]) {
                if (dls(nx, ny, goalX, goalY, depth - 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int depthLimit = 8;
        System.out.println("=== Maze Problem - DLS (limit=" + depthLimit + ") ===");
        boolean found = dls(0, 0, 3, 3, depthLimit);
        if (!found) {
            System.out.println("Goal not found within depth limit " + depthLimit);
        }
    }
}
