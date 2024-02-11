package pij.main;

public class HumanWord {
    private int score;
    private String word;

    public HumanWord(int score, String word) {
        this.score = score;
        this.word = word;
    }

    public int getScore() {
        return score;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "HumanWord{" +
                "score=" + score +
                ", word='" + word + '\'' +
                '}';
    }
}
