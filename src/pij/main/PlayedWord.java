package pij.main;

public class PlayedWord {
    private String word;
    private int score;

    public PlayedWord(String word, int score) {
        this.word = word;
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayedWord [word=" + word + ", score=" + score + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PlayedWord other = (PlayedWord) obj;
        return word.equals(other.word);
    }
}
