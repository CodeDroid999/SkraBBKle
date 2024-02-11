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
            while (!BoardFileValidator.isValidBoardFile(boardFileName)) {
                System.out.println("This is not a valid file. Please enter the file name of the board:");
                boardFileName = scanner.nextLine().trim();
            }
        } else {
            boardFileName = "resources/defaultBoard.txt"; // Default board file
            PrintDefaultBoard.print(boardFileName); // Print the default board
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
        startGame(boardFileName, gameType.equals("o"), scanner);
    }

    // Method to start the game
    private static void startGame(String boardFileName, boolean isOpenGame, Scanner scanner) {
        // Implement game initialization logic here

        // Example of getting human player's move
        System.out.println("Please enter your move in the format: \"word,square\"");
        System.out.println("For example, \"HI,f4\" for a downward move or \"HI,4f\" for a rightward move.");
        System.out.println("Enter \",\" to pass your turn.");
        System.out.print("Your move: ");
        String moveInput = scanner.nextLine().trim();

        // Validate move format
        while (!HumanMove.isValidMoveFormat(moveInput)) {
            System.out.println("Illegal move format. Please enter your move in the correct format.");
            System.out.println("Please enter your move in the format: \"word,square\"");
            System.out.println("For example, \"HI,f4\" for a downward move or \"HI,4f\" for a rightward move.");
            System.out.println("Enter \",\" to pass your turn.");
            System.out.print("Your move: ");
            moveInput = scanner.nextLine().trim();
        }

        // Continue with the game...
    }
}
