package edu.Fractal;


import edu.Fractal.image.ConcurrentImageBuffer;
import edu.Fractal.image.ImageBuffer;
import edu.Fractal.transformations.ClassifiedPoint;
import edu.Fractal.transformations.StochasticFunctionSet;
import edu.Fractal.transformations.Transformation;
import edu.Fractal.transformations.TransformationWithClassification;
import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChaosGame {

  private static StochasticFunctionSet buildUniformlyDistributedStochasticFunctionSet(
      List<Transformation> transformations) {

    List<Pair<Transformation, Double>> uniformlyDistributedTransformation = new ArrayList<>();

    for (Transformation transformation : transformations) {
      uniformlyDistributedTransformation.add(new Pair<>(transformation, 1.0));
    }
    return new StochasticFunctionSet(transformations, uniformlyDistributedTransformation);
  }
  private static Vector2D samplePoint(Rectangle2D.Double area, Random random) {
    double x = area.getX() + random.nextDouble() * area.getWidth();
    double y = area.getY() + random.nextDouble() * area.getHeight();
    return new Vector2D(x, y);
  }


  public static int[][][] chaosGameWithClass(List<Transformation> transformations,
      int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, Random random, String name) {
    return chaosGameWithClass(buildUniformlyDistributedStochasticFunctionSet(transformations),
        points, iterations, area, imageSize, random, name);
  }

  public static int[][][] chaosGameWithClass(TransformationWithClassification transformations,
                                             int points, int iterations, Rectangle2D.Double area,
                                             Dimension imageSize, Random random, String name) {
    int[][][] histogram = new int[imageSize.width][imageSize.height][transformations
        .classCount()];
    return chaosGameWithClass(transformations, histogram, points, iterations, area, area, imageSize, random, name);
  }


  public static int[][][] chaosGameWithClass(TransformationWithClassification transformations, int[][][] histogram, int points, int iterations,
      Rectangle2D.Double drawArea, Rectangle2D.Double iterationArea, Dimension imageSize, Random random, String name) {

    double scaleX = imageSize.getWidth() / drawArea.getWidth();
    double scaleY = imageSize.getHeight() / drawArea.getHeight();
    Function<Vector2D, Point> toPixel = v -> new Point((int) ((v.getX() - drawArea.getX()) * scaleX),
        (int) ((v.getY() - drawArea.getY()) * scaleY));

    ImageBuffer buffer = new ConcurrentImageBuffer(histogram);

    ProgressBar pb = new ProgressBar(name, points);
    pb.start();

    /*
     * s
     */
    Stream.generate(() -> samplePoint(drawArea, random)).peek(i -> pb.step()).parallel().limit(points)
        .map(ClassifiedPoint::new).forEach(q -> Stream
        .iterate(q, p -> iterationArea.contains(p.getX(), p.getY()),
                transformations::applyWithClass)
        .skip(20).limit(iterations).forEach(p -> {
          if (drawArea.contains(p.getX(), p.getY())) {
            Point px = toPixel.apply(p);
            buffer.update(px.x, px.y, p.getClassification());
          }
        }));

    pb.stop();

    return buffer.getBuffer();
  }
}
