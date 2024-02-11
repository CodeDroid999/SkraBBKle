package pij.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HumanMove {
    private static ArrayList<HumanAction> actionList;

    static {
        actionList = new ArrayList<>();
    }

    public static ArrayList<HumanAction> getInstance() {
        return actionList;
    }

    public static boolean isValid() {
        return hasMovedTiles() && (isRowOrCol()) && !hasGaps() && isJoinedUp() && isProperWord();
    }

    private static boolean isJoinedUp() {
        ArrayList<PlayedWord> newWords = WordsOnBoard.getNewWords();
        int letterCount = 0;
        for (PlayedWord word : newWords)
            letterCount += word.word.length();

        return letterCount > actionList.size();
    }

    private static boolean isProperWord() {
        if (Scrabble.enforeDictionary.isSelected()) {
            ArrayList<PlayedWord> newWords = WordsOnBoard.getNewWords();
            for (PlayedWord word : newWords) {
                if (!Dictionary.isValidWord(word.word)) {
                    return promptForConfirmation("The word '" + word.word
                            + "' does not appear in the dictionary. \nWould you like to play it anyway?");
                }
            }
        }
        return true;
    }

    private static boolean promptForConfirmation(String message) {
        System.out.println(message);
        System.out.print("Enter 'yes' to confirm, or 'no' to cancel: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim().toLowerCase();
        return choice.equals("yes");
    }

    private static boolean hasMovedTiles() {
        return !actionList.isEmpty();
    }

    private static boolean isRowOrCol() {
        return isRow() || isCol();
    }

    private static boolean isRow() {
        int moveRow = actionList.get(0).getRow();
        for (HumanAction a : actionList) {
            if (moveRow != a.getRow()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCol() {
        int moveCol = actionList.get(0).getCol();
        for (HumanAction a : actionList) {
            if (moveCol != a.getCol()) {
                return false;
            }
        }
        return true;
    }

    private static void sortActions() {
        if (isRow()) {
            Collections.sort(actionList, Comparator.comparing(HumanAction::getCol));
        } else if (isCol()) {
            Collections.sort(actionList, Comparator.comparing(HumanAction::getRow));
        }
    }

    private static boolean hasGaps() {
        if (actionList.size() <= 1)
            return false;
        sortActions();

        int row = actionList.get(0).getRow();
        int col = actionList.get(0).getCol();

        int endRow = actionList.get(actionList.size() - 1).getRow();
        int endCol = actionList.get(actionList.size() - 1).getCol();

        if (isRow()) {
            for (int c = col; c <= endCol; c++) {
                if (Board.getInstance().getTile(row, c).getLetter() == ' ') {
                    return true;
                }
            }
        } else if (isCol()) {
            for (int r = row; r <= endRow; r++) {
                if (Board.getInstance().getTile(r, col).getLetter() == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    public static void execute(Player player) {
        ArrayList<PlayedWord> newWords = WordsOnBoard.getNewWords();

        for (PlayedWord word : newWords) {
            if (!Dictionary.isValidWord(word.word)) {
                Scrabble.log.append("??? '" + word.word + "' ??? !\n");
            }

            int score = word.score;
            Scrabble.log.append(player.name + " plays the word " + word.word + " for " + score + " points\n");
            player.awardPoints(score);
        }

        if (player.letterRack.tiles.size() == 0) {
            Scrabble.log.append("***" + player.name + " scores a BINGO for 50 points! ***    \n");
            player.awardPoints(50);
        }

        if (!player.name.equalsIgnoreCase("scrabblebot")) {
            for (HumanAction action : actionList) {
                action.getMovedTile().setNormal();
            }
            actionList.clear();
        }
        player.letterRack.refill();
    }

    public static void reverse() {
        for (HumanAction action : actionList) {
            action.getMovedTile().setNormal();
            Scrabble.user.letterRack.tiles.add(action.getMovedTile());
            Board.getInstance().setTile(action.getRow(), action.getCol(), new Tile(' ', 0));
        }
        actionList.clear();
    }
}
