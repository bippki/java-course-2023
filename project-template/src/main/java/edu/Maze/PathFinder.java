package edu.Maze;

import java.util.*;

public class PathFinder {

    public static List<Cell> findPath(MazeGenerator maze, Cell start, Cell end) {
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Queue<Cell> queue = new LinkedList<>();

        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (current == end) {
                return reconstructPath(cameFrom, start, end);
            }

            List<Cell> neighbors = getPassableNeighbors(maze, current);
            for (Cell neighbor : neighbors) {
                if (!cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return null; // No path found
    }

    private static List<Cell> getPassableNeighbors(MazeGenerator maze, Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x > 0 && !maze.hasWall(x, y, x - 1, y)) {
            neighbors.add(maze.getCell(x - 1, y));
        }
        if (x < maze.getWidth() - 1 && !maze.hasWall(x, y, x + 1, y)) {
            neighbors.add(maze.getCell(x + 1, y));
        }
        if (y > 0 && !maze.hasWall(x, y, x, y - 1)) {
            neighbors.add(maze.getCell(x, y - 1));
        }
        if (y < maze.getHeight() - 1 && !maze.hasWall(x, y, x, y + 1)) {
            neighbors.add(maze.getCell(x, y + 1));
        }

        return neighbors;
    }

    private static List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Cell current = end;

        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
