import java.util.*;

/**
 * Animal Identification Expert System
 * Reimplemented from Prolog: uses if-else rule chains to identify animals
 * based on user answers about features.
 * Animals: tiger, lion, elephant, giraffe, eagle, penguin
 */
public class AnimalIdentification {

    static Scanner sc = new Scanner(System.in);
    static Map<String, String> known = new HashMap<>(); // feature -> "yes"/"no"

    /** Ask user about a feature; cache the answer */
    static boolean verify(String feature) {
        if (known.containsKey(feature)) {
            return known.get(feature).equals("yes");
        }
        System.out.print("Does the animal have " + feature + "? (yes/no): ");
        String response = sc.nextLine().trim().toLowerCase();
        known.put(feature, response);
        return response.equals("yes");
    }

    // --- Classification helpers ---

    static boolean isMammal() {
        return verify("hair") || verify("gives_milk");
    }

    static boolean isBird() {
        return verify("feathers");
    }

    static boolean isCarnivore() {
        return verify("eats_meat");
    }

    static boolean isHerbivore() {
        return verify("eats_plants");
    }

    // --- Animal identification rules (mirroring Prolog facts) ---

    static String identify() {
        // Try each animal rule in order
        // Tiger: mammal + carnivore + stripes
        known.clear();
        System.out.println("\nLet's identify the animal. Answer yes/no to each question.\n");

        boolean mammal = isMammal();
        boolean bird = !mammal && isBird();

        if (mammal) {
            boolean carnivore = isCarnivore();
            boolean herbivore = !carnivore && isHerbivore();

            if (carnivore) {
                if (verify("stripes")) return "Tiger";
                if (verify("mane"))    return "Lion";
            }
            if (herbivore) {
                if (verify("trunk"))     return "Elephant";
                if (verify("long_neck")) return "Giraffe";
            }
        }

        if (bird) {
            boolean flies = verify("can_fly");
            if (flies && verify("sharp_vision")) return "Eagle";
            if (!flies && verify("swims"))        return "Penguin";
        }

        return "Unknown animal";
    }

    public static void main(String[] args) {
        System.out.println("=== Animal Identification Expert System ===");
        String animal = identify();
        System.out.println("\nThe animal is: " + animal);
        sc.close();
    }
}
