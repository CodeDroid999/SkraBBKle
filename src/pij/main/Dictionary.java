package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private Set<String> wordSet;

    public Dictionary() {
        wordSet = new HashSet<>();
        // Load words from file
        loadWordsFromFile("resources/defaultWordList.txt");
    }

    // Method to load words from a file
    private void loadWordsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordSet.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error loading word list from file: " + e.getMessage());
        }
    }

    // Method to check if a word is valid
    public boolean isValidWord(String word) {
        return wordSet.contains(word.toLowerCase());
    }
}
