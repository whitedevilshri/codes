import java.util.*;

/**
 * Sentiment Analysis System
 * Reimplemented from Prolog: classifies input sentence as positive, negative, or neutral
 * by counting positive and negative keywords.
 */
public class SentimentAnalysis {

    // Positive and negative word lists (mirrors Prolog facts)
    static final Set<String> POSITIVE_WORDS = new HashSet<>(Arrays.asList(
        "happy", "good", "great", "excellent", "love"
    ));
    static final Set<String> NEGATIVE_WORDS = new HashSet<>(Arrays.asList(
        "sad", "bad", "terrible", "hate", "worst"
    ));

    /** Count positive words in the sentence */
    static int countPositive(String[] words) {
        int count = 0;
        for (String w : words) {
            if (POSITIVE_WORDS.contains(w.toLowerCase())) count++;
        }
        return count;
    }

    /** Count negative words in the sentence */
    static int countNegative(String[] words) {
        int count = 0;
        for (String w : words) {
            if (NEGATIVE_WORDS.contains(w.toLowerCase())) count++;
        }
        return count;
    }

    /** Determine sentiment: positive, negative, or neutral */
    static String sentiment(String[] words) {
        int pos = countPositive(words);
        int neg = countNegative(words);

        if (pos > neg) return "Positive";
        if (neg > pos) return "Negative";
        return "Neutral";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sentiment Analysis System ===");
        System.out.println("Known positive words: " + POSITIVE_WORDS);
        System.out.println("Known negative words: " + NEGATIVE_WORDS);
        System.out.println("\nEnter a sentence (or 'quit' to exit):");

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) break;
            if (line.isEmpty()) continue;

            String[] words = line.split("\\s+");
            int pos = countPositive(words);
            int neg = countNegative(words);
            String result = sentiment(words);

            System.out.println("  Positive words: " + pos);
            System.out.println("  Negative words: " + neg);
            System.out.println("  Sentiment: " + result);
            System.out.println("\nEnter another sentence (or 'quit'):");
        }
        sc.close();
    }
}
