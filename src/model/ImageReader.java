package model;


import java.awt.image.BufferedImage;

/**
 * Represents the operations that should be supported by an image reader.
 */
public interface ImageReader {
  /**
   * Read an image file in any format. Store the data as the fields of the class.
   *
   * @param filePath the path of the file.
   */
  void readPPM(String filePath);

  void readOther(String filepath);

  /**
   * A method that contains the height of the Image.
   *
   * @return the height of the read image as an integer.
   */
  int getImageHeight();

  /**
   * A method that contains the width of the Image.
   *
   * @return the width of the read image as an integer.
   */
  int getImageWidth();

  /**
   * A method that contains the rgb values of the Image.
   *
   * @return the rgb of the read image as an array of strings.
   */
  String[] provideRGBAsString();

  /**
   * A method that contains the max value of the ppm file.
   *
   * @return the max of the read image as an integer.
   */
  int getMaxValue();

  /**
   * A method that provides this Image as a buffered Image.
   *
   * @return this image as a buffered Image.
   */
  BufferedImage getImage();

}
