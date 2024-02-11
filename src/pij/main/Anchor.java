package pij.main;

public class Anchor {
    private int row;
    private int col;
    private Tile anchorTile;
    private int prefixCap;
    private int postfixCap;
    private boolean across;

    public Anchor(int row, int col, Tile anchorTile, int prefixCap, int postfixCap, boolean across) {
        this.row = row;
        this.col = col;
        this.anchorTile = anchorTile;
        this.prefixCap = prefixCap;
        this.postfixCap = postfixCap;
        this.across = across;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Tile getAnchorTile() {
        return anchorTile;
    }

    public int getPrefixCap() {
        return prefixCap;
    }

    public int getPostfixCap() {
        return postfixCap;
    }

    public boolean isAcross() {
        return across;
    }

    @Override
    public String toString() {
        return "Anchor [" + row + "," + col + "] " + anchorTile.getLetter() + ", pre: " + prefixCap
                + ", post: " + postfixCap + ", across=" + across;
    }
}
