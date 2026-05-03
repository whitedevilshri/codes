import java.util.*;

/**
 * Cryptarithmetic - SEND + MORE = MONEY
 * CSP (Constraint Satisfaction Problem) solved via backtracking.
 * Each letter maps to a unique digit 0-9; leading digits cannot be 0.
 */
public class CSP {

    // Letters involved in SEND + MORE = MONEY
    static char[] letters = {'S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y'};
    static int[] val = new int[256];      // letter -> digit mapping
    static boolean[] used = new boolean[10]; // digit usage tracking

    /** Check if current assignment satisfies SEND + MORE = MONEY */
    static boolean isValid() {
        if (val['S'] == 0 || val['M'] == 0) return false; // No leading zeros

        int SEND  = val['S'] * 1000 + val['E'] * 100 + val['N'] * 10 + val['D'];
        int MORE  = val['M'] * 1000 + val['O'] * 100 + val['R'] * 10 + val['E'];
        int MONEY = val['M'] * 10000 + val['O'] * 1000 + val['N'] * 100 + val['E'] * 10 + val['Y'];

        return SEND + MORE == MONEY;
    }

    /** Backtracking solver: assign digits to each letter */
    static boolean solve(int idx) {
        if (idx == letters.length) {
            return isValid();
        }

        for (int digit = 0; digit <= 9; digit++) {
            if (!used[digit]) {
                val[letters[idx]] = digit;
                used[digit] = true;

                if (solve(idx + 1)) return true;

                used[digit] = false; // Backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Cryptarithmetic: SEND + MORE = MONEY ===");
        Arrays.fill(used, false);

        if (solve(0)) {
            System.out.println("\nSolution found:");
            for (char c : letters) {
                System.out.println("  " + c + " = " + val[c]);
            }

            int SEND  = val['S'] * 1000 + val['E'] * 100 + val['N'] * 10 + val['D'];
            int MORE  = val['M'] * 1000 + val['O'] * 100 + val['R'] * 10 + val['E'];
            int MONEY = val['M'] * 10000 + val['O'] * 1000 + val['N'] * 100 + val['E'] * 10 + val['Y'];

            System.out.println("\n  " + SEND + " + " + MORE + " = " + MONEY);
        } else {
            System.out.println("No solution found.");
        }
    }
}
