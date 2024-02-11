package pij.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileBag {
    private List<Tile> tiles;

    public TileBag() {
        this.tiles = new ArrayList<>();
        fillTileBag();
    }

    private void fillTileBag() {
        // Define the Scrabble letter distribution
        // The distribution is represented as an array of tuples, each containing the
        // letter and its frequency
        char[] letters = { 'E', 'A', 'I', 'O', 'N', 'R', 'T', 'L', 'S', 'U', 'D', 'G', 'B', 'C', 'M', 'P', 'F', 'H',
                'V', 'W', 'Y', 'K', 'J', 'X', 'Q', 'Z' };
        int[] frequencies = { 12, 9, 9, 8, 6, 6, 6, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1 };

        // Add tiles to the bag according to the distribution
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < frequencies[i]; j++) {
                tiles.add(new Tile(letters[i]));
            }
        }

        // Shuffle the tiles in the bag
        Collections.shuffle(tiles);
    }

    public Tile drawTile() {
        if (!tiles.isEmpty()) {
            return tiles.remove(0); // Remove and return the first tile from the bag
        }
        return null; // No more tiles in the bag
    }

    public static Object getInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
    }

    public static Object getInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
    }
}
