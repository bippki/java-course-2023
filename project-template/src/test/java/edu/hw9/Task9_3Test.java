package edu.hw9;

import edu.Maze.PathFinders.AbstractMazeSolver;
import edu.Maze.Structures.Maze;
import edu.Maze.Structures.MazeObjectType;
import edu.hw9.Task3.MTDFSMazeSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task9_3Test{
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

        AbstractMazeSolver mazeSolver = new MTDFSMazeSolver(mazeInput,1,1);

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

        AbstractMazeSolver mazeSolver = new MTDFSMazeSolver(mazeInput,1,1);
        assertTrue(mazeSolver.solveMaze());
        assertEquals(MazeObjectType.VISITED, mazeInput.getCell(1, 1).getType());
        assertEquals(MazeObjectType.VISITED, mazeInput.getCell(1, 2).getType());
        assertEquals(MazeObjectType.VISITED, mazeInput.getCell(1, 3).getType());
        assertEquals(MazeObjectType.VISITED, mazeInput.getCell(2, 3).getType());
        assertEquals(MazeObjectType.END, mazeInput.getCell(3, 3).getType());
        assertEquals(MazeObjectType.EMPTY, mazeInput.getCell(3, 1).getType());
        assertEquals(MazeObjectType.EMPTY, mazeInput.getCell(3, 2).getType());
    }
}




