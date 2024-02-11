
package pij.main;

public class Board {

    private Tile[][] tileArr;

    public Board() {
        tileArr = new Tile[BOARD_DIMENSIONS][BOARD_DIMENSIONS];

        for (int row = 0; row < tileArr.length; row++) {
            for (int col = 0; col < tileArr[0].length; col++) {
                tileArr[row][col] = new Tile(' ', 0);
            }
        }
    }

    public void print() {
        for (int row = 0; row < tileArr.length; row++) {
            StringBuilder rowStringBuilder = new StringBuilder();
            for (int col = 0; col < tileArr[0].length; col++) {
                rowStringBuilder.append(tileArr[row][col].getLetter()).append(" ");
            }
            System.out.println(rowStringBuilder.toString());
        }
    }

    public void applyMove(HumanMove move) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMove'");
    }

    public static Object getInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
    }

    // Additional methods as needed
}
