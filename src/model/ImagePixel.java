package model;

/**
 * Represents the list of operations that any implementation of a pixel should support.
 */
public interface ImagePixel {

  /**
   * This method calculates the "brightness" of the pixel in the Luma measurement scale.
   * The Luma of a pixel is the weighted average of the pixel's red-green-blue values.
   * The maximum intensity of a pixel is 255.842. This will be rounded down to 255.
   *
   * @return the integer representation of the brightness of a pixel as its intensity.
   */
  int calculateLuma();

  /**
   * This method calculates the "brightness" of the pixel in the value measurement scale.
   * The "brightness" of the pixel in the value measurement scale. The value of a pixel is the
   * maximum integer value of the pixel's red-green-blue values. The maximum value is 255.
   *
   * @return the integer representation of the brightness of a pixel as its value.
   */
  int calculateValue();

  /**
   * This method calculates the "brightness" of the pixel in the intensity measurement scale.
   * The intensity of a pixel is the average of the pixel's red-green-blue values.
   * The maximum intensity of a pixel is 255.
   *
   * @return the integer representation of the brightness of a pixel as its intensity.
   */
  int calculateIntensity();

  /**
   * Converts a pixel to its gray scale form, allowing one to visualise the pixel's red component.
   *
   * @return a new gray scale image based on the pixel's red component. The deeper the
   *         red component, the brighter the gray scale pixel.
   */
  Pixel convertGrayScaleRed();

  /**
   * Converts a pixel to its gray scale form, allowing one to visualise the pixel's green component.
   *
   * @return a new gray scale image based on the pixel's green component. The deeper the
   *         green component, the brighter the gray scale pixel.
   */
  Pixel convertGrayScaleGreen();

  /**
   * Converts a pixel to its gray scale form, allowing one to visualise the pixel's blue component.
   *
   * @return a new gray scale image based on the pixel's blue component. The deeper the
   *         blue component, the brighter the gray scale pixel.
   */
  Pixel convertGrayScaleBlue();

  /**
   * A method that changes the brightness of a pixel based on an integer value.
   *
   * @param brighterValue changes the brightness of the pixel based on the integer value.
   *                      The pixel will be brightened if the value is positive and darkened if the
   *                      value is negative.
   * @return a new brightened or darkened pixel.
   * @throws IllegalArgumentException if the brighter value is more than 255 or less than -255.
   */
  Pixel changeBrightness(int brighterValue) throws IllegalArgumentException;

  /**
   * A method that converts a pixel to its sepia version, based on the value of all the pixels
   * channels.
   *
   * @return a new pixel that is the original Pixel in sepia form.
   */
  Pixel convertSepia();

  /**
   * A method that converts a pixel to its grayScale form, based on the value of all of its
   * channels.
   *
   * @return a new pixel that is the original Pixel in grayscale form.
   */
  Pixel convertGrayScale();
}
