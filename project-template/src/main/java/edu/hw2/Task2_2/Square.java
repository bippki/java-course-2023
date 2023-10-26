package edu.hw2.Task2_2;

public class Square extends Rectangle {
    public Square(int size) {
        super(size, size);
    }

    @Override
    public Rectangle setWidth(int width) {
        return super.setWidth(width).setLenght(width);
    }

    @Override
    public Rectangle setLenght(int lenght) {
        return super.setWidth(lenght).setLenght(lenght);
    }
}
