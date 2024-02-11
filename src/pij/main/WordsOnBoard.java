package pij.main;

import java.util.ArrayList;
import java.util.List;

public class WordsOnBoard {
    private static final List<PlayedWord> wordList = new ArrayList<>();

    public static void addWord(PlayedWord word) {
        wordList.add(word);
    }

    public static List<PlayedWord> getWordList() {
        return new ArrayList<>(wordList);
    }

    public static void clearWordList() {
        wordList.clear();
    }
}
