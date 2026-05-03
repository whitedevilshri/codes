import java.util.*;

/**
 * Skillset Job Matching Expert System
 * Reimplemented from Prolog: matches user's skills to job requirements.
 * Jobs: software_engineer, data_scientist, web_developer, network_engineer
 */
public class SkillsetMatching {

    // Job requirements (mirrors Prolog requires/2 facts)
    static final Map<String, List<String>> JOB_REQUIREMENTS = new LinkedHashMap<>();

    static {
        JOB_REQUIREMENTS.put("Software Engineer",  Arrays.asList("programming", "algorithms", "data structures"));
        JOB_REQUIREMENTS.put("Data Scientist",      Arrays.asList("statistics", "programming", "machine learning"));
        JOB_REQUIREMENTS.put("Web Developer",       Arrays.asList("html", "css", "javascript"));
        JOB_REQUIREMENTS.put("Network Engineer",    Arrays.asList("networking", "security", "protocols"));
    }

    static Scanner sc = new Scanner(System.in);
    static Map<String, Boolean> userSkills = new HashMap<>();

    /** Ask user if they have a skill; cache answer */
    static boolean hasSkill(String skill) {
        if (userSkills.containsKey(skill)) return userSkills.get(skill);

        System.out.print("Do you have skill: " + skill + "? (yes/no): ");
        String response = sc.nextLine().trim().toLowerCase();
        boolean has = response.equals("yes");
        userSkills.put(skill, has);
        return has;
    }

    /** Check if user meets all requirements for a job */
    static boolean matchesJob(String job) {
        for (String skill : JOB_REQUIREMENTS.get(job)) {
            if (!hasSkill(skill)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== Skillset Job Matching Expert System ===");
        System.out.println("Answer yes/no for each skill question.\n");

        for (String job : JOB_REQUIREMENTS.keySet()) {
            System.out.println("\nChecking for: " + job);
            if (matchesJob(job)) {
                System.out.println("Recommended Job: " + job);
                sc.close();
                return;
            }
        }

        System.out.println("\nNo suitable job found based on your current skills.");
        System.out.println("Consider acquiring more skills!");
        sc.close();
    }
}
