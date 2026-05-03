import java.util.*;

/**
 * Missionaries and Cannibals - DFS
 * State: (ML, CL, Boat) where ML/CL = missionaries/cannibals on left bank
 *        Boat = 0 (left) or 1 (right)
 * Goal: move all 3 missionaries and 3 cannibals to right bank safely.
 */
public class DFS {

    // Possible moves: (missionaries, cannibals) to send across
    static int[][] moves = {{1,0},{2,0},{0,1},{0,2},{1,1}};

    static Set<String> visited = new HashSet<>();
    static Map<String, String> parent = new HashMap<>();

    static String key(int ml, int cl, int b) { return ml + "," + cl + "," + b; }

    /** Validate state: missionaries never outnumbered by cannibals on either side */
    static boolean isValid(int ml, int cl) {
        int mr = 3 - ml, cr = 3 - cl;
        if (ml < 0 || cl < 0 || ml > 3 || cl > 3) return false;
        if (ml > 0 && ml < cl)   return false; // Left side unsafe
        if (mr > 0 && mr < cr)   return false; // Right side unsafe
        return true;
    }

    /** DFS from current state to goal (0,0,1) */
    static boolean dfs(int ml, int cl, int boat) {
        String cur = key(ml, cl, boat);
        if (visited.contains(cur)) return false;
        visited.add(cur);

        if (ml == 0 && cl == 0 && boat == 1) return true; // Goal reached

        for (int[] move : moves) {
            int m = move[0], c = move[1];
            int nml, ncl, nBoat;

            if (boat == 0) { // Boat on left: move people to right
                nml = ml - m; ncl = cl - c; nBoat = 1;
            } else {          // Boat on right: move people to left
                nml = ml + m; ncl = cl + c; nBoat = 0;
            }

            if (isValid(nml, ncl)) {
                String next = key(nml, ncl, nBoat);
                if (!visited.contains(next)) {
                    parent.put(next, cur);
                    if (dfs(nml, ncl, nBoat)) return true;
                }
            }
        }
        return false;
    }

    /** Print the solution path */
    static void printPath() {
        List<String> path = new ArrayList<>();
        String cur = key(0, 0, 1);
        while (parent.containsKey(cur)) {
            path.add(cur);
            cur = parent.get(cur);
        }
        path.add(key(3, 3, 0));
        Collections.reverse(path);

        System.out.println("DFS Solution Path:");
        for (String step : path) {
            String[] p = step.split(",");
            int ml = Integer.parseInt(p[0]), cl = Integer.parseInt(p[1]), b = Integer.parseInt(p[2]);
            System.out.println("  ML=" + ml + " CL=" + cl + " | MR=" + (3-ml) + " CR=" + (3-cl)
                    + " | Boat=" + (b == 0 ? "Left" : "Right"));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Missionaries & Cannibals - DFS ===");
        if (dfs(3, 3, 0)) {
            printPath();
        } else {
            System.out.println("No solution found.");
        }
    }
}
