package pij.main;

public class HumanAction {
    private Tile movedTile;
    private int row;
    private int col;

    public HumanAction(Tile movedTile, int row, int col) {
        this.movedTile = movedTile;
        this.row = row;
        this.col = col;
    }

    public Tile getMovedTile() {
        return movedTile;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "HumanAction [movedTile=" + movedTile + ", row=" + row + ", col=" + col + "]";
    }
}
