package edu.Maze.Input;

import java.util.Scanner;

public class MazeCoordinateInputHandler {
    private final Scanner scanner;

    public MazeCoordinateInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int[] getValidCoordinate(String coordinateType, int maxRows, int maxCols) {
        int[] coordinate = new int[2];
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter the row coordinate for " + coordinateType + ": ");
            if (scanner.hasNextInt()) {
                coordinate[0] = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the row.");
                scanner.next();
                continue;
            }

            System.out.print("Enter the column coordinate for " + coordinateType + ": ");
            if (scanner.hasNextInt()) {
                coordinate[1] = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the column.");
                scanner.next();
                continue;
            }

            if (isValidCoordinate(coordinate[0], coordinate[1], maxRows, maxCols)) {
                isValidInput = true;
            } else {
                System.out.println("Invalid input. The coordinates are out of bounds. Try again.");
            }
        }

        return coordinate;
    }

    private boolean isValidCoordinate(int row, int col, int maxRows, int maxCols) {
        return row >= 0 && row < maxRows && col >= 0 && col < maxCols;
    }
}
