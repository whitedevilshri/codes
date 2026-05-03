import java.util.*;

/**
 * Bird Classification System
 * Reimplemented from Prolog: encodes facts about birds and classification rules.
 * Classifies birds as: flying_bird, flightless_bird, water_bird, raptor, small_bird, aquatic_bird
 */
public class BirdClassification {

    // --- Facts: bird properties ---
    static final Set<String> HAS_FEATHERS = new HashSet<>(Arrays.asList("sparrow", "eagle", "penguin", "ostrich"));
    static final Set<String> FLIES        = new HashSet<>(Arrays.asList("sparrow", "eagle"));
    static final Set<String> CANNOT_FLY   = new HashSet<>(Arrays.asList("penguin", "ostrich"));
    static final Set<String> SWIMS        = new HashSet<>(Arrays.asList("penguin"));

    // --- Rules ---

    static boolean isBird(String x)          { return HAS_FEATHERS.contains(x); }
    static boolean laysEggs(String x)        { return isBird(x); }
    static boolean isFlying(String x)        { return isBird(x) && FLIES.contains(x); }
    static boolean isFlightless(String x)    { return isBird(x) && CANNOT_FLY.contains(x); }
    static boolean isWaterBird(String x)     { return isBird(x) && SWIMS.contains(x); }
    static boolean isRaptor(String x)        { return isBird(x) && FLIES.contains(x) && x.equals("eagle"); }
    static boolean isSmallBird(String x)     { return isBird(x) && FLIES.contains(x) && x.equals("sparrow"); }
    static boolean isAquaticBird(String x)   { return isWaterBird(x); }

    /** Classify a bird and print all applicable categories */
    static void classify(String bird) {
        if (!isBird(bird)) {
            System.out.println(bird + " is not a known bird.");
            return;
        }
        System.out.println("\nClassification for: " + bird);
        System.out.println("  Is a bird:        " + isBird(bird));
        System.out.println("  Lays eggs:        " + laysEggs(bird));
        System.out.println("  Flying bird:      " + isFlying(bird));
        System.out.println("  Flightless bird:  " + isFlightless(bird));
        System.out.println("  Water bird:       " + isWaterBird(bird));
        System.out.println("  Raptor:           " + isRaptor(bird));
        System.out.println("  Small bird:       " + isSmallBird(bird));
        System.out.println("  Aquatic bird:     " + isAquaticBird(bird));
    }

    public static void main(String[] args) {
        System.out.println("=== Bird Classification System ===");
        String[] birds = {"sparrow", "eagle", "penguin", "ostrich"};
        for (String bird : birds) {
            classify(bird);
        }

        // Interactive query
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a bird name to classify (or 'quit'): ");
        String input = sc.nextLine().trim().toLowerCase();
        if (!input.equals("quit")) {
            classify(input);
        }
        sc.close();
    }
}
