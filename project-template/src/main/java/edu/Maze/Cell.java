package edu.Maze;


class Cell {
    private int row;
    private int col;
    private MazeObjectType type;

    public Cell(int row, int col, MazeObjectType type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    // Getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public MazeObjectType getType() {
        return type;
    }

    public void setType(MazeObjectType type) {
        this.type = type;
    }
}
