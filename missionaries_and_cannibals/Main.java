import java.util.*;

/**
 * Missionaries and Cannibals - BFS (Breadth First Search)
 * Finds the shortest sequence of moves to transfer all 3 missionaries
 * and 3 cannibals from the left bank to the right bank safely.
 */
public class Main {

    static int[][] moves = {{1,0},{2,0},{0,1},{0,2},{1,1}};

    record State(int ml, int cl, int boat) {}

    static boolean isValid(int ml, int cl) {
        int mr = 3 - ml, cr = 3 - cl;
        if (ml < 0 || cl < 0 || ml > 3 || cl > 3) return false;
        if (ml > 0 && ml < cl) return false;  // Left bank cannibals outnumber missionaries
        if (mr > 0 && mr < cr) return false;  // Right bank cannibals outnumber missionaries
        return true;
    }

    static void printPath(Map<State, State> parent, State goal) {
        List<State> path = new ArrayList<>();
        State cur = goal;

        while (parent.get(cur) != null) {
            path.add(cur);
            cur = parent.get(cur);
        }
        path.add(new State(3, 3, 0)); // Start state
        Collections.reverse(path);

        System.out.println("Solution Path (BFS):");
        for (State s : path) {
            System.out.printf("  ML=%d CL=%d | MR=%d CR=%d | Boat=%s%n",
                s.ml(), s.cl(), 3 - s.ml(), 3 - s.cl(),
                s.boat() == 0 ? "Left" : "Right");
        }
    }

    static void bfs() {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parent = new HashMap<>();

        State start = new State(3, 3, 0);
        State goal  = new State(0, 0, 1);

        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.equals(goal)) {
                printPath(parent, goal);
                return;
            }

            for (int[] move : moves) {
                int m = move[0], c = move[1];
                int nml, ncl, nBoat;

                if (cur.boat() == 0) { nml = cur.ml() - m; ncl = cur.cl() - c; nBoat = 1; }
                else                 { nml = cur.ml() + m; ncl = cur.cl() + c; nBoat = 0; }

                if (isValid(nml, ncl)) {
                    State next = new State(nml, ncl, nBoat);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        parent.put(next, cur);
                        queue.add(next);
                    }
                }
            }
        }
        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        System.out.println("=== Missionaries & Cannibals - BFS ===");
        bfs();
    }
}
