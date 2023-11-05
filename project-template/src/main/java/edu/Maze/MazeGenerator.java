package edu.Maze;

import java.util.*;

public class MazeGenerator {
    private int width;
    private int height;
    private boolean[][] visited;
    private Stack<Cell> stack;
    private Cell[][] cells;

    private boolean[][] verticalWalls;
    private boolean[][] horizontalWalls;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.verticalWalls = new boolean[width][height];
        this.horizontalWalls = new boolean[width][height];
        this.visited = new boolean[width][height];
        this.stack = new Stack<>();
        this.cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public void setWall(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            verticalWalls[x1][Math.min(y1, y2)] = true;
        } else if (y1 == y2) {
            horizontalWalls[Math.min(x1, x2)][y1] = true;
        }
    }

    public boolean hasWall(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            return verticalWalls[x1][Math.min(y1, y2)];
        } else if (y1 == y2) {
            return horizontalWalls[Math.min(x1, x2)][y1];
        }
        return false; // Нет стены между клетками
    }

    public void generateMaze() {
        Cell currentCell = cells[0][0];
        currentCell.setVisited(true);
        stack.push(currentCell);

        while (!stack.isEmpty()) {
            List<Cell> neighbors = getUnvisitedNeighbors(currentCell);
            if (!neighbors.isEmpty()) {
                Cell nextCell = neighbors.get(new Random().nextInt(neighbors.size()));
                removeWall(currentCell, nextCell);
                nextCell.setVisited(true);
                stack.push(nextCell);
                currentCell = nextCell;
            } else if (!stack.isEmpty()) {
                currentCell = stack.pop();
            }
        }
    }

    public List<Cell> getUnvisitedNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x > 1 && !cells[x - 2][y].isVisited()) {
            neighbors.add(cells[x - 2][y]);
        }
        if (x < width - 2 && !cells[x + 2][y].isVisited()) {
            neighbors.add(cells[x + 2][y]);
        }
        if (y > 1 && !cells[x][y - 2].isVisited()) {
            neighbors.add(cells[x][y - 2]);
        }
        if (y < height - 2 && !cells[x][y + 2].isVisited()) {
            neighbors.add(cells[x][y + 2]);
        }

        return neighbors;
    }

    private void removeWall(Cell currentCell, Cell nextCell) {
        int x1 = currentCell.getX();
        int y1 = currentCell.getY();
        int x2 = nextCell.getX();
        int y2 = nextCell.getY();

        if (x1 > x2) {
            cells[x1 - 1][y1].setVisited(true);
        } else if (x1 < x2) {
            cells[x1 + 1][y1].setVisited(true);
        } else if (y1 > y2) {
            cells[x1][y1 - 1].setVisited(true);
        } else if (y1 < y2) {
            cells[x1][y1 + 1].setVisited(true);
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Cell> findPath(Cell start, Cell end) {
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Queue<Cell> queue = new LinkedList<>();

        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (current == end) {
                return reconstructPath(cameFrom, start, end);
            }

            for (Cell neighbor : getUnvisitedNeighbors(current)) {
                if (!cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return null; // No path found
    }
    private List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Cell current = end;

        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x > 0) {
            neighbors.add(cells[x - 1][y]);
        }
        if (x < width - 1) {
            neighbors.add(cells[x + 1][y]);
        }
        if (y > 0) {
            neighbors.add(cells[x][y - 1]);
        }
        if (y < height - 1) {
            neighbors.add(cells[x][y + 1]);
        }

        return neighbors;
    }



}
