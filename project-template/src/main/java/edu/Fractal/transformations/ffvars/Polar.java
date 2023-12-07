package edu.Fractal.transformations.ffvars;

import edu.Fractal.transformations.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Polar implements Transformation {

  @Override
  public Vector2D apply(Vector2D t) {
    double omega = FastMath.atan(t.getX() / t.getY());
    double r = t.getNorm();
    return new Vector2D(omega / FastMath.PI, r - 1);
  }

}
