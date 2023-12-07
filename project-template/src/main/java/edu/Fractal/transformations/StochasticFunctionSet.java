package edu.Fractal.transformations;


 
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StochasticFunctionSet implements TransformationWithClassification {

  private final List<Transformation> transformations;
  private final EnumeratedIntegerDistribution distribution;
  private final List<Pair<Transformation, Double>> probabilities;

  public StochasticFunctionSet(List<Transformation> transformations, List<Pair<Transformation, Double>> probabilities) {
    if (probabilities == null) {
      throw new NullPointerException("Probabilities cannot be null.");
    }
    this.transformations = transformations;
    this.probabilities = probabilities;
    this.distribution = new EnumeratedIntegerDistribution(
            IntStream.range(0, probabilities.size()).toArray(),
            probabilities.stream().mapToDouble(Pair::getSecond).toArray());
  }

  private List<Transformation> getTransformations() {
    return probabilities.stream().map(Pair::getFirst).collect(Collectors.toList());
  }

  public edu.Fractal.transformations.ClassifiedPoint applyWithClass(edu.Fractal.transformations.ClassifiedPoint classifiedPoint) {
    if (transformations.isEmpty()) {
      throw new IllegalStateException("No transformations in the set.");
    }
    int i = distribution.sample();
    return new edu.Fractal.transformations.ClassifiedPoint(transformations.get(i).apply(classifiedPoint), i);
  }

  public Vector2D apply(Vector2D p) {
    if (transformations.isEmpty()) {
      throw new IllegalStateException("No transformations in the set.");
    }
    Transformation f = transformations.get(this.distribution.sample());
    return f.apply(p);
  }

  @Override
  public int classCount() {
    return transformations.size();
  }


}
