package edu.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeFileReader {
    public static Maze readMazeFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            Maze maze = new Maze(rows, cols);

            for (int row = 0; row < rows; row++) {
                String[] cellTypes = reader.readLine().split(" ");
                for (int col = 0; col < cols; col++) {
                    MazeObjectType type = MazeObjectType.valueOf(cellTypes[col]);
                    maze.setCell(row, col, type);
                }
            }

            return maze;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

