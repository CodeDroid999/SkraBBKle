package pij.main;

import java.util.ArrayList;
import java.util.Collections;

public class LetterRack implements Constants {
    private ArrayList<Tile> tiles;
    private Player owner;

    public LetterRack(Player owner) {
        this.owner = owner;
        tiles = new ArrayList<Tile>();
        refill();
    }

    public void readTiles() {
        for (Tile tile : tiles) {
            if (tile != null) {
                System.out.println(tile.toString());
            }
        }
    }

    public void refill() {
        while (tiles.size() < TILES_IN_RACK) {
            Tile newTile = TileBag.getInstance().takeOutTile();
            if (newTile == null) {
                return;
            }
            tiles.add(newTile);
        }
    }

    public void swapTiles() {
        for (Tile tile : tiles) {
            if (tile != null) {
                TileBag.getInstance().addToTileSet(tile);
            }
        }
        tiles.clear();
        refill();
    }

 
    }
}
