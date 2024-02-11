// ComputerPlayer.java
package pij.main;

import java.util.ArrayList;

public class ComputerPlayer {
    private Player bot;
    private int maxScore;
    private ArrayList<Tile> bestWord;
    private Anchor currentAnchor;

    public ComputerPlayer(Player bot) {
        this.bot = bot;
    }

    public boolean makeFirstMove() {
        bestWord = new ArrayList<>();
        getStartingWord(bot.getLetterRack().getTiles(), bestWord, "", 0);

        if (maxScore == 0) {
            System.err.println("AI couldn't move with starting tiles: \n" + bot.getLetterRack().toString());
            bot.getLetterRack().swapTiles();
            return false;
        }

        Move move = new Move(bestWord, 7, 7 - (bestWord.size() / 2), true, maxScore, bot);
        move.execute(Board.getInstance().getTileArr());
        bot.getLetterRack().refill();
        return true;
    }

    public boolean makeSubsequentMove() {
        maxScore = 0;
        bestWord = new ArrayList<>();
        for (Anchor anchor : findAnchors()) {
            ArrayList<Tile> inputTiles = new ArrayList<>(bot.getLetterRack().getTiles());
            inputTiles.add(anchor.getAnchorTile());
            findHighestScoringWord(inputTiles, new ArrayList<>(), "", 0, anchor);
        }
        if (bestWord == null || bestWord.size() == 0) {
            bot.getLetterRack().swapTiles();
            return false;
        } else {
            int startCol;
            int startRow;
            if (currentAnchor.isAcross()) {
                startCol = currentAnchor.getCol() - getAnchorPosition(currentAnchor, bestWord);
                startRow = currentAnchor.getRow();
            } else {
                startCol = currentAnchor.getCol();
                startRow = currentAnchor.getRow() - getAnchorPosition(currentAnchor, bestWord);
            }

            Move move = new Move(bestWord, startRow, startCol, currentAnchor.isAcross(), maxScore, bot);
            move.execute(Board.getInstance().getTileArr());
            Board.getInstance().repaint();
        }
        return true;
    }

    private int getAnchorPosition(Anchor anchor, ArrayList<Tile> word) {
        for (int c = 0; c < word.size(); c++) {
            if (word.get(c).getLetter() == anchor.getAnchorTile().getLetter()) {
                return c;
            }
        }
        return -1000;
    }

    private boolean fitsOnBoard(Anchor anchor, ArrayList<Tile> word) {
        int anchorPos = getAnchorPosition(anchor, word);
        int prefixLength = anchorPos;
        int postfixLength = word.size() - anchorPos - 1;

        return anchor.getPrefixCap() >= prefixLength && anchor.getPostfixCap() >= postfixLength;
    }

