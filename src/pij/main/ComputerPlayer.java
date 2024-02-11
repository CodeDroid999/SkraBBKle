// ComputerPlayer.java
package pij.main;

import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        // Initialize the computer player
    }

    public void makeMove(Board board, TileBag tileBag, Dictionary dictionary) {
        List<Tile> rackTiles = getRackTiles(); // Get the tiles in the player's rack
        List<Move> validMoves = findValidMoves(board, rackTiles, dictionary); // Find all valid moves

        if (!validMoves.isEmpty()) {
            // Make a random move from the list of valid moves
            Move selectedMove = validMoves.get((int) (Math.random() * validMoves.size()));
            applyMove(board, selectedMove); // Apply the selected move to the board
            removeTilesFromRack(selectedMove.getTiles()); // Remove the played tiles from the rack
        } else {
            // If no valid moves are found, the computer player passes its turn
            System.out.println("Computer player passes its turn.");
        }
    }

    private List<Move> findValidMoves(Board board, List<Tile> rackTiles, Dictionary dictionary) {
        List<Move> validMoves = new ArrayList<>();

        // Iterate over all possible positions and directions on the board
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                for (Direction direction : Direction.values()) {
                    // Check if it is possible to play n tiles at the current position and direction
                    for (int n = 1; n <= rackTiles.size(); n++) {
                        List<Tile> tilesToPlay = rackTiles.subList(0, n); // Get n tiles from the rack

                        // Check if playing these tiles at the current position and direction forms a
                        // valid move
                        if (isValidMove(board, tilesToPlay, row, col, direction, dictionary)) {
                            // If valid, create a move object and add it to the list of valid moves
                            Move move = new Move(tilesToPlay, row, col, direction);
                            validMoves.add(move);
                        }
                    }
                }
            }
        }

        return validMoves;
    }

    private boolean isValidMove(Board board, List<Tile> tilesToPlay, int row, int col, Direction direction,
            Dictionary dictionary) {
        // Implement logic to check if playing the given tiles at the specified position
        // and direction forms a valid move
        // This would involve checking if the move fits on the board, forms valid words,
        // etc.
        return true; // Placeholder
    }
}
