package edu.Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DFSMazeGenerator extends MazeGenerator {

    public DFSMazeGenerator(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Maze generateMaze() {
        Maze maze = new Maze(rows, cols);
        dfs(1, 1, maze);
        // ВЫВЕСТИ В МЕТОДЫ
        /*
        maze.setCell(1, 0, MazeObjectType.START);
        maze.setCell(rows - 2, cols - 1, MazeObjectType.END);*/
        return maze;
    }

    private void dfs(int row, int col, Maze maze) {
        maze.setCell(row, col, MazeObjectType.EMPTY);

        List<int[]> directions = new ArrayList<>(Arrays.asList(
                new int[] { 0, 2 },
                new int[] { 2, 0 },
                new int[] { 0, -2 },
                new int[] { -2, 0 }
        ));
        Collections.shuffle(directions, random);

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow > 0 && newRow < rows - 1 && newCol > 0 && newCol < cols - 1 &&
                    maze.getCell(newRow, newCol).getType() == MazeObjectType.WALL) {
                maze.setCell(row + direction[0]/2, col + direction[1]/2, MazeObjectType.EMPTY);
                dfs(newRow, newCol, maze);
            }
        }
    }

}
