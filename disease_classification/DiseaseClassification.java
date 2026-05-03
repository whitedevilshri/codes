import java.util.*;

/**
 * Disease Diagnosis Expert System
 * Reimplemented from Prolog: uses rule-based reasoning to diagnose diseases
 * based on user-reported symptoms.
 * Diseases: flu, covid19, malaria, dengue, common_cold
 */
public class DiseaseClassification {

    static Scanner sc = new Scanner(System.in);
    static Map<String, Boolean> known = new HashMap<>(); // symptom -> answer

    /** Ask user about a symptom; cache answer to avoid re-asking */
    static boolean verify(String symptom) {
        if (known.containsKey(symptom)) return known.get(symptom);

        System.out.print("Do you have " + symptom.replace("_", " ") + "? (yes/no): ");
        String response = sc.nextLine().trim().toLowerCase();
        boolean answer = response.equals("yes");
        known.put(symptom, answer);
        return answer;
    }

    /**
     * Try each disease rule; return matching disease name or null.
     * Rules mirror the original Prolog knowledge base.
     */
    static String diagnose() {
        // Flu: fever + cough + body_ache
        if (verify("fever") && verify("cough") && verify("body_ache"))
            return "Flu";

        // COVID-19: fever + cough + loss_of_taste
        if (verify("fever") && verify("cough") && verify("loss_of_taste"))
            return "COVID-19";

        // Malaria: fever + chills + sweating
        if (verify("fever") && verify("chills") && verify("sweating"))
            return "Malaria";

        // Dengue: fever + headache + joint_pain
        if (verify("fever") && verify("headache") && verify("joint_pain"))
            return "Dengue";

        // Common Cold: sneezing + runny_nose + cough
        if (verify("sneezing") && verify("runny_nose") && verify("cough"))
            return "Common Cold";

        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== Disease Diagnosis Expert System ===");
        System.out.println("Please answer the following questions about your symptoms.\n");

        String disease = diagnose();

        if (disease != null) {
            System.out.println("\nPossible Disease: " + disease);
        } else {
            System.out.println("\nNo disease identified based on symptoms given.");
        }
        System.out.println("(Note: This is for educational purposes only. Consult a doctor.)");
        sc.close();
    }
}
