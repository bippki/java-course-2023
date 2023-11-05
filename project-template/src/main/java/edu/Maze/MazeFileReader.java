package edu.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class MazeFileReader {
    public static MazeGenerator readMazeFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] dimensions = reader.readLine().split(" ");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            MazeGenerator maze = new MazeGenerator(width, height);

            for (int y = 0; y < height; y++) {
                String line = reader.readLine();
                for (int x = 0; x < width; x++) {
                    char cellSymbol = line.charAt(x);
                    if (cellSymbol == ' ') {
                        maze.getCell(x, y).setVisited(true);
                    }
                }
            }
            return maze;
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file.");
            e.printStackTrace();
            return null;
        }
    }
}
