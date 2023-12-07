package edu.Fractal.test;

import edu.Fractal.ChaosGame;
import edu.Fractal.image.IO;
import edu.Fractal.image.LogDensityWithClass;
import edu.Fractal.transformations.Rotation;
import edu.Fractal.transformations.Scaling;
import edu.Fractal.transformations.ffvars.*;
import edu.Fractal.transformations.FractalFlameTransformation;
import edu.Fractal.transformations.Transformation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FractalFlame {

  public static void main(String[] arguments) throws IOException {

    long seed = new BigInteger("2936234611", 16).longValue();

    Random random = new Random(seed);
    double p = random.nextDouble();


    List<Transformation> variations = new ArrayList<>();
    variations.add(new Disc());
    variations.add(new Handkerchief());
    variations.add(new Scaling(p));
    variations.add(new Heart());
    variations.add(new Polar());
    variations.add(new Sinusoidal());
    variations.add(new Spherical());
    variations.add(new Scaling(p));
    variations.add(new Spiral());
    variations.add(new Swirl());
    variations.add(new Popcorn(1.0, 1.0));
    variations.add(new Rotation(p));
    variations.add(new Scaling(p));

    List<Transformation> usedVariations = new ArrayList<>();
    for (Transformation variation : variations) {
      if (random.nextDouble() < p) {
        usedVariations.add(variation);
      }
    }

    List<Transformation> functions = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Transformation transformation =  new FractalFlameTransformation(usedVariations, random);
      functions.add(transformation);
    }

    List<Color> colors = List.of(
        Color.decode("#204C5B"),
        Color.decode("#867088"),
        Color.decode("#00C6A8"),
        Color.decode("#F59432"),
        Color.decode("#924755")
    );

    List<Color> warmColors = List.of(
            Color.decode("#E74C3C"),  // Красный
            Color.decode("#F39C12"),  // Оранжевый
            Color.decode("#D35400"),  // Темно-оранжевый
            Color.decode("#F1C40F"),  // Жёлтый
            Color.decode("#D35400")   // Темно-оранжевый
    );

    LogDensityWithClass logDensityWithClass = new LogDensityWithClass(warmColors);
    logDensityWithClass.setBackgroundColor(Color.WHITE);
    logDensityWithClass.setInvert(false);

    int smoothingRadius = 0;
    int[][][] histogram = ChaosGame
        .chaosGameWithClass(functions, 50000, 20000,
            new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0),
            new Dimension(1000, 1000), new Random(seed), Long.toHexString(seed));
    IO.generateImageSmoothed(histogram, logDensityWithClass, smoothingRadius, new File("flame_" + Long.toHexString(seed) + ".png"));
  }


}
