package pij.main;

public class Constants {
    // Board size constants
    public static final int BOARD_SIZE = 15; // Standard Scrabble board size is 15x15

    // Tile scores
    public static final int[] TILE_SCORES = {
            // Scores for each letter in the alphabet (A-Z)
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
    };

    // Bonus multipliers for letter and word premiums
    public static final int LETTER_PREMIUM = 2; // Letter premium score multiplier
    public static final int WORD_PREMIUM = 3; // Word premium score multiplier

    // Number of tiles in the tile bag for each letter
    public static final int[] TILE_COUNTS = {
            // Frequency of each letter in the Scrabble tile set
            9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
    };

    // Number of tiles in the tile rack for each player
    public static final int TILE_RACK_SIZE = 7; // Standard Scrabble tile rack size for each player

    // File path for the default board configuration
    public static final String DEFAULT_BOARD_FILE_PATH = "resources/defaultBoard.txt";

    // File path for the default word list
    public static final String DEFAULT_WORD_LIST_FILE_PATH = "resources/defaultWordList.txt";

    // Special characters
    public static final char BLANK_TILE_SYMBOL = '_'; // Symbol representing a blank (wildcard) tile
    public static final char PASS_MOVE_SYMBOL = ','; // Symbol representing a pass move

    // Error messages
    public static final String ILLEGAL_MOVE_FORMAT_MESSAGE = "Illegal move format.";
    public static final String INVALID_FILE_MESSAGE = "This is not a valid file.";
}
