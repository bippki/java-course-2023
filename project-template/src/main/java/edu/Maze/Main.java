package edu.Maze;

import java.util.List;

/*
public class Main {
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(81, 81);
        generator.generateMaze();
        MazePrinter.printMaze(generator);

        String fileName = "maze.txt";
        MazeFileWriter.writeMazeToFile(generator, fileName);

        MazeGenerator loadedMaze = MazeFileReader.readMazeFromFile(fileName);
        if (loadedMaze != null) {
            System.out.println("Loaded Maze:");
            MazePrinter.printMaze(loadedMaze);
        }
    }
}
*/
import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(81, 81);
        generator.generateMaze();
        MazePrinter.printMaze(generator);


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the start cell (e.g., A1): ");
        String startAddress = scanner.next();
        System.out.print("Enter the end cell (e.g., J10): ");
        String endAddress = scanner.next();

        Cell startCell = parseCellAddress(startAddress, generator);
        Cell endCell = parseCellAddress(endAddress, generator);

        if (startCell != null && endCell != null) {
            List<Cell> path = PathFinder.findPath(generator,startCell, endCell);

            if (path != null) {
                System.out.println("Path found:");
                for (Cell cell : path) {
                    System.out.print(cell.getAddress() + " -> ");
                }
                System.out.println("End");
            } else {
                System.out.println("No path found.");
            }
        } else {
            System.out.println("Invalid cell addresses.");
        }
    }

    private static Cell parseCellAddress(String address, MazeGenerator maze) {
        if (address.length() < 2) {
            return null;
        }

        char file = address.charAt(0);
        int rank;
        try {
            rank = Integer.parseInt(address.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }

        int x = file - 'A';
        int y = rank - 1;

        if (x >= 0 && x < maze.getWidth() && y >= 0 && y < maze.getHeight()) {
            return maze.getCell(x, y);
        }

        return null;
    }
}




