package edu.Maze.Generators;

import edu.Maze.Structures.MazeObjectType;
import edu.Maze.Structures.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OldMazeGenerator extends MazeGenerator {

    public OldMazeGenerator(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Maze generateMaze() {
        Maze maze = new Maze(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maze.setCell(row, col, MazeObjectType.WALL);
            }
        }

        List<int[]> cellsToVisit = new ArrayList<>();
        for (int row = 1; row < rows; row += 2) {
            for (int col = 1; col < cols; col += 2) {
                cellsToVisit.add(new int[] { row, col });
            }
        }

        Collections.shuffle(cellsToVisit, random);

        for (int[] cell : cellsToVisit) {
            int row = cell[0];
            int col = cell[1];
            maze.setCell(row, col, MazeObjectType.EMPTY);

            List<int[]> neighbors = new ArrayList<>();
            if (row - 2 >= 1) neighbors.add(new int[] { row - 2, col });
            if (row + 2 < rows - 1) neighbors.add(new int[] { row + 2, col });
            if (col - 2 >= 1) neighbors.add(new int[] { row, col - 2 });
            if (col + 2 < cols - 1) neighbors.add(new int[] { row, col + 2 });

            if (!neighbors.isEmpty()) {
                Collections.shuffle(neighbors, random);
                int[] neighbor = neighbors.get(0);
                int neighborRow = neighbor[0];
                int neighborCol = neighbor[1];
                maze.setCell(neighborRow, neighborCol, MazeObjectType.EMPTY);

                int wallRow = (row + neighborRow) / 2;
                int wallCol = (col + neighborCol) / 2;
                maze.setCell(wallRow, wallCol, MazeObjectType.EMPTY);
            }
        }

        return maze;
    }
}

