package edu.Maze;

class Cell {
    private int x;
    private int y;
    private boolean visited;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getAddress() {
        char file = (char) ('A' + x);
        int rank = y + 1;
        return String.valueOf(file) + rank;
    }
}
