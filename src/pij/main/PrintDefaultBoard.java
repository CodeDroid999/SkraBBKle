package pij.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PrintDefaultBoard {

    // Method to print the default board
    public static void print(String boardFileName) {
        try (Scanner fileScanner = new Scanner(new File(boardFileName))) {
            int size = Integer.parseInt(fileScanner.nextLine().trim());
            System.out.println("Default board:");
            for (int i = 0; i < size; i++) {
                String line = fileScanner.nextLine().trim();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Default board file not found.");
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Invalid format in default board file.");
        }
    }
}
