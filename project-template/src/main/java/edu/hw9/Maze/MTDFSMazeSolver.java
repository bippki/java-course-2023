package edu.hw9.Maze;

import edu.Maze.PathFinders.AbstractMazeSolver;
import edu.Maze.Structures.Maze;
import edu.Maze.Structures.MazeObjectType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MTDFSMazeSolver extends AbstractMazeSolver {
    private int startRow;
    private int startCol;
    private ExecutorService executor;

    public MTDFSMazeSolver(Maze maze, int startRow, int startCol) {
        super(maze);
        this.startRow = startRow;
        this.startCol = startCol;
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    public boolean solveMaze() {
        try {
            return solve(startRow, startCol);
        } finally {
            executor.shutdown();
        }
    }

    @Override public boolean solve(int row, int col) {
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

        List<Future<Boolean>> futures = new ArrayList<>();

        futures.add(executor.submit(() -> solve(row + 1, col)));
        futures.add(executor.submit(() -> solve(row - 1, col)));
        futures.add(executor.submit(() -> solve(row, col + 1)));
        futures.add(executor.submit(() -> solve(row, col - 1)));

        for (Future<Boolean> future : futures) {
            try {
                if (future.get()) {
                    maze.setCell(row, col, MazeObjectType.VISITED);
                    return true;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
