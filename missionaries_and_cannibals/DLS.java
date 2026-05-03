import java.util.*;

/**
 * Missionaries and Cannibals - Depth Limited Search (DLS)
 */
public class DLS {

    static int[][] moves = {{1,0},{2,0},{0,1},{0,2},{1,1}};
    static Set<String> visited = new HashSet<>();

    static String key(int ml, int cl, int b) { return ml + "," + cl + "," + b; }

    static boolean isValid(int ml, int cl) {
        int mr = 3 - ml, cr = 3 - cl;
        if (ml < 0 || cl < 0 || ml > 3 || cl > 3) return false;
        if (ml > 0 && ml < cl) return false;
        if (mr > 0 && mr < cr) return false;
        return true;
    }

    static boolean dls(int ml, int cl, int boat, int depth) {
        if (depth < 0) return false;

        if (ml == 0 && cl == 0 && boat == 1) {
            System.out.println("Goal Reached!");
            return true;
        }

        visited.add(key(ml, cl, boat));

        for (int[] move : moves) {
            int m = move[0], c = move[1];
            int nml, ncl, nBoat;

            if (boat == 0) { nml = ml - m; ncl = cl - c; nBoat = 1; }
            else           { nml = ml + m; ncl = cl + c; nBoat = 0; }

            String next = key(nml, ncl, nBoat);
            if (isValid(nml, ncl) && !visited.contains(next)) {
                if (dls(nml, ncl, nBoat, depth - 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int depthLimit = 15;
        System.out.println("=== Missionaries & Cannibals - DLS (limit=" + depthLimit + ") ===");
        boolean found = dls(3, 3, 0, depthLimit);
        if (!found) System.out.println("Goal not reachable within depth limit.");
    }
}
