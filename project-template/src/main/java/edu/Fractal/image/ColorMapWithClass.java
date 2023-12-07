package edu.Fractal.image;

import java.awt.*;
import java.util.function.Function;

public interface ColorMapWithClass extends Function<int[][][], Color[][]> {

  Color getBackgroundColor();

}
