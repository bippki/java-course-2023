package edu.Fractal.transformations;



import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Transformation extends Function<Vector2D, Vector2D> {

}
