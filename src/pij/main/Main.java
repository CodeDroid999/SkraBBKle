package pij.main;

import java.io.File;
import java.io.FileNotFoundException;
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

    // Method to start the game
    private static void startGame(String boardFileName, boolean isOpenGame) {
        // Read and process the board configuration from the file
        try {
            Scanner fileScanner = new Scanner(new File(boardFileName));
            int boardSize = Integer.parseInt(fileScanner.nextLine().trim());
            String[] board = new String[boardSize];
            for (int i = 0; i < boardSize; i++) {
                board[i] = fileScanner.nextLine().trim();
            }
            // Initialize the game with the obtained board configuration
            // Implement this part based on your game logic
            System.out.println("Game initialized with board configuration from " + boardFileName);
            System.out.println("Board size: " + boardSize);
            System.out.println("Board:");
            for (String row : board) {
                System.out.println(row);
            }
            // Close the file scanner
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
