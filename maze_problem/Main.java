import java.util.*;

/**
 * Maze Problem - BFS (Breadth First Search)
 * Finds the shortest path from start to goal in a 4x4 grid.
 * 0 = open cell, 1 = wall
 */
public class Main {

    static int N, M;
    static int[][] maze;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    /** Check if (x,y) is a valid, open, unvisited cell */
    static boolean isValid(int x, int y, boolean[][] visited) {
        return x >= 0 && x < N && y >= 0 && y < M
                && maze[x][y] == 0 && !visited[x][y];
    }

    /** Reconstruct and print path using parent map */
    static void printPath(Map<String, String> parent, int endX, int endY) {
        List<int[]> path = new ArrayList<>();
        String cur = endX + "," + endY;

        while (!cur.equals("-1,-1")) {
            String[] parts = cur.split(",");
            path.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])});
            cur = parent.get(cur);
        }
        Collections.reverse(path);

        System.out.println("Shortest Path (BFS):");
        for (int[] p : path) {
            System.out.println("  (" + p[0] + ", " + p[1] + ")");
        }
    }

    /** BFS from start to goal */
    static void bfs(int startX, int startY, int goalX, int goalY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        Map<String, String> parent = new HashMap<>();

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        parent.put(startX + "," + startY, "-1,-1");

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            if (x == goalX && y == goalY) {
                printPath(parent, goalX, goalY);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (isValid(nx, ny, visited)) {
                    visited[nx][ny] = true;
                    parent.put(nx + "," + ny, x + "," + y);
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        System.out.println("No path exists.");
    }

    public static void main(String[] args) {
        maze = new int[][]{
            {0, 1, 0, 0},
            {0, 0, 0, 1},
            {1, 0, 1, 0},
            {0, 0, 0, 0}
        };
        N = maze.length;
        M = maze[0].length;

        System.out.println("=== Maze Problem - BFS ===");
        bfs(0, 0, 3, 3);
    }
}
