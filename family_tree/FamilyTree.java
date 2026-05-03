import java.util.*;

/**
 * Family Tree Knowledge Base
 * Reimplemented from Prolog: encodes family facts and derives relationships
 * using Java logic (rules implemented as methods).
 * Relationships: parent, father, mother, sibling, brother, sister,
 *                grandparent, grandfather, grandmother, uncle, aunt
 */
public class FamilyTree {

    // --- Facts: gender ---
    static final Set<String> MALES   = new HashSet<>(Arrays.asList("john", "paul", "mike", "tom"));
    static final Set<String> FEMALES = new HashSet<>(Arrays.asList("mary", "lisa", "susan", "anna"));

    // --- Facts: parent relationships (parent -> set of children) ---
    static final Map<String, Set<String>> PARENT_OF = new HashMap<>();

    static {
        addParent("john", "paul");  addParent("mary", "paul");
        addParent("john", "lisa");  addParent("mary", "lisa");
        addParent("paul", "mike");  addParent("susan", "mike");
        addParent("lisa", "tom");   addParent("anna", "tom");
    }

    static void addParent(String parent, String child) {
        PARENT_OF.computeIfAbsent(parent, k -> new HashSet<>()).add(child);
    }

    // --- Rules ---

    static boolean isParent(String x, String y) {
        return PARENT_OF.getOrDefault(x, Collections.emptySet()).contains(y);
    }

    static boolean isMale(String x)   { return MALES.contains(x); }
    static boolean isFemale(String x) { return FEMALES.contains(x); }

    static boolean isFather(String x, String y) { return isParent(x, y) && isMale(x); }
    static boolean isMother(String x, String y) { return isParent(x, y) && isFemale(x); }

    static boolean isSibling(String x, String y) {
        if (x.equals(y)) return false;
        // Share at least one parent
        for (String p : PARENT_OF.keySet()) {
            if (isParent(p, x) && isParent(p, y)) return true;
        }
        return false;
    }

    static boolean isBrother(String x, String y) { return isSibling(x, y) && isMale(x); }
    static boolean isSister(String x, String y)  { return isSibling(x, y) && isFemale(x); }

    static boolean isGrandparent(String x, String y) {
        for (String z : getAllPersons()) {
            if (isParent(x, z) && isParent(z, y)) return true;
        }
        return false;
    }

    static boolean isGrandfather(String x, String y) { return isGrandparent(x, y) && isMale(x); }
    static boolean isGrandmother(String x, String y) { return isGrandparent(x, y) && isFemale(x); }

    static boolean isUncle(String x, String y) {
        for (String p : getAllPersons()) {
            if (isBrother(x, p) && isParent(p, y)) return true;
        }
        return false;
    }

    static boolean isAunt(String x, String y) {
        for (String p : getAllPersons()) {
            if (isSister(x, p) && isParent(p, y)) return true;
        }
        return false;
    }

    /** All known persons */
    static Set<String> getAllPersons() {
        Set<String> all = new HashSet<>();
        all.addAll(MALES);
        all.addAll(FEMALES);
        return all;
    }

    /** Find and list all X such that relation(X, y) is true */
    static List<String> findAll(String person, String relation) {
        List<String> results = new ArrayList<>();
        for (String x : getAllPersons()) {
            boolean match = switch (relation) {
                case "father"      -> isFather(x, person);
                case "mother"      -> isMother(x, person);
                case "sibling"     -> isSibling(x, person);
                case "brother"     -> isBrother(x, person);
                case "sister"      -> isSister(x, person);
                case "grandparent" -> isGrandparent(x, person);
                case "grandfather" -> isGrandfather(x, person);
                case "grandmother" -> isGrandmother(x, person);
                case "uncle"       -> isUncle(x, person);
                case "aunt"        -> isAunt(x, person);
                default            -> false;
            };
            if (match) results.add(x);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println("=== Family Tree Knowledge Base ===\n");

        String[] persons = {"paul", "mike", "tom"};
        String[] relations = {"father", "mother", "sibling", "grandparent", "uncle", "aunt"};

        for (String person : persons) {
            System.out.println("Relationships for: " + person);
            for (String rel : relations) {
                List<String> found = findAll(person, rel);
                if (!found.isEmpty()) {
                    System.out.println("  " + rel + "(" + person + "): " + found);
                }
            }
            System.out.println();
        }
    }
}
