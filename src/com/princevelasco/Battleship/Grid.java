package com.princevelasco.Battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.princevelasco.Battleship.Game.countCells;

public class Grid {
    private static final int MAX_ROWS = 10;
    private static final int MAX_COLUMNS = 10;
    static List<List<String>> grid = new ArrayList<>();

    static void createGrid() {
        for (int row = 0; row <= MAX_ROWS; row++) {
            List<String> temp = new ArrayList<>();

            for (int col = 0; col < MAX_COLUMNS; col++) {
                temp.add("~");
            }
            grid.add(temp);
        }
    }

    static void printGrid() {
        System.out.print(" ");
        IntStream.range(1, 11).forEach(i -> System.out.printf(" %d", i));

        System.out.println();
        for (int row = 1; row <= MAX_ROWS; row++) {
            String letter = Character.toChars(row + 64)[0] + " ";
            System.out.printf("%s%s\n", letter, grid.get(row)
                    .toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
            );
        }

        System.out.println();
    }

    static boolean isShipPlaced(Ship ship, int row1, int row2, int col1, int col2) {
        int numCells = countCells(row1, row2, col1, col2);

        if (col1 < 0 || col1 > MAX_COLUMNS || col2 < 0 || col2 > MAX_COLUMNS) {
            System.out.println("\nError! Ship is out of bounds! Try again:\n");
            return false;
        } else if (numCells != ship.getLength()) {
            System.out.printf("\nError! Wrong length of the %s! Try again:\n", ship.getName());
            return false;
        } else if (isCloseToAnotherShip(row1, row2, col1, col2)) {
            System.out.println("\nError! Ship is close to another ship! Try again:\n");
            return false;
        } else if (!(row1 == row2) && !(col1 == col2)) {
            System.out.println("\nError! You can only place ships horizontally or vertically! Try again:\n");
            return false;
        } else {
            for (int row = row1; row <= row2; row++) {
                for (int col = col1; col <= col2; col++) {
                    grid.get(row).set(col, "O");
                }
            }
            return true;
        }
    }

    private static boolean isCloseToAnotherShip(int row1, int row2, int col1, int col2) {
        try {
            for (int row = row1 + 1; row <= row2 + 1; row++) {
                for (int col = col1 + 1; col <= col2 + 1; col++) {
                    if (grid.get(row).get(col).equals("O")) {
                        return true;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // continue
        }
        return false;
    }

    static boolean isShipHit(int posLetter, int posNumber) {
        if (grid.get(posLetter).get(posNumber).equals("O")) {
            grid.get(posLetter).set(posNumber, "X");
            return true;
        } else if (grid.get(posLetter).get(posNumber).equals("~")) {
            grid.get(posLetter).set(posNumber, "M");
            return false;
        }
        return false;
    }

    static boolean isCorrectCoordinate(int posLetter, int posNumber) {
        if (posLetter < 0 || posLetter >= MAX_ROWS || posNumber < 0 || posNumber >= MAX_COLUMNS) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            return false;
        } else {
            return true;
        }
    }

    static void clearGrid() {
        grid.clear();
    }
}
