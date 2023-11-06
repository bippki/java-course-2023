package edu.Maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.junit.jupiter.api.Test;

public class MazeTest {

    @Test
    void testWriteMazeToFile() {
        int rows = 5;
        int cols = 5;
        Maze maze = new Maze(rows, cols);
        maze.setCell(1, 1, MazeObjectType.START);
        maze.setCell(3, 3, MazeObjectType.END);
        maze.setCell(2, 2, MazeObjectType.WALL);

        String filePath = "testMaze.txt";

        MazeFileWriter.writeMazeToFile(maze, filePath);

        File file = new File(filePath);
        assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String sizeLine = reader.readLine();
            String[] sizeTokens = sizeLine.split(" ");
            int fileRows = Integer.parseInt(sizeTokens[0]);
            int fileCols = Integer.parseInt(sizeTokens[1]);
            assertEquals(rows, fileRows);
            assertEquals(cols, fileCols);

            for (int row = 0; row < rows; row++) {
                String line = reader.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < cols; col++) {
                    MazeObjectType fileObjectType = MazeObjectType.valueOf(tokens[col]);
                    MazeObjectType expectedObjectType = maze.getCell(row, col).getType();
                    assertEquals(expectedObjectType, fileObjectType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
    }

    @Test
    public void testReadMazeFromFile() {
        int rows = 5;
        int cols = 5;
        Maze mazeInput = new Maze(rows, cols);
        mazeInput.setCell(1, 1, MazeObjectType.START);
        mazeInput.setCell(1, 2, MazeObjectType.EMPTY);
        mazeInput.setCell(1, 3, MazeObjectType.EMPTY);
        mazeInput.setCell(2, 1, MazeObjectType.EMPTY);
        mazeInput.setCell(2, 3, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 1, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 2, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 3, MazeObjectType.END);
        mazeInput.setCell(2, 2, MazeObjectType.WALL);

        String filePath = "testMaze.txt";

        MazeFileWriter.writeMazeToFile(mazeInput, filePath);

        String filePath2 = "testMaze.txt";

        Maze maze = MazeFileReader.readMazeFromFile(filePath2);

        assertNotNull(maze);
        assertEquals(5, maze.getRows());
        assertEquals(5, maze.getCols());
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                assertEquals(mazeInput.getCell(row, col).getType(), maze.getCell(row, col).getType());
            }
        }
    }
    @Test
    public void testNoPathFind() {
        int rows = 5;
        int cols = 5;
        Maze mazeInput = new Maze(rows, cols);
        mazeInput.setCell(1, 1, MazeObjectType.START);
        mazeInput.setCell(1, 2, MazeObjectType.EMPTY);
        mazeInput.setCell(1, 3, MazeObjectType.EMPTY);
        mazeInput.setCell(2, 1, MazeObjectType.WALL);
        mazeInput.setCell(2, 2, MazeObjectType.WALL);
        mazeInput.setCell(2, 3, MazeObjectType.WALL);
        mazeInput.setCell(3, 1, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 2, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 3, MazeObjectType.END);

        AbstractMazeSolver mazeSolver = new DFSMazeSolver(mazeInput,1,1);
        assertFalse(mazeSolver.solveMaze());
    }
    @Test
    public void testPathFind() {
        int rows = 5;
        int cols = 5;
        Maze mazeInput = new Maze(rows, cols);
        mazeInput.setCell(1, 1, MazeObjectType.START);
        mazeInput.setCell(1, 2, MazeObjectType.EMPTY);
        mazeInput.setCell(1, 3, MazeObjectType.EMPTY);
        mazeInput.setCell(2, 1, MazeObjectType.WALL);
        mazeInput.setCell(2, 2, MazeObjectType.WALL);
        mazeInput.setCell(2, 3, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 1, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 2, MazeObjectType.EMPTY);
        mazeInput.setCell(3, 3, MazeObjectType.END);

        AbstractMazeSolver mazeSolver = new DFSMazeSolver(mazeInput,1,1);
        assertTrue(mazeSolver.solveMaze());
    }
}



