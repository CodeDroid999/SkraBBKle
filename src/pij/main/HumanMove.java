package pij.main;

public class HumanMove {
    private String word;
    private String square;

    public HumanMove(String word, String square) {
        this.word = word;
        this.square = square;
    }

    public String getWord() {
        return word;
    }

    public String getSquare() {
        return square;
    }

    // Method to parse a move input string and create a HumanMove object
    public static HumanMove parseMove(String moveInput) {
        String[] parts = moveInput.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid move format");
        }
        String word = parts[0].trim();
        String square = parts[1].trim();
        return new HumanMove(word, square);
    }

    // Method to validate the format of a move input string
    public static boolean isValidMoveFormat(String moveInput) {
        String[] parts = moveInput.split(",");
        if (parts.length != 2) {
            return false;
        }
        String word = parts[0].trim();
        String square = parts[1].trim();
        return isValidWordFormat(word) && isValidSquareFormat(square);
    }

    // Method to validate the format of a word
    private static boolean isValidWordFormat(String word) {
        // Check if the word contains only letters or wildcards
        return word.matches("[A-Z]+[a-z]*");
    }

    // Method to validate the format of a square
    private static boolean isValidSquareFormat(String square) {
        // Check if the square has the format letter-digit or digit-letter
        return square.matches("[a-zA-Z]\\d+") || square.matches("\\d+[a-zA-Z]");
    }
}
