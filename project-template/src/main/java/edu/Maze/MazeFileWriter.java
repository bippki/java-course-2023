package edu.Maze;

import java.io.*;
import java.util.*;

class MazeFileWriter {
    public static void writeMazeToFile(MazeGenerator maze, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            int width = maze.getWidth();
            int height = maze.getHeight();

            writer.println(width + " " + height);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Cell cell = maze.getCell(x, y);
                    String cellSymbol = cell.isVisited() ? " " : "#";
                    writer.print(cellSymbol);
                }
                writer.println();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
