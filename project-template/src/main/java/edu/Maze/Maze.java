package edu.Maze;


class Maze {
    private final Cell[][] cells;

    public Maze(int rows, int cols) {
        this.cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.cells[i][j] = new Cell(i, j, MazeObjectType.WALL);
            }
        }
    }

    public void setCell(int row, int col, MazeObjectType type) {
        this.cells[row][col].setType(type);
    }

    public Cell getCell(int row, int col) {
        return this.cells[row][col];
    }

    public int getRows() {
        return this.cells.length;
    }

    public int getCols() {
        return this.cells[0].length;
    }
}