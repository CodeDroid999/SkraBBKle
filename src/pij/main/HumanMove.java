package pij.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class HumanMove implements Constants {

	private static ArrayList<HumanAction> actionList;
	static {
		actionList = new ArrayList<HumanAction>();
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

		// System.err.println(letterCount + );
		if (letterCount > actionList.size()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Tiles must be joined up with existing tiles");
			return false;
		}
	}

	private static boolean isProperWord() {
		if (Scrabble.enforeDictionary.isSelected()) {
			ArrayList<PlayedWord> newWords = WordsOnBoard.getNewWords();
			for (PlayedWord word : newWords) {
				if (!Dictionary.bigTrie.searchWord(word.word)) {
					if (JOptionPane.showConfirmDialog(null, "The word '" + word.word
							+ "' does not appear in ScrabbleBot's Dictionary. \n would you like to play it anyway?") == JOptionPane.YES_OPTION) {

					} else {
						return false;
					}
					// JOptionPane.showOptionDialog(Board.getInstance(), message, title, optionType,
					// messageType, icon, options, initialValue)//(null, "The word '" + word + "'
					// does not appear in ScrabbleBot's Dictionary. Please play a different word or
					// deselect 'enfore dictionary'");
				}
			}
		}
		return true;
	}

	private static boolean hasMovedTiles() {
		if (actionList.size() > 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Please move some tiles onto the board before pressing 'Play'");
		return false;
	}

	private static boolean isRowOrCol() {
		if (isRow() || isCol()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Tiles must be all be in the same row or column");
			return false;
		}
	}

	private static boolean isRow() {
		int moveRow = actionList.get(0).row;
		for (HumanAction a : actionList) {
			if (moveRow != a.row) {
				return false;
			}
		}
		return true;
	}

	private static boolean isCol() {
		int moveCol = actionList.get(0).col;
		for (HumanAction a : actionList) {
			if (moveCol != a.col) {
				return false;
			}
		}
		return true;
	}

	private static void sortActions() {
		if (isRow()) {
			Collections.sort(actionList, new Comparator<HumanAction>() {
				public int compare(HumanAction a1, HumanAction a2) {
					return a1.col - a2.col;
				}
			});
		} else if (isCol()) {
			Collections.sort(actionList, new Comparator<HumanAction>() {
				public int compare(HumanAction a1, HumanAction a2) {
					return a1.row - a2.row;
				}
			});
		}
	}

	private static boolean hasGaps() {

		if (actionList.size() <= 1)
			return false;
		sortActions();
		if (isRow()) {
			int row = actionList.get(0).row;
			for (int col = actionList.get(0).col; col <= actionList.get(actionList.size() - 1).col; col++) {
				if (Board.getInstance().tileArr[row][col].letter == ' ') {
					JOptionPane.showMessageDialog(null, "Words must not have gaps");
					return true;
				}
			}
		} else if (isCol()) {
			int col = actionList.get(0).col;
			for (int row = actionList.get(0).row; row <= actionList.get(actionList.size() - 1).row; row++) {
				if (Board.getInstance().tileArr[row][col].letter == ' ') {
					JOptionPane.showMessageDialog(null, "Words must not have gaps");
					return true;
				}
			}
		}
		return false;
	}

	public static void execute(Player player) {

		ArrayList<PlayedWord> newWords = WordsOnBoard.getNewWords();

		for (PlayedWord word : newWords) {

			if (!Dictionary.bigTrie.searchWord(word.word)) {
				Scrabble.log.append("??? '" + word.word + "' ??? !\n");
			}

			int score = word.score;// Dictionary.getWordScore(word.);

			Scrabble.log.append(player.name + " plays the word " + word.word + " for " + score + " points\n");

			player.awardPoints(score);
		}
		if (player.letterRack.tiles.size() == 0) {
			Scrabble.log.append("***" + player.name + " scores a BINGO for 50 points! ***    \n");
			player.awardPoints(50);
		}

		if (!player.name.equalsIgnoreCase("scrabblebot")) {
			for (HumanAction action : actionList) {
				action.movedTile.setNormal();
			}
			actionList = new ArrayList<HumanAction>();
		}
		BonusChecker.RemovePlayedBonuses();
		player.letterRack.refill();
	}

	public static void reverse() {

		for (HumanAction action : actionList) {
			action.movedTile.setNormal();
			Scrabble.user.letterRack.tiles.add(action.movedTile);
			Board.getInstance().tileArr[action.row][action.col] = new Tile(' ', 0);
		}
		actionList = new ArrayList<HumanAction>();
	}
}
