package edu.Fractal.image;

public class ConcurrentImageBuffer implements ImageBuffer {

  private final int[][][] buffer;
  private final int[] dimensions;

  public ConcurrentImageBuffer(int[][][] buffer) {
    this.buffer = buffer;
    this.dimensions = new int[] {buffer.length, buffer[0].length, buffer[0][0].length};
  }
  public void update(int x, int y, int z) {
    if (x < 0 || x >= dimensions[0] || y < 0 || y >= dimensions[1] || z < 0 || z >= dimensions[2]) {
      return;
    }
    buffer[x][y][z]++;
  }

  public int[][][] getBuffer() {
    return buffer;
  }

  public int get(int x, int y, int z) {
    return buffer[x][y][z];
  }

}
