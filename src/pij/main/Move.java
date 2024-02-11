package pij.main;

public class Move {
    private String word;
    private Position position;
    private Direction direction;

    public Move(String word, Position position, Direction direction) {
        this.word = word;
        this.position = position;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
}
