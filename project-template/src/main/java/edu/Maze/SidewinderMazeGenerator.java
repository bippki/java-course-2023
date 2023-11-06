package edu.Maze;

import java.util.Random;

public class SidewinderMazeGenerator extends MazeGenerator {

    public SidewinderMazeGenerator(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Maze generateMaze() {
        Maze maze = new Maze(rows, cols);

        for (int row = 1; row < rows; row += 2) {
            for (int col = 1; col < cols; col += 2) {
                maze.setCell(row, col, MazeObjectType.EMPTY);

                if (col < cols - 2 && random.nextBoolean()) {
                    maze.setCell(row, col + 1, MazeObjectType.EMPTY);
                } else {
                    int randomRow = row - 1 + random.nextInt(2) * 2;
                    maze.setCell(randomRow, col, MazeObjectType.EMPTY);
                }
            }
        }

        return maze;
    }
}
