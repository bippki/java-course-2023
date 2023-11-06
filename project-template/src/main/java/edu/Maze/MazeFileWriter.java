package edu.Maze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MazeFileWriter {
    public static void writeMazeToFile(Maze maze, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            int rows = maze.getRows();
            int cols = maze.getCols();

            writer.write(rows + " " + cols);
            writer.newLine();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    MazeObjectType type = maze.getCell(row, col).getType();
                    writer.write(type + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
