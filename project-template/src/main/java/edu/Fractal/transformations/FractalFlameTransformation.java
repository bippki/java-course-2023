package edu.Fractal.transformations;


 
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FractalFlameTransformation implements Transformation {

  private final Transformation transformation;

  public FractalFlameTransformation(double[] affineParameters, double[] postTransformParameters,
      List<Transformation> variations, double... weights) {
    Transformation affineTransformation = new edu.Fractal.transformations.AffineTransformation(affineParameters);

    List<Pair<Double, Transformation>> weightedVariations = new LinkedList<>();
    for (int i = 0; i < variations.size(); i++) {
      weightedVariations.add(new Pair<>(weights[i], variations.get(i)));
    }

    Transformation postTransform = new edu.Fractal.transformations.AffineTransformation(postTransformParameters);

    Transformation weightedSum = new WeightedSum(weightedVariations);

    this.transformation = (p) -> postTransform.apply(weightedSum.apply(affineTransformation.apply(p)));
  }

  public FractalFlameTransformation(List<Transformation> variations, Random random) {
    this(randomDoubles(random, 6, -0.5, 0.5), randomDoubles(random, 6, -0.5, 0.5), variations,
        randomDoubles(random, variations.size(), 0.0, 1.0));
  }

  private static double[] randomDoubles(Random random, int n, double low, double high) {
    double[] list = new double[n];
    for (int i = 0; i < n; i++) {
      list[i] = random.nextDouble() * (high - low) + low;
    }
    return list;
  }

  @Override
  public Vector2D apply(Vector2D t) {
    return transformation.apply(t);
  }

}
