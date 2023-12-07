package edu.Fractal.image;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class IO {

  public static void generateImage(int[][][] image, ColorMapWithClass colormap, File file) throws IOException {
    BufferedImage bi = generateImage(image, colormap);
    ImageIO.write(bi, "PNG", file);
  }

  public static void generateImageSmoothed(int[][][] image, ColorMapWithClass colormap, int smoothingRadius, File file) throws IOException {
    BufferedImage bi = generateImage(image, colormap, smoothingRadius);
    ImageIO.write(bi, "PNG", file);
  }
  public static BufferedImage generateImage(int[][][] image, ColorMapWithClass colormap) throws IOException {
    return generateImage(colormap.apply(image), colormap.getBackgroundColor());
  }

  public static BufferedImage generateImage(Color[][] image, Color backgroundColor) {
    image = applyBackgroundColor(image, backgroundColor);
    int width = image.length;
    int height = image[0].length;

    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = bi.createGraphics();
    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        bi.setRGB(x, y, image[x][y].getRGB());
      }
    }
    return bi;
  }

  public static BufferedImage generateImage(int[][][] image, ColorMapWithClass colormap, int smoothingRadius) throws IOException {
    return generateImage(colormap.apply(image), colormap.getBackgroundColor(), smoothingRadius);
  }

  public static BufferedImage generateImage(Color[][] image, Color backgroundColor, int smoothingRadius) {
    image = applyBackgroundColor(image, backgroundColor);
    int width = image.length;
    int height = image[0].length;

    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = bi.createGraphics();
    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int averageColor = calculateAverageColor(image, x, y, smoothingRadius);
        bi.setRGB(x, y, averageColor);
      }
    }

    return bi;
  }

  private static int calculateAverageColor(Color[][] image, int centerX, int centerY, int smoothingRadius) {
    int totalRed = 0;
    int totalGreen = 0;
    int totalBlue = 0;
    int totalPixels = 0;

    int width = image.length;
    int height = image[0].length;

    for (int i = -smoothingRadius; i <= smoothingRadius; i++) {
      for (int j = -smoothingRadius; j <= smoothingRadius; j++) {
        int newX = centerX + i;
        int newY = centerY + j;

        if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
          Color pixelColor = image[newX][newY];
          totalRed += pixelColor.getRed();
          totalGreen += pixelColor.getGreen();
          totalBlue += pixelColor.getBlue();
          totalPixels++;
        }
      }
    }

    int averageRed = totalRed / totalPixels;
    int averageGreen = totalGreen / totalPixels;
    int averageBlue = totalBlue / totalPixels;

    return new Color(averageRed, averageGreen, averageBlue).getRGB();
  }

  public static Color[][] applyBackgroundColor(Color[][] image, Color backgroundColor) {
    int width = image.length;
    int height = image[0].length;

    Color[][] resultImage = new Color[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color pixelColor = image[x][y];

        resultImage[x][y] = (pixelColor.equals(Color.WHITE)) ? backgroundColor : pixelColor;
      }
    }

    return resultImage;
  }

  public static void writeFile(int[][][] histogram, File file) throws IOException {
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream stream = new FileOutputStream(file);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(7);
  }

  public static void writeCSV(int[][][] histogram, File file) throws IOException {
      FileWriter out = new FileWriter(file);
      try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
        int width = histogram.length;
        int height = histogram[0].length;
        int classes = histogram[0][0].length;
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
              List<String> record = Arrays.stream(histogram[x][y]).mapToObj(Integer::toString).collect(
                  Collectors.toList());
                printer.printRecord(record);
          }
        }
    }
      out.close();
  }

  public static int[][][] readCSV(int w, int h, int c, File file) throws IOException {

    int[][][] histogram = new int[w][h][c];

    Reader in = new FileReader(file);
    Iterator<CSVRecord> records = CSVFormat.DEFAULT
        .parse(in).iterator();

    for (int x = 0; x < w; x++) {
      for (int y = 0; y < h; y++) {
        CSVRecord record = records.next();
        for (int j = 0; j < c; j++) {
          histogram[x][y][j] = Integer.parseInt(record.get(j));
        }
      }
    }
    in.close();

    return histogram;
  }

  public static BufferedImage generateImage(int[][] image, ColorMap colormap) throws IOException {
    return generateImage(colormap.apply(image), colormap.getBackgroundColor());
  }

}
