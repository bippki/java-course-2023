package edu.Fractal.transformations.ffvars;

import edu.Fractal.transformations.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Spherical implements Transformation {

  @Override
  public Vector2D apply(Vector2D p) {
    return p.scalarMultiply(1.0 / p.getNormSq());
  }

}
