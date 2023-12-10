package edu.Maze.Generators;

import edu.Maze.Structures.Maze;
import java.util.Random;

public abstract class MazeGenerator {
    protected int rows;
    protected int cols;
    protected Maze maze;
    protected Random random;

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new Maze(rows, cols);
        this.random = new Random();
    }


    public abstract Maze generateMaze();
}
