package edu.Fractal.transformations.ffvars;

import edu.Fractal.transformations.Transformation;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class ComplexMultiplication implements Transformation {

  private final Complex a;

  public ComplexMultiplication(Complex a) {
    this.a = a;
  }

  @Override
  public Vector2D apply(Vector2D v) {
    Complex r = a.multiply(new Complex(v.getX(), v.getY()));
    return new Vector2D(r.getReal(), r.getImaginary());
  }

}
