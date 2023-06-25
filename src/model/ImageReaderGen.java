package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Represents a general image reader.
 */
public class ImageReaderGen implements ImageReader {
  private BufferedImage image;
  private int height;
  private int width;
  private int maxValue = 255;
  private final StringBuilder rgbValues = new StringBuilder();


  /**
   * Read an image file in a non-ppm format and store the relevant information.
   *
   * @param filePath the path of the file.
   */
  public void readOther(String filePath) {
    try {
      this.image = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.height = image.getHeight();
    this.width = image.getWidth();
  }


  /**
   * Read an image file in the PPM format and store the relevant information.
   *
   * @param filePath the path of the file.
   */
  public void readPPM(String filePath) {
    StringBuilder heightWidth = new StringBuilder();
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filePath + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    heightWidth.append(width + ",");
    int height = sc.nextInt();
    heightWidth.append(height + ",");
    int maxValue = sc.nextInt();
    heightWidth.append(maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        rgbValues.append(r + "," + g + "," + b + "\n");
      }
    }

    String[] stringA = heightWidth.toString().split(",");
    this.height = Integer.parseInt(stringA[1]);
    this.width = Integer.parseInt(stringA[0]);
    this.maxValue = Integer.parseInt(stringA[2]);
  }


  /**
   * A method that contains the height of the Image.
   *
   * @return the height of the read image as an integer.
   */
  public int getImageHeight() {
    return this.height;
  }

  /**
   * A method that contains the width of the Image.
   *
   * @return the width of the read image as an integer.
   */
  public int getImageWidth() {
    return this.width;
  }

  /**
   * A method that contains the max value of the ppm file.
   *
   * @return the max of the read image as an integer.
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * A method that contains the rgb values of the Image.
   *
   * @return the rgb of the read image as an array of strings.
   */
  public String[] provideRGBAsString() {
    return rgbValues.toString().split("\n");
  }

  /**
   * A method that provides this Image as a buffered Image.
   *
   * @return this image as a buffered Image.
   */
  public BufferedImage getImage() {
    return this.image;
  }
}
