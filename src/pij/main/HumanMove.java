package pij.main;

public class HumanMove {
    private String word;
    private String square;

    public HumanMove(String word, String square) {
        this.word = word;
        this.square = square;
    }

    // Getters for word and square
    public String getWord() {
        return word;
    }

    public String getSquare() {
        return square;
    }

    // Method to validate move format
    public static boolean isValidMoveFormat(String moveInput) {
        // Check if the input string contains a comma
        if (!moveInput.contains(",")) {
            return false;
        }

        // Split the input string into word and square parts
        String[] parts = moveInput.split(",");
        if (parts.length != 2) {
            return false;
        }

        // Check if the word part is non-empty
        String word = parts[0].trim();
        if (word.isEmpty()) {
            return false;
        }

        // Check if the square part consists of a letter and a number
        String square = parts[1].trim();
        if (square.length() != 2 || !Character.isLetter(square.charAt(0)) || !Character.isDigit(square.charAt(1))) {
            return false;
        }

        return true;
    }

    // Method to parse move input and create a HumanMove object
    public static HumanMove parseMove(String moveInput) {
        // Extract word and square from the move input
        String[] parts = moveInput.split(",");
        String word = parts[0].toUpperCase().trim();
        String square = parts[1].toLowerCase().trim();

        return new HumanMove(word, square);
    }
}
