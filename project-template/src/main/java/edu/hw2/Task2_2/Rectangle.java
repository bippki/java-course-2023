package edu.hw2.Task2_2;

public class Rectangle {
    private final int width;
    private final int lenght;

    public Rectangle(int width, int lenght) {
        this.width = width;
        this.lenght = lenght;
    }

    public double getLength() {
        return lenght;
    }

    public double getWidth() {
        return width;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.lenght);
    }

    public Rectangle setLenght(int lenght) {
        return new Rectangle(lenght, this.width);
    }
    public double area() {
        return width * lenght;
    }
}
