package pij.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Dictionary {
    static WordTrie wordTrie;
    static HashMap<Character, Integer> pointsPerLetter;

    public Dictionary() {
        wordTrie = new WordTrie();
        ReadWordList("/defaultWordlist.txt", wordTrie);
        setupLetterScores();
    }

    void ReadWordList(String fileName, WordTrie trie) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)));
            String word;
            while ((word = br.readLine()) != null) {
                trie.insertWord(word.toUpperCase());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getWordScore(String word) {
        int score = 0;
        for (char c : word.toCharArray()) {
            score += pointsPerLetter.get(c);
        }
        return score;
    }

    void setupLetterScores() {
        pointsPerLetter = new HashMap<>();
        pointsPerLetter.put('A', 1);
        pointsPerLetter.put('B', 3);
        pointsPerLetter.put('C', 3);
        pointsPerLetter.put('D', 2);
        pointsPerLetter.put('E', 1);
        pointsPerLetter.put('F', 4);
        pointsPerLetter.put('G', 2);
        pointsPerLetter.put('H', 4);
        pointsPerLetter.put('I', 1);
        pointsPerLetter.put('J', 8);
        pointsPerLetter.put('K', 5);
        pointsPerLetter.put('L', 1);
        pointsPerLetter.put('M', 3);
        pointsPerLetter.put('N', 1);
        pointsPerLetter.put('O', 1);
        pointsPerLetter.put('P', 3);
        pointsPerLetter.put('Q', 10);
        pointsPerLetter.put('R', 1);
        pointsPerLetter.put('S', 1);
        pointsPerLetter.put('T', 1);
        pointsPerLetter.put('U', 1);
        pointsPerLetter.put('V', 4);
        pointsPerLetter.put('W', 4);
        pointsPerLetter.put('X', 8);
        pointsPerLetter.put('Y', 4);
        pointsPerLetter.put('Z', 10);
    }

    // Prefix validation logic
    public static boolean isValidPrefix(String prefix) {
        return wordTrie.searchPrefix(prefix.toUpperCase());
    }
}
