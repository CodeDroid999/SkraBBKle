package pij.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to SkraBBKle!");

        // Prompt user to choose to load a board or use the default board
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.print("Please enter your choice (l/d): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        String boardFileName;

        // Validate user's choice
        while (!choice.equals("l") && !choice.equals("d")) {
            System.out.println("Invalid choice. Please enter 'l' to load a board or 'd' to use the default board.");
            System.out.print("Please enter your choice (l/d): ");
            choice = scanner.nextLine().trim().toLowerCase();
        }

        // Load or use default board based on user's choice
        if (choice.equals("l")) {
            System.out.print("Please enter the file name of the board: ");
            boardFileName = scanner.nextLine().trim();
            while (!isValidBoardFile(boardFileName)) {
                System.out.println("This is not a valid file. Please enter the file name of the board:");
                boardFileName = scanner.nextLine().trim();
            }
        } else {
            boardFileName = "resources/defaultBoard.txt"; // Default board file
        }

        // Prompt user to choose between open or closed game
        System.out.println("Would you like to play an _o_pen or a _c_losed game?");
        System.out.print("Please enter your choice (o/c): ");
        String gameType = scanner.nextLine().trim().toLowerCase();

        // Validate user's choice
        while (!gameType.equals("o") && !gameType.equals("c")) {
            System.out.println("Invalid choice. Please enter 'o' for open game or 'c' for closed game.");
            System.out.print("Please enter your choice (o/c): ");
            gameType = scanner.nextLine().trim().toLowerCase();
        }

        // Start the game based on user's choices
        startGame(boardFileName, gameType.equals("o"));
    }

    // Method to check if the board file is valid
    private static boolean isValidBoardFile(String fileName) {
        // Add validation logic here
        return true; // For demonstration purpose
    }

    // Method to start the game
    private static void startGame(String boardFileName, boolean isOpenGame) {
        // Implement game initialization logic here
    }
}
