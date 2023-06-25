package model;

import java.awt.Color;

/**
 * Represents a single pixel. The pixel has three values that determine the pixel's color : the
 * three values represent the pixel's color on the red-green-blue scale (RGB).
 */
public class Pixel implements ImagePixel {
  private int red;
  private int green;
  private int blue;

  /**
   * A constructor for a colored pixel.
   *
   * @param red   represents the red value in a pixel's RGB scale.
   * @param green represents the green value in a pixel's RGB scale.
   * @param blue  represents the blue value in a pixel's RGB scale.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255) {
      throw new IllegalArgumentException("Red value is invalid.");
    }
    if (green < 0 || green > 255) {
      throw new IllegalArgumentException("Green value is invalid.");
    }
    if (blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Blue value is invalid.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Represents a grey-scale pixel. The constructor takes in one value and initialises each color in
   * the pixel on the red-green-blue scale to the one value.
   *
   * @param value represents the value of the pixel on the greyscale, with 0 representing black and
   *              255 representing white.
   */
  public Pixel(int value) {
    this.red = value;
    this.green = value;
    this.blue = value;
  }

  /**
   * This method calculates the "brightness" of the pixel in the Luma measurement scale.
   * The Luma of a pixel is the weighted average of the pixel's red-green-blue values.
   * The maximum intensity of a pixel is 255.842. This will be rounded down to 255.
   *
   * @return the integer representation of the brightness of a pixel as its intensity.
   */
  public int calculateLuma() {
    return (int) ((0.2162 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue));
  }

  /**
   * This method calculates the "brightness" of the pixel in the value measurement scale.
   * The "brightness" of the pixel in the value measurement scale. The value of a pixel is the
   * maximum integer value of the pixel's red-green-blue values. The maximum value is 255.
   *
   * @return the integer representation of the brightness of a pixel as its value.
   */
  public int calculateValue() {
    int maxOfRedAndGreen = Math.max(this.red, this.green);
    return Math.max(maxOfRedAndGreen, this.blue);
  }

  /**
   * This method calculates the "brightness" of the pixel in the intensity measurement scale.
   * The intensity of a pixel is the average of the pixel's red-green-blue values.
   * The maximum intensity of a pixel is 255.
   *
   * @return the integer representation of the brightness of a pixel as its intensity.
   */
  public int calculateIntensity() {
    double average = (this.red + this.green + this.blue) / 3;
    return (int) average;
  }

  /**
   * Converts a pixel to its gray scale form, allowing one to visualise the pixel's red component.
   *
   * @return a new gray scale image based on the pixel's red component. The deeper the
   *         red component, the brighter the gray scale pixel.
   */
  public Pixel convertGrayScaleRed() {
    return new Pixel(this.red);
  }

  /**
   * Converts a pixel to its gray scale form, allowing one to visualise the pixel's green component.
   *
   * @return a new gray scale image based on the pixel's green component. The deeper the
   *         green component, the brighter the gray scale pixel.
   */
  public Pixel convertGrayScaleGreen() {
    return new Pixel(this.green);
  }

  /**
   * Converts a pixel to its gray scale form, allowing one to visualise the pixel's blue component.
   *
   * @return a new gray scale image based on the pixel's blue component. The deeper the
   *         blue component, the brighter the gray scale pixel.
   */
  public Pixel convertGrayScaleBlue() {
    return new Pixel(this.blue);
  }

  /**
   * A method that changes the brightness of a pixel based on a integer value.
   *
   * @param brighterValue changes the brightness of the pixel based on the integer value.
   *                      The pixel will be brightened if the value is positive and darkened if the
   *                      value is negative.
   * @return a new brightened or darkened pixel.
   * @throws IllegalArgumentException if the brighter value is more than 255 or less than -255.
   */
  public Pixel changeBrightness(int brighterValue) throws IllegalArgumentException {
    // exception handling
    if (brighterValue > 255 || brighterValue < -255) {
      throw new IllegalArgumentException("Please input a value between 255 and negative 255");
    }

    return new Pixel(checkColorBoundaries(this.red, brighterValue)
            , checkColorBoundaries(this.green, brighterValue)
            , checkColorBoundaries(this.blue, brighterValue));
  }

  private int checkColorBoundaries(int component, int brighterValue) {
    if (component + brighterValue < 0) {
      return 0;
    } else if (component + brighterValue > 255) {
      return 255;
    } else {
      return component + brighterValue;
    }
  }

  /**
   * The to string over-ridden for this class.
   *
   * @return a string of the red green and blue values, separated by a newLine.
   */
  @Override
  public String toString() {
    return this.red + "\n" + this.green + "\n" + this.blue + "\n";
  }

  /**
   * Defining custom equality for pixels.
   *
   * @param other The second pixel to be compared.
   * @return true if the fields of the other pixel are the same as this pixel.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Pixel)) {
      return false;
    }

    return this.red == ((Pixel) other).red
            && this.green == ((Pixel) other).green
            && this.blue == ((Pixel) other).blue;
  }

  /**
   * Custom hashcode for pixels.
   *
   * @return the sum of the red green and blue values.
   */
  @Override
  public int hashCode() {
    return this.red + this.blue + this.green;
  }

  /**
   * Converts the pixel to a sepia version of itself.
   *
   * @return a new Pixel that is the original pixel in sepia color.
   */
  public Pixel convertSepia() {
    int redNew = (int) (0.393 * (this.red) + 0.769 * this.green + 0.189 * this.blue);
    int greenNew = (int) (0.349 * (this.red) + 0.686 * this.green + 0.168 * this.blue);
    int blueNew = (int) (0.272 * (this.red) + 0.534 * this.green + 0.131 * this.blue);

    return new Pixel(Math.min(redNew, 255), Math.min(greenNew, 255), Math.min(blueNew, 255));
  }

  /**
   * Converts the pixel to grayscale.
   *
   * @return a new Pixel that is the original pixel in grayScale.
   */
  public Pixel convertGrayScale() {
    int value = (int) (0.2126 * (this.red) + 0.7152 * this.green + 0.0722 * this.blue);
    return new Pixel(Math.min(value, 255));
  }

  /**
   * A getter method for the red component of the pixel.
   *
   * @return the integer value of the red component of the pixel.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * A getter method for the green component of the pixel.
   *
   * @return the integer value of the green component of the pixel.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * A getter method for the blue component of the pixel.
   *
   * @return the integer value of the blue component of the pixel.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Converts a pixel to a color.
   *
   * @return the pixel as a color.
   */
  public Color convertToColor() {
    return new Color(this.red, this.green, this.blue);
  }
}
