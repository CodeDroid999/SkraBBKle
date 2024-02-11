package pij.main;

public class PlayedWord {
    private final String word;
    private final int row;
    private final int column;
    private final Direction direction;

    public PlayedWord(String word, int row, int column, Direction direction) {
        this.word = word;
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Word: " + word + ", Row: " + row + ", Column: " + column + ", Direction: " + direction;
    }
}
