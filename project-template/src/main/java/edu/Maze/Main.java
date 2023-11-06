package edu.Maze;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rows;
        int cols;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the number of maze rows: ");
            if (scanner.hasNextInt()) {
                rows = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid input. Enter valid integer value for rows");
                    scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println("Invalid input.Enter valid integer value for rows.");
                scanner.nextLine();
                continue;
            }

            System.out.print("Enter the number of maze columns: ");
            if (scanner.hasNextInt()) {
                cols = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid input. Enter valid integer value for column.");
                    scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println("Invalid input. Enter valid integer value for column.");
                scanner.nextLine();
                continue;
            }

            if (isValidSize(rows, cols)) {
                break;
            } else {
                System.out.println("Invalid input. Rows and columns must be greater than 5 and odd.");
            }
        }

        MazeGenerator mazeGenerator = null;

        while (mazeGenerator == null) {
            System.out.println("Choose maze generation algorithm:");
            System.out.println("1. DFS");
            System.out.println("2. Sidewinder");
            System.out.println("3. Oldos-Broder");
            int choice = scanner.nextInt();

            if (choice == 1) {
                mazeGenerator = new DFSMazeGenerator(rows, cols);
            } else if (choice == 2) {
                mazeGenerator = new SidewinderMazeGenerator(rows, cols);
            } else if (choice == 3) {
                mazeGenerator = new OldMazeGenerator(rows, cols);
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        Maze generatedMaze = mazeGenerator.generateMaze();
        MazePrinter mazePrinter = new MazePrinter();
        mazePrinter.printMaze(generatedMaze);

        boolean isValidInput = false;
        int startRow=0, startCol=0, endRow, endCol;
        while (!isValidInput) {
            System.out.print("Enter the row coordinate for START: ");
            startRow = scanner.nextInt();
            System.out.print("Enter the column coordinate for START: ");
            startCol = scanner.nextInt();

            if (isValidCoordinate(startRow, startCol, rows, cols)) {
                isValidInput = true;
                generatedMaze.setCell(startRow, startCol, MazeObjectType.START);
            } else {
                System.out.println("Invalid input. The coordinates are out of bounds. Try again.");
            }
        }
        mazePrinter.printMaze(generatedMaze);
        isValidInput = false;
        while (!isValidInput) {
            System.out.print("Enter the row coordinate for END: ");
            endRow = scanner.nextInt();
            System.out.print("Enter the column coordinate for END: ");
            endCol = scanner.nextInt();

            if (isValidCoordinate(endRow, endCol, rows, cols)) {
                isValidInput = true;
                generatedMaze.setCell(endRow, endCol, MazeObjectType.END);
            } else {
                System.out.println("Invalid input. The coordinates are out of bounds. Try again.");
            }
        }

        mazePrinter.printMaze(generatedMaze);
        AbstractMazeSolver mazeSolver = new DFSMazeSolver(generatedMaze,startRow,startCol);
        if (mazeSolver.solveMaze()) {
            System.out.println("Maze solved!");
            mazePrinter.printMaze(generatedMaze);
        } else {
            System.out.println("No solution found for the maze.");
        }

    }
    private static boolean isValidSize(int rows, int cols) {
        return rows > 5 && cols > 5 && rows % 2 != 0 && cols % 2 != 0;
    }

    private static boolean isValidCoordinate(int row, int col, int maxRows, int maxCols) {
        return row >= 0 && row < maxRows && col >= 0 && col < maxCols;
    }
}
