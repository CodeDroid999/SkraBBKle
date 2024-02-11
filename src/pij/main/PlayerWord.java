package pij.main;

public class PlayerWord {
    private String word;
    private int score;

    public PlayerWord(String word, int score) {
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public int getScore() {
        return score;
    }
}
