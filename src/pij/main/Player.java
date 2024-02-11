package pij.main;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int score;
    private List<Tile> tileRack;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.tileRack = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<Tile> getTileRack() {
        return tileRack;
    }

    public void addScore(int points) {
        score += points;
    }

    public void refillTileRack(TileBag tileBag) {
        int tilesNeeded = Math.max(0, 7 - tileRack.size());
        for (int i = 0; i < tilesNeeded; i++) {
            Tile tile = tileBag.drawTile();
            if (tile != null) {
                tileRack.add(tile);
            } else {
                // Tile bag is empty
                break;
            }
        }
    }

    // Other methods such as playMove, exchangeTiles, etc. can be added here
}
