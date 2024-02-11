package pij.main;

public class HumanAction {

    // Method for making a move
    public static void makeMove(Board board, String moveInput) {
        if (isValidMoveFormat(moveInput)) {
            HumanMove move = HumanMove.parseMove(moveInput);
            // Apply the move to the board
            board.applyMove(move);
        } else {
            System.out.println("Illegal move format. Please enter your move in the correct format.");
        }
    }

    // Method for passing a turn
    public static void passTurn() {
        System.out.println("Turn passed.");
    }

    // Method to validate the format of a move input string
    private static boolean isValidMoveFormat(String moveInput) {
        return HumanMove.isValidMoveFormat(moveInput);
    }
}
