package edu.Maze;
import edu.Maze.PathFinders.AbstractMazeSolver;
import edu.Maze.Generators.MazeGenerator;
import edu.Maze.Input.MazeCoordinateInputHandler;
import edu.Maze.Input.MazeGeneratorSelector;
import edu.Maze.Input.MazeInputHandler;
import edu.Maze.PathFinders.DFSMazeSolver;
import edu.Maze.Structures.Maze;
import edu.Maze.Structures.MazeObjectType;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] mazeDimensions = MazeInputHandler.getMazeDimensions();
        int rows = mazeDimensions[0];
        int cols = mazeDimensions[1];
        Scanner scanner = new Scanner(System.in);

        MazeGenerator mazeGenerator = MazeGeneratorSelector.selectMazeGenerator(rows, cols);

        Maze generatedMaze = mazeGenerator.generateMaze();
        MazePrinter mazePrinter = new MazePrinter();
        mazePrinter.printMaze(generatedMaze);

        MazeCoordinateInputHandler coordinateInputHandler = new MazeCoordinateInputHandler(scanner);

        int[] startCoordinate = coordinateInputHandler.getValidCoordinate("START", rows, cols);
        generatedMaze.setCell(startCoordinate[0], startCoordinate[1], MazeObjectType.START);

        mazePrinter.printMaze(generatedMaze);

        int[] endCoordinate = coordinateInputHandler.getValidCoordinate("END", rows, cols);
        generatedMaze.setCell(endCoordinate[0], endCoordinate[1], MazeObjectType.END);

        mazePrinter.printMaze(generatedMaze);
        AbstractMazeSolver mazeSolver = new DFSMazeSolver(generatedMaze, startCoordinate[0], startCoordinate[1]);

        if (mazeSolver.solveMaze()) {
            System.out.println("Maze solved!");
            mazePrinter.printMaze(generatedMaze);
        } else {
            System.out.println("No solution found for the maze.");
        }
    }
}
