package edu.Maze.PathFinders;

import edu.Maze.PathFinders.AbstractMazeSolver;
import edu.Maze.Structures.Maze;
import edu.Maze.Structures.MazeObjectType;

public class DFSMazeSolver extends AbstractMazeSolver {
    private int startRow;
    private int startCol;

    public DFSMazeSolver(Maze maze, int startRow, int startCol) {
        super(maze);
        this.startRow = startRow;
        this.startCol = startCol;
    }

    @Override
    public boolean solveMaze() {
        return solve(startRow, startCol);
    }

    @Override
    public boolean solve(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }

        if (maze.getCell(row, col).getType() == MazeObjectType.END) {
            return true;
        }

        if (maze.getCell(row, col).getType() == MazeObjectType.WALL || visited[row][col]) {
            return false;
        }

        visited[row][col] = true;

        if (solve(row + 1, col) || solve(row - 1, col) || solve(row, col + 1) || solve(row, col - 1)) {
            maze.setCell(row, col, MazeObjectType.VISITED);
            return true;
        }

        return false;
    }
}
