package edu.Maze;

import edu.Maze.Structures.Maze;
import edu.Maze.Structures.MazeObjectType;

class MazePrinter {

    public void printMaze(Maze maze) {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                MazeObjectType type = maze.getCell(i, j).getType();
                char cellChar = getCharForType(type);
                System.out.print(cellChar + " ");
            }
            System.out.println();
        }
    }

    private char getCharForType(MazeObjectType type) {
        return switch (type) {
            case EMPTY -> '□';
            case WALL -> '■';
            case START -> 'S';
            case END -> 'E';
            case VISITED -> '*';
            default -> '?';
        };
    }
}
