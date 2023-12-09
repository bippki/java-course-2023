package edu.Fractal.image;

import java.awt.*;
import java.util.function.Function;

public interface ColorMap extends Function<int[][], Color[][]> {

  Color getBackgroundColor();
  
}
