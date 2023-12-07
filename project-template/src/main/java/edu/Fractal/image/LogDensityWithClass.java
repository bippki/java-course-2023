package edu.Fractal.image;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.util.FastMath;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LogDensityWithClass implements ColorMapWithClass {

  private final List<Color> bases;
  @Setter
  private boolean invert;
  @Setter
  private Color backgroundColor = Color.WHITE;

  public LogDensityWithClass(List<Color> bases) {
    this.bases = bases;
  }

  private static int sum(int[] array) {
    return Arrays.stream(array).sum();
  }

  @Override
  public Color[][] apply(int[][][] pixels) {
    int classes = pixels[0][0].length;
    int[] max = new int[classes];
    for (int[][] pixel : pixels) {
      for (int[] ints : pixel) {
        for (int c = 0; c < classes; c++) {
          max[c] = FastMath.max(max[c], ints[c]);
        }
      }
    }

    Color[][] colors = new Color[pixels.length][];
    for (int x = 0; x < pixels.length; x++) {
      colors[x] = new Color[pixels[x].length];
      for (int y = 0; y < pixels[x].length; y++) {
        double r = 0, g = 0, b = 0;
        for (int c = 0; c < classes; c++) {
          if (pixels[x][y][c] > 0) {
            double ratio = FastMath.log(pixels[x][y][c]) / FastMath.log(max[c]);
            int baseRed = bases.get(c).getRed();
            int baseGreen = bases.get(c).getGreen();
            int baseBlue = bases.get(c).getBlue();

            if (invert) {
              r += ratio * (baseRed / 255.0);
              g += ratio * (baseGreen / 255.0);
              b += ratio * (baseBlue / 255.0);
            } else {
              r += ratio * ((255 - baseRed) / 255.0);
              g += ratio * ((255 - baseGreen) / 255.0);
              b += ratio * ((255 - baseBlue) / 255.0);
            }
          }
        }

        r = Math.max(0.0, Math.min(1.0, r));
        g = Math.max(0.0, Math.min(1.0, g));
        b = Math.max(0.0, Math.min(1.0, b));

        int red = (int) (255 - 255 * r);
        int green = (int) (255 - 255 * g);
        int blue = (int) (255 - 255 * b);
        colors[x][y] = new Color(red, green, blue);
      }
    }
    return colors;
  }

  @Override
  public Color getBackgroundColor() {
    if (invert) {
      int invertedRGB = ~backgroundColor.getRGB();
      return new Color(invertedRGB);
    } else {
      return backgroundColor;
    }
  }

}
