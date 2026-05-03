import java.util.*;

/**
 * Vegetable and Fruit Classification System
 * Reimplemented from Prolog: classifies items based on plant part and taste.
 * Categories: fruit, vegetable, sweet_fruit, root_vegetable, leafy_vegetable, culinary_vegetable
 */
public class VegeFruitClassification {

    // Facts: plant part of each item
    static final Map<String, String> PLANT_PART = new HashMap<>();
    // Facts: taste of each item
    static final Map<String, String> TASTE = new HashMap<>();

    static {
        PLANT_PART.put("apple", "fruit");   PLANT_PART.put("banana", "fruit");
        PLANT_PART.put("mango", "fruit");   PLANT_PART.put("tomato", "fruit");
        PLANT_PART.put("cucumber", "fruit");
        PLANT_PART.put("carrot", "root");   PLANT_PART.put("potato", "stem");
        PLANT_PART.put("spinach", "leaf");

        TASTE.put("apple", "sweet");   TASTE.put("banana", "sweet");
        TASTE.put("mango", "sweet");   TASTE.put("tomato", "sweet");
        TASTE.put("cucumber", "mild"); TASTE.put("carrot", "mild");
        TASTE.put("potato", "mild");   TASTE.put("spinach", "bitter");
    }

    // --- Classification rules (mirrors Prolog rules) ---

    static boolean isFruit(String x)           { return "fruit".equals(PLANT_PART.get(x)); }
    static boolean isVegetable(String x)       {
        String p = PLANT_PART.get(x);
        return "root".equals(p) || "stem".equals(p) || "leaf".equals(p);
    }
    static boolean isSweetFruit(String x)      { return isFruit(x) && "sweet".equals(TASTE.get(x)); }
    static boolean isRootVegetable(String x)   { return "root".equals(PLANT_PART.get(x)); }
    static boolean isLeafyVegetable(String x)  { return "leaf".equals(PLANT_PART.get(x)); }
    /** Culinary vegetable: botanically a fruit but not sweet (e.g. cucumber, tomato in cooking) */
    static boolean isCulinaryVegetable(String x) {
        return isFruit(x) && !"sweet".equals(TASTE.get(x));
    }

    static void classify(String item) {
        System.out.println("\nClassification for: " + item);
        System.out.println("  Plant part:           " + PLANT_PART.getOrDefault(item, "unknown"));
        System.out.println("  Taste:                " + TASTE.getOrDefault(item, "unknown"));
        System.out.println("  Fruit:                " + isFruit(item));
        System.out.println("  Vegetable:            " + isVegetable(item));
        System.out.println("  Sweet fruit:          " + isSweetFruit(item));
        System.out.println("  Root vegetable:       " + isRootVegetable(item));
        System.out.println("  Leafy vegetable:      " + isLeafyVegetable(item));
        System.out.println("  Culinary vegetable:   " + isCulinaryVegetable(item));
    }

    public static void main(String[] args) {
        System.out.println("=== Vegetable & Fruit Classification System ===");
        String[] items = {"apple", "banana", "mango", "carrot", "potato", "spinach", "tomato", "cucumber"};
        for (String item : items) {
            classify(item);
        }
    }
}
