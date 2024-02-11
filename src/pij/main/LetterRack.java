package pij.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterRack {
    private List<Tile> tiles;
    private final int rackSize;

    public LetterRack(int rackSize) {
        this.rackSize = rackSize;
        this.tiles = new ArrayList<>();
    }

    public void refill(List<Tile> newTiles) {
        tiles.clear();
        tiles.addAll(newTiles.subList(0, Math.min(rackSize, newTiles.size())));
    }

    public void addTile(Tile tile) {
        if (tiles.size() < rackSize) {
            tiles.add(tile);
        }
    }

    public void removeTile(Tile tile) {
        tiles.remove(tile);
    }

    public void ShuffleTiles() {
        Collections.shuffle(tiles);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int size() {
        return tiles.size();
    }

    @Override
    public String toString() {
        StringBuilder rackString = new StringBuilder();
        for (Tile tile : tiles) {
            rackString.append(tile.getLetter()).append(" ");
        }
        return rackString.toString().trim();
    }
}