    private void findHighestScoringWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord,
            int score, Anchor anchor) {
        for (int tileNo = 0; tileNo < inputTiles.size(); tileNo++) {
            Tile curTile = inputTiles.get(tileNo);
            if (curTile == null)
                break;
            if (isValidPrefix(currentWord + curTile.getLetter())) {
                ArrayList<Tile> remainingTiles = new ArrayList<>(inputTiles);
                ArrayList<Tile> tilesInWord = new ArrayList<>(tilesToBeUsed);
                remainingTiles.remove(tileNo);
                tilesInWord.add(curTile);
                findHighestScoringWord(remainingTiles, tilesInWord, currentWord + curTile.getLetter(),
                        score + curTile.getPoints(), anchor);
                if (currentWord.length() >= 7) {
                    score += 50;
                }
                if (tilesToBeUsed.contains(anchor.getAnchorTile()) || curTile.equals(anchor.getAnchorTile())) {
                    if (isValidWord(currentWord + curTile.getLetter())) {
                        if (fitsOnBoard(anchor, tilesInWord)) {
                            if (maxScore < score + curTile.getPoints()) {
                                maxScore = score + curTile.getPoints();
                                bestWord = tilesInWord;
                                currentAnchor = anchor;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isValidPrefix(String prefix) {
        return Dictionary.getInstance().searchPrefix(prefix);
    }

    private boolean isValidWord(String word) {
        return Dictionary.getInstance().searchWord(word);
    }

    private void getStartingWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord,
            int score) {
        for (int tileNo = 0; tileNo < inputTiles.size(); tileNo++) {
            Tile curTile = inputTiles.get(tileNo);
            if (isValidPrefix(currentWord + curTile.getLetter())) {
                ArrayList<Tile> remainingTiles = new ArrayList<>(inputTiles);
                ArrayList<Tile> tilesInWord = new ArrayList<>(tilesToBeUsed);
                remainingTiles.remove(tileNo);
                tilesInWord.add(curTile);
                getStartingWord(remainingTiles, tilesInWord, currentWord + curTile.getLetter(),
                        score + curTile.getPoints());
                if (currentWord.length() >= 6) {
                    score += 50;
                }
                if (isValidWord(currentWord + curTile.getLetter())) {
                    if (maxScore < score + curTile.getPoints()) {
                        maxScore = score + curTile.getPoints();
                        bestWord = tilesInWord;
                    }
                }
            }
        }
    }

    private ArrayList<Anchor> findAnchors() {
        ArrayList<Anchor> anchors = new ArrayList<>();
        Tile[][] tileArr = Board.getInstance().getTileArr();
        for (int row = 0; row < tileArr.length; row++) {
            for (int col = 0; col < tileArr[0].length; col++) {
                if (tileArr[row][col].getLetter() != ' ') {
                    int startCol = col;
                    int endCol = col;

                    if (col > 0 && tileArr[row][col - 1].getLetter() < 'A') {
                        while (startCol > 0) {
                            if (row != Board.BOARD_DIMENSIONS - 1
                                    && tileArr[row + 1][startCol - 1].getLetter() != ' ') {
                                break;
                            }
                            if (row != 0 && tileArr[row - 1][startCol - 1].getLetter() != ' ') {
                                break;
                            }
                            if (startCol == 1) {
                                startCol--;
                                break;
                            }
                            if (tileArr[row][startCol - 2].getLetter() != ' ') {
                                break;
                            }
                            startCol--;
                        }
                    }

                    if (col < Board.BOARD_DIMENSIONS - 1 && tileArr[row][col + 1].getLetter() == ' ') {
                        while (endCol < Board.BOARD_DIMENSIONS - 1) {
                            if (row != Board.BOARD_DIMENSIONS - 1 && tileArr[row + 1][endCol + 1].getLetter() != ' ') {
                                break;
                            }
                            if (row != 0 && tileArr[row - 1][endCol + 1].getLetter() != ' ') {
                                break;
                            }
                            if (endCol == Board.BOARD_DIMENSIONS - 2) {
                                endCol++;
                                break;
                            }
                            if (tileArr[row][endCol + 2].getLetter() != ' ') {
                                break;
                            }
                            endCol++;
                        }
                    }

                    if (col - startCol > 0 && endCol - col > 0) {
                        anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col, true));
                    } else {
                        if (col - startCol > 0) {
                            if (col < Board.BOARD_DIMENSIONS - 1 && tileArr[row][col + 1].getLetter() == ' ') {
                                anchors.add(
                                        new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col, true));
                            }
                        }
                        if (endCol - col > 0) {
                            if (col > 0 && tileArr[row][col - 1].getLetter() == ' ') {
                                anchors.add(
                                        new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col, true));
                            }
                        }
                    }

                    int startRow = row;
                    int endRow = row;

                    if (row > 0 && tileArr[row - 1][col].getLetter() == ' ') {
                        while (startRow > 0) {
                            if (col < Board.BOARD_DIMENSIONS - 1 && tileArr[startRow - 1][col + 1].getLetter() != ' ') {
                                break;
                            }
                            if (col > 0 && tileArr[startRow - 1][col - 1].getLetter() != ' ') {
                                break;
                            }
                            if (startRow == 1) {
                                startRow--;
                                break;
                            }
                            if (tileArr[startRow - 2][col].getLetter() != ' ') {
                                break;
                            }
                            startRow--;
                        }
                    }

                    if (row < Board.BOARD_DIMENSIONS - 1 && tileArr[row + 1][col].getLetter() == ' ') {
                        while (endRow < Board.BOARD_DIMENSIONS - 1) {
                            if (col < Board.BOARD_DIMENSIONS - 1 && tileArr[endRow + 1][col + 1].getLetter() != ' ') {
                                break;
                            }
                            if (col > 0 && tileArr[endRow + 1][col - 1].getLetter() != ' ') {
                                break;
                            }
                            if (endRow == Board.BOARD_DIMENSIONS - 2) {
                                endRow++;
                                break;
                            }
                            if (tileArr[endRow + 2][col].getLetter() != ' ') {
                                break;
                            }
                            endRow++;
                        }
                    }

                    if (row - startRow > 0 && endRow - row > 0) {
                        anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
                    } else {
                        if (row - startRow > 0) {
                            if (row < Board.BOARD_DIMENSIONS - 1 && tileArr[row + 1][col].getLetter() == ' ') {
                                anchors.add(
                                        new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
                            }
                        }
                        if (endRow - row > 0) {
                            if (row > 0 && tileArr[row - 1][col].getLetter() == ' ') {
                                anchors.add(
                                        new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
                            }
                        }
                    }
                }
            }
        }
        return anchors;
    }
}
