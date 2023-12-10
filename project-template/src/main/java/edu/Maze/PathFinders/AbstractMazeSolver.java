package edu.Maze.PathFinders;

import edu.Maze.Structures.Maze;

public abstract class AbstractMazeSolver {
    protected Maze maze;
    protected boolean[][] visited;
    protected int rows;
    protected int cols;

    public AbstractMazeSolver(Maze maze) {
        this.maze = maze;
        this.rows = maze.getRows();
        this.cols = maze.getCols();
        this.visited = new boolean[rows][cols];
    }

    public abstract boolean solveMaze();


    public abstract boolean solve(int row, int col);
}
