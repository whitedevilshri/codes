import java.util.*;

/**
 * Family Tree Expert System
 * Reimplemented from Prolog: interactive menu to query family relationships.
 * Supports: father, mother, siblings, grandparent, uncle, ancestor queries.
 * Also includes ancestor (transitive parent) rule.
 */
public class FamilyTreeExpertSystem {

    // --- Facts: gender ---
    static final Set<String> MALES   = new HashSet<>(Arrays.asList("john", "paul", "mike", "tom"));
    static final Set<String> FEMALES = new HashSet<>(Arrays.asList("mary", "lisa", "susan", "anna"));

    // --- Facts: parent-child relationships ---
    static final Map<String, Set<String>> PARENT_OF = new HashMap<>();

    static {
        addParent("john", "paul");  addParent("mary", "paul");
        addParent("john", "lisa");  addParent("mary", "lisa");
        addParent("paul", "mike");  addParent("susan", "mike");
        addParent("lisa", "tom");   addParent("anna", "tom");
    }

    static void addParent(String p, String c) {
        PARENT_OF.computeIfAbsent(p, k -> new HashSet<>()).add(c);
    }

    static boolean isMale(String x)   { return MALES.contains(x); }
    static boolean isFemale(String x) { return FEMALES.contains(x); }

    static boolean isParent(String x, String y) {
        return PARENT_OF.getOrDefault(x, Collections.emptySet()).contains(y);
    }

    static boolean isFather(String x, String y) { return isParent(x, y) && isMale(x); }
    static boolean isMother(String x, String y) { return isParent(x, y) && isFemale(x); }

    static boolean isSibling(String x, String y) {
        if (x.equals(y)) return false;
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

    static boolean isUncle(String x, String y) {
        for (String p : getAllPersons()) {
            if (isBrother(x, p) && isParent(p, y)) return true;
        }
        return false;
    }

    /** Ancestor: transitive parent relationship */
    static boolean isAncestor(String x, String y) {
        if (isParent(x, y)) return true;
        for (String z : getAllPersons()) {
            if (isParent(x, z) && isAncestor(z, y)) return true;
        }
        return false;
    }

    static Set<String> getAllPersons() {
        Set<String> all = new HashSet<>();
        all.addAll(MALES); all.addAll(FEMALES);
        return all;
    }

    static String findFirst(String child, String relation) {
        for (String x : getAllPersons()) {
            boolean match = switch (relation) {
                case "father"      -> isFather(x, child);
                case "mother"      -> isMother(x, child);
                case "grandparent" -> isGrandparent(x, child);
                case "uncle"       -> isUncle(x, child);
                case "ancestor"    -> isAncestor(x, child);
                default            -> false;
            };
            if (match) return x;
        }
        return null;
    }

    static List<String> findSiblings(String person) {
        List<String> result = new ArrayList<>();
        for (String x : getAllPersons()) {
            if (isSibling(x, person)) result.add(x);
        }
        return result;
    }

    static List<String> findAncestors(String person) {
        List<String> result = new ArrayList<>();
        for (String x : getAllPersons()) {
            if (isAncestor(x, person)) result.add(x);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Family Tree Expert System ===");

        while (true) {
            System.out.println("\n1. Find Father");
            System.out.println("2. Find Mother");
            System.out.println("3. Find Siblings");
            System.out.println("4. Find Grandparent");
            System.out.println("5. Find Uncle");
            System.out.println("6. Find Ancestors");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");

            int choice;
            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { continue; }

            if (choice == 0) break;

            System.out.print("Enter person name: ");
            String name = sc.nextLine().trim().toLowerCase();

            switch (choice) {
                case 1 -> {
                    String father = findFirst(name, "father");
                    System.out.println(father != null ? "Father: " + father : "Not found.");
                }
                case 2 -> {
                    String mother = findFirst(name, "mother");
                    System.out.println(mother != null ? "Mother: " + mother : "Not found.");
                }
                case 3 -> {
                    List<String> siblings = findSiblings(name);
                    System.out.println(siblings.isEmpty() ? "No siblings found." : "Siblings: " + siblings);
                }
                case 4 -> {
                    String gp = findFirst(name, "grandparent");
                    System.out.println(gp != null ? "Grandparent: " + gp : "Not found.");
                }
                case 5 -> {
                    String uncle = findFirst(name, "uncle");
                    System.out.println(uncle != null ? "Uncle: " + uncle : "Not found.");
                }
                case 6 -> {
                    List<String> ancestors = findAncestors(name);
                    System.out.println(ancestors.isEmpty() ? "No ancestors found." : "Ancestors: " + ancestors);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
