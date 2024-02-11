package pij.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initiation
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.print("Please enter your choice (l/d): ");
        String boardChoice = scanner.nextLine();
        SkraBBKleGame game;
        if (boardChoice.equals("d")) {
            game = new SkraBBKleGame("resources/defaultBoard.txt");
        } else if (boardChoice.equals("l")) {
            System.out.print("Please enter the file name of the board: ");
            String fileName = scanner.nextLine();
            game = new SkraBBKleGame(fileName);
        } else {
            System.out.println("Invalid choice. Please enter 'l' to load a board or 'd' for default.");
            return;
        }

        System.out.println("Would you like to play an _o_pen or a _c_losed game?");
        System.out.print("Please enter your choice (o/c): ");
        String gameType = scanner.nextLine();
        if (gameType.equals("o")) {
            game.setOpenGame(true);
        } else if (gameType.equals("c")) {
            game.setOpenGame(false);
        } else {
            System.out.println("Invalid choice. Please enter 'o' for open game or 'c' for closed game.");
            return;
        }

        // Play moves
        while (!game.isGameOver()) {
            game.printBoard();
            game.printScore();

            if (game.isHumanTurn()) {
                System.out.println("Human's turn. Enter your move in the format 'word,square':");
                String move = scanner.nextLine();
                if (!game.makeMove(move)) {
                    System.out.println("Illegal move format. Please try again.");
                }
            } else {
                System.out.println("Computer's turn:");
                game.makeComputerMove();
            }
        }

        // Game over
        System.out.println("Game Over!");
        game.printFinalScores();
    }
}
