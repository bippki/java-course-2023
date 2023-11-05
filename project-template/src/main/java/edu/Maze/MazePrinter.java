package edu.Maze;

import java.util.ArrayList;
import java.util.List;

class MazePrinter {
    public static void printMaze(MazeGenerator maze) {
        printMaze(maze, null);
    }

    public static void printMaze(MazeGenerator maze, List<Cell> path) {
        int width = maze.getWidth();
        int height = maze.getHeight();
        List<Cell> emptyCells = new ArrayList<>();

        for (int x = 0; x < width + 1; x++) {
            System.out.print("##");
        }
        System.out.println("");
        for (int y = 0; y < height; y++) {
            System.out.print("#");
            for (int x = 0; x < width; x++) {
                Cell cell = maze.getCell(x, y);
                String cellSymbol;
                if (path != null && path.contains(cell)) {
                    cellSymbol = "**";
                } else {
                    cellSymbol = cell.isVisited() ? "  " : "##";
                    if (cellSymbol.equals("  ")) {
                        emptyCells.add(cell);
                    }
                }
                System.out.print(cellSymbol);
            }
            System.out.println("#");
        }

        for (int x = 0; x < width + 1; x++) {
            System.out.print("##");
        }
        System.out.println("");

        System.out.print("Empty Cell Addresses (\"  \"): ");
        for (Cell emptyCell : emptyCells) {
            System.out.print(emptyCell.getAddress() + " ");
        }
        System.out.println("");
    }
}

