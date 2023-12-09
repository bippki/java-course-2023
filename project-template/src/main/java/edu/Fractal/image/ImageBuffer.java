package edu.Fractal.image;

public interface ImageBuffer {

    void update(int x, int y, int z);

    int get(int x, int y, int z);


    int[][][] getBuffer();

}
