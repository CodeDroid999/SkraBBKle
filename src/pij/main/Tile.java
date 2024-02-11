package pij.main;

public class Tile {
    private char letter;
    private int letterMultiplier;
    private int wordMultiplier;

    // Constructor
    public Tile(char letter, int letterMultiplier, int wordMultiplier) {
        this.letter = letter;
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
    }

    // Getters and setters
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    public void setLetterMultiplier(int letterMultiplier) {
        this.letterMultiplier = letterMultiplier;
    }

    public int getWordMultiplier() {
        return wordMultiplier;
    }

    public void setWordMultiplier(int wordMultiplier) {
        this.wordMultiplier = wordMultiplier;
    }

    public void setNormal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNormal'");
    }
}
