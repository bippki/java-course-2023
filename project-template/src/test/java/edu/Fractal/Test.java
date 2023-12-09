package edu.Fractal;

import edu.Fractal.image.IO;
import edu.Fractal.image.LogDensityWithClass;
import edu.Fractal.main.FractalFlame;
import edu.Fractal.transformations.FractalFlameTransformation;
import edu.Fractal.transformations.Rotation;
import edu.Fractal.transformations.Scaling;
import edu.Fractal.transformations.Transformation;
import edu.Fractal.transformations.ffvars.Disc;
import edu.Fractal.transformations.ffvars.Handkerchief;
import edu.Fractal.transformations.ffvars.Heart;
import edu.Fractal.transformations.ffvars.Polar;
import edu.Fractal.transformations.ffvars.Popcorn;
import edu.Fractal.transformations.ffvars.Sinusoidal;
import edu.Fractal.transformations.ffvars.Spherical;
import edu.Fractal.transformations.ffvars.Spiral;
import edu.Fractal.transformations.ffvars.Swirl;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Test1 {

    @Test
    void testGeneratedImage() throws NoSuchFieldException, IllegalAccessException, IOException {
        long seed = new BigInteger("2936234614", 16).longValue();
        Random random = new Random(seed);
        double p = random.nextDouble();

        List<Transformation> variations = new ArrayList<>();
        variations.add(new Disc());
        variations.add(new Handkerchief());
        variations.add(new Scaling(p));

        List<Transformation> usedVariations = new ArrayList<>();
        for (Transformation variation : variations) {
            if (random.nextDouble() < p) {
                usedVariations.add(variation);
            }
        }

        List<Transformation> functions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Transformation transformation = new FractalFlameTransformation(usedVariations, random);
            functions.add(transformation);
        }

        List<Color> colors = List.of(
            Color.decode("#204C5B"),
            Color.decode("#867088"),
            Color.decode("#00C6A8"),
            Color.decode("#F59432"),
            Color.decode("#924755")
        );

        LogDensityWithClass logDensityWithClass = new LogDensityWithClass(colors);
        logDensityWithClass.setBackgroundColor(Color.WHITE);
        logDensityWithClass.setInvert(false);

        Field backgroundColorField = LogDensityWithClass.class.getDeclaredField("backgroundColor");
        Field invertField;
        try {
            invertField = LogDensityWithClass.class.getDeclaredField("invert");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        backgroundColorField.setAccessible(true);
        invertField.setAccessible(true);

        Color backgroundColor = (Color) backgroundColorField.get(logDensityWithClass);
        boolean invert = (boolean) invertField.get(logDensityWithClass);

        assertEquals(Color.WHITE, backgroundColor);
        assertEquals(false, invert);

        int smoothingRadius = 0;

        int[][][] histogram = ChaosGame
            .chaosGameWithClass(functions, 50000, 20000,
                new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0),
                new Dimension(1000, 1000), new Random(seed), Long.toHexString(seed)
            );
        try {
            IO.generateImageSmoothed(
                histogram,
                logDensityWithClass,
                smoothingRadius,
                new File("flame_" + Long.toHexString(seed) + ".png")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String filePath = "flame_2936234614.png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(image);
        Path path = Paths.get(filePath);
        Files.delete(path);

    }

}
