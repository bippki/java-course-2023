package edu.Maze.Input;

import java.util.Scanner;

public class MazeInputHandler {
    public static int[] getMazeDimensions() {
        Scanner scanner = new Scanner(System.in);

        int rows;
        int cols;

        while (true) {
            System.out.print("Enter the number of maze rows: ");
            if (scanner.hasNextInt()) {
                rows = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid input. Enter a valid integer value for rows");
                    scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println("Invalid input. Enter a valid integer value for rows.");
                scanner.nextLine();
                continue;
            }

            System.out.print("Enter the number of maze columns: ");
            if (scanner.hasNextInt()) {
                cols = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid input. Enter a valid integer value for columns.");
                    scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println("Invalid input. Enter a valid integer value for columns.");
                scanner.nextLine();
                continue;
            }

            if (isValidSize(rows, cols)) {
                return new int[]{rows, cols};
            } else {
                System.out.println("Invalid input. Rows and columns must be greater than 5 and odd.");
            }
        }
    }

    private static boolean isValidSize(int rows, int cols) {
        return rows > 5 && cols > 5 && rows % 2 != 0 && cols % 2 != 0;
    }
}
