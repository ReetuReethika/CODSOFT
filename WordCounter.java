import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = getInputFromUser(scanner);

        // Split the input into words using space and punctuation as delimiters
        String[] words = input.split("[\\s.,?!:;\"]+");

        // Count the words
        int totalWords = words.length;

        // Create a map to store the frequency of each word
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            // Convert the word to lowercase to ignore case sensitivity
            word = word.toLowerCase();

            // Ignore common words or stop words
            if (!isStopWord(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Display the total count of words
        System.out.println("Total words: " + totalWords);

        // Display the number of unique words
        System.out.println("Number of unique words: " + wordFrequency.size());

        // Display the frequency of each word
        System.out.println("Word frequency:");
        for (String word : wordFrequency.keySet()) {
            System.out.println(word + ": " + wordFrequency.get(word));
        }

        scanner.close();
    }

    private static String getInputFromUser(Scanner scanner) {
        System.out.println("Enter text or provide a file path to count words:");
        String input = scanner.nextLine();

        // Check if the user provided a file path
        if (input.trim().startsWith("file:")) {
            String filePath = input.trim().substring(5);
            try {
                input = readFromFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                input = "";
            }
        }
        return input;
    }

    private static String readFromFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static boolean isStopWord(String word) {
        // You can add more common words to this list if needed
        String[] stopWords = {"a", "an", "the", "is", "and", "in", "on", "of", "for", "with", "to"};
        return Pattern.compile("\\b" + word + "\\b").matcher(String.join(" ", stopWords)).find();
    }
}
