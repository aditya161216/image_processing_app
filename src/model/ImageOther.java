package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Represents an image of any type except ppm.
 */
public class ImageOther extends AbstractImage {

  private BufferedImage image;

  /**
   * Constructor for an ImageOther class.
   *
   * @param reader represents an ImageReader, that contains all the read data from an image file.
   */
  public ImageOther(ImageReader reader) {
    super(reader);
    this.image = reader.getImage();
    loadImage();
  }

  /**
   * Secondary Image constructor for testing purposes.
   *
   * @param width    the width of an image.
   * @param height   the height of an image.
   * @param maxValue the maximum value a pixel can have in an image.
   * @param pixels   an array of pixels contained in the image.
   */
  public ImageOther(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue, pixels);
    loadImage();
  }


  /**
   * gets the rgb values of each pixel and adds it to the 2D array of pixels of this image.
   */
  @Override
  public void loadImage() {
    this.pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // changed j and i here as well
        int rgb = this.image.getRGB(j, i);
        Color color = new Color(rgb);
        this.pixels[i][j] = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
  }

  /**
   * Creates a new copy of the given Image, that is separate from this image.
   *
   * @return A new image which is a copy of this image.
   */
  @Override
  public Image createCopy() {
    AbstractImage newImage = new ImageOther(this.imageReader);
    Pixel[][] test = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel current = this.pixels[i][j];
        test[i][j] = current;
      }
    }
    newImage.pixels = test;
    return newImage;
  }
}
