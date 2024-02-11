package pij.main;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Begin SkraBBKle");
        new TileBag();
        beginGame();
    }

    private static void beginGame() {
        new Dictionary();
        AI ai = new AI();
        Player humanPlayer = new Player();
        ArrayList<PlayedWord> wordList = new ArrayList<>();

        // First move by the human player
        boolean validFirstMove = false;
        do {
            // Prompt the user for input and validate the move
            // Example: word = "HELLO", startRow = 7, startCol = 7, across = true
            // (meaning the word is placed horizontally), score = calculateScore(word,
            // startRow, startCol, across)
            String word = "HELLO";
            int startRow = 7;
            int startCol = 7;
            boolean across = true;
            int score = calculateScore(word, startRow, startCol, across);

            // Execute the move if it's valid
            if (score > 0) {
                ((Object) humanPlayer.letterRack).removeTiles(word); // Remove tiles from player's rack
                placeWordOnBoard(word, startRow, startCol, across); // Place the word on the board
                humanPlayer.awardPoints(score); // Award points to the player
                validFirstMove = true;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        } while (!validFirstMove);

        // Populate player's rack with tiles from the bag
        humanPlayer.letterRack.refill();

        // Continue the game until one player has no more tiles on their rack or no more
        // moves are possible
        boolean gameEnd = false;
        while (!gameEnd) {
            // Human player's turn
            // Prompt the user for input and validate the move

            // Execute the move if it's valid

            // Populate player's rack with tiles from the bag

            // Computer player's turn
            // AI logic to make a move

            // Populate computer player's rack with tiles from the bag

            // Check if game end condition is met
            // If yes, set gameEnd flag to true
        }
    }

    private static int calculateScore(String word, int startRow, int startCol, boolean across) {
        // Logic to calculate the score based on the word placed on the board
        return 0; // Placeholder
    }

    private static void placeWordOnBoard(String word, int startRow, int startCol, boolean across) {
        // Logic to place the word on the board
    }
}
