package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardFileValidator {
    public static boolean isValidBoardFile(String fileName) {
        // Check if the file exists
        if (!fileExists(fileName)) {
            return false;
        }

        // Check if the file has a .txt extension
        if (!fileName.endsWith(".txt")) {
            return false;
        }

        // Validate the content format
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the first line to get the board size
            String firstLine = reader.readLine();
            if (firstLine == null) {
                return false; // File is empty
            }
            int boardSize = Integer.parseInt(firstLine);
            if (boardSize < 12 || boardSize > 26) {
                return false; // Board size is not within the allowed range
            }

            // Check each subsequent line for correct format
            Pattern pattern = Pattern.compile("\\.{1}|\\(\\-?\\d{1,2}\\)|\\{\\-?\\d{1,2}\\}");
            for (int i = 0; i < boardSize; i++) {
                String line = reader.readLine();
                if (line == null) {
                    return false; // Not enough rows
                }
                if (line.length() != boardSize) {
                    return false; // Row length doesn't match board size
                }
                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    return false; // Invalid token format
                }
            }

            // Check if there are extra lines
            String extraLine = reader.readLine();
            if (extraLine != null) {
                return false;
            }

            return true; // File passed all checks
        } catch (IOException | NumberFormatException e) {
            return false; // Error occurred while reading or parsing file
        }
    }

    private static boolean fileExists(String fileName) {
        return fileName != null && !fileName.isEmpty();
    }
}
