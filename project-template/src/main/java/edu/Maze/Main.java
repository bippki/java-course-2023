package edu.Maze;

public class Main {
    public static void main(String[] args) {
        int rows = 23;
        int cols = 45;
        DFSMazeGenerator dfsMazeGenerator = new DFSMazeGenerator(rows, cols);
        Maze generatedMaze = dfsMazeGenerator.generateMaze();

        MazePrinter mazePrinter = new MazePrinter();
        mazePrinter.printMaze(generatedMaze);

        AbstractMazeSolver mazeSolver = new DFSMazeSolver(generatedMaze);
        if (mazeSolver.solveMaze()) {
            System.out.println("Maze solved!");
            mazePrinter.printMaze(generatedMaze);
        } else {
            System.out.println("No solution found for the maze.");
        }
    }
}
