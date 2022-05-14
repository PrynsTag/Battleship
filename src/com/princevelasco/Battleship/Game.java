package com.princevelasco.Battleship;

import java.util.List;
import java.util.Scanner;

class Game {
    static Scanner scanner = new Scanner(System.in);

    static void start() {
        List<String> inputs = List.of(
                "Enter the coordinates of the Aircraft Carrier (5 cells):",
                "Enter the coordinates of the Battleship (4 cells):",
                "Enter the coordinates of the Submarine (3 cells):",
                "Enter the coordinates of the Cruiser (3 cells):",
                "Enter the coordinates of the Destroyer (2 cells):"
        );

        Grid.createGrid();
        Grid.printGrid();

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(inputs.get(i));

            String[] position = scanner.nextLine().split(" ");

            char[] coordinatesLetter = splitLetterCoordinates(position);
            String[] coordinatesNumber = splitNumberCoordinates(position);

            char posLetter1 = coordinatesLetter[0];
            char posLetter2 = coordinatesLetter[1];

            String posNumber1 = coordinatesNumber[0];
            String posNumber2 = coordinatesNumber[1];

            int[] coords = getCoordinates(posLetter1, posLetter2, posNumber1, posNumber2);
            boolean shipPlaced = Grid.isShipPlaced(Ship.getShip(i), coords[0], coords[1], coords[2], coords[3]);

            while (!shipPlaced) {
                position = scanner.nextLine().split(" ");

                coordinatesLetter = splitLetterCoordinates(position);
                coordinatesNumber = splitNumberCoordinates(position);

                posLetter1 = coordinatesLetter[0];
                posLetter2 = coordinatesLetter[1];

                posNumber1 = coordinatesNumber[0];
                posNumber2 = coordinatesNumber[1];

                coords = getCoordinates(posLetter1, posLetter2, posNumber1, posNumber2);
                shipPlaced = Grid.isShipPlaced(Ship.getShip(i), coords[0], coords[1], coords[2], coords[3]);
            }

            System.out.println();
            Grid.printGrid();
        }
    }

    static void attack() {
        System.out.println("\nThe game starts!\n");
        Grid.printGrid();
        System.out.println("\nTake a shot!\n");

        String coordinates = scanner.nextLine();

        String posLetter = coordinates.substring(0, 1);
        String posNumber = coordinates.substring(1);

        int[] coords = getCoordinates(posLetter, posNumber);
        boolean correctCoordinate = Grid.isCorrectCoordinate(coords[0], coords[1]);

        while (!correctCoordinate) {
            coordinates = scanner.nextLine();

            posLetter = coordinates.substring(0, 1);
            posNumber = coordinates.substring(1);

            coords = getCoordinates(posLetter, posNumber);
            correctCoordinate = Grid.isCorrectCoordinate(coords[0], coords[1]);
        }

        boolean shipHit = Grid.isShipHit(coords[0], coords[1]);
        Grid.printGrid();
        if (shipHit) {
            System.out.println("\nYou hit a ship!\n");
        } else {
            System.out.println("\nYou missed!\n");
        }
    }

    static int[] getCoordinates(int posLetter1, int posLetter2, String posNumber1, String posNumber2) {
        int row1 = posLetter1 - 64;
        int row2 = posLetter2 - 64;

        int col1 = Integer.parseInt(posNumber1) - 1;
        int col2 = Integer.parseInt(posNumber2) - 1;

        if (col1 > col2) {
            int temp = col1;
            col1 = col2;
            col2 = temp;
        }

        if (row1 > row2) {
            int temp = row1;
            row1 = row2;
            row2 = temp;
        }

        return new int[]{row1, row2, col1, col2};
    }

    static int[] getCoordinates(String posLetter, String posNumber) {
        int row = posLetter.charAt(0) - 64;
        int col = Integer.parseInt(posNumber) - 1;

        return new int[]{row, col};
    }

    static char[] splitLetterCoordinates(String[] coordinates) {
        String x = coordinates[0];
        String y = coordinates[1];

        char posLetter1 = x.charAt(0);
        char posLetter2 = y.charAt(0);

        return new char[]{posLetter1, posLetter2};
    }

    static String[] splitNumberCoordinates(String[] coordinates) {
        String x = coordinates[0];
        String y = coordinates[1];

        String posNumber1 = x.substring(1);
        String posNumber2 = y.substring(1);

        return new String[]{posNumber1, posNumber2};
    }

    static int countCells(int posLetter1, int posLetter2, int posNumber1, int posNumber2) {
        int numCells;

        if (posLetter1 == posLetter2) {
            numCells = Math.abs(posNumber1 - posNumber2) + 1;
        } else {
            int row1 = posLetter1 - 64;
            int row2 = posLetter2 - 64;
            numCells = Math.abs(row1 - row2) + 1;
        }

        return numCells;
    }

    static void end() {
        System.out.println("Game ended");
    }

    static void play() {
        start();
        attack();
        end();
    }
}
