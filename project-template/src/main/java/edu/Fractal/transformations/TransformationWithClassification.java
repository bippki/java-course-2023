package edu.Fractal.transformations;



 
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface TransformationWithClassification extends Transformation {

  edu.Fractal.transformations.ClassifiedPoint applyWithClass(edu.Fractal.transformations.ClassifiedPoint classifiedPoint);

  @Override
  default Vector2D apply(Vector2D point) {
    return apply(new edu.Fractal.transformations.ClassifiedPoint(point, 0));
  }

  int classCount();
}
