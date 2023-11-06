package edu.Maze;

public class DFSMazeSolver extends AbstractMazeSolver {
    public DFSMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public boolean solveMaze() {
        return solve(1, 0);
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