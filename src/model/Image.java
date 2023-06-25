package model;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Represents the operations that should be supported by any type of Image.
 */
public interface Image {

  /**
   * Gets the width.
   *
   * @return the width of this image as an integer.
   */
  int getWidth();

  /**
   * Gets the height.
   *
   * @return the height of this image as an integer.
   */
  int getHeight();

  /**
   * This operations regards loading an image, instantiating it, details are implementation
   * dependant.
   */
  void loadImage();

  /**
   * Creates a new copy of the given Image, that is separate from this image.
   *
   * @return A new image which is a copy of this image.
   */
  Image createCopy();

  /**
   * Create a gray scale image, which is based off the red component in the rgb value of every
   * pixel in this image.
   */
  void createGrayScaleRed();

  /**
   * Create a gray scale image, which is based off the green component in the rgb value of every
   * pixel in this image.
   */
  void createGrayScaleGreen();

  /**
   * Create a gray scale image, which is based off the blue component in the rgb value of every
   * pixel in this image.
   */
  void createGrayScaleBlue();

  /**
   * Create a gray scale image, which is based off the value of every pixel in this image.
   */
  void visualiseImageValue();

  /**
   * Create a gray scale image, which is based off the intensity of every pixel in this image.
   */
  void visualiseImageIntensity();

  /**
   * Create a gray scale image, which is based off the luma of every pixel in this image.
   */
  void visualiseImageLuma();

  /**
   * Create a vertically flipped image of this image.
   */
  void verticalFlip();

  /**
   * Create a horizontally flipped image of this image.
   */
  void horizontalFlip();

  /**
   * Changes the brightness of the image by the specific brightness value.
   */
  void changeBrightness(int brightnessValue);

  /**
   * creates a string builder in the ppm file format containing all the original image's pixels.
   * and data.
   *
   * @return the string builder containing the ppm file data.
   */
  StringBuilder formatTextPPM();

  /**
   * Translates the given image class to a buffered Image so the image can be written as a file
   * with an extension other than ppm.
   *
   * @return the given image as a buffered image.
   */
  BufferedImage createImage();

  /**
   * Blurs an image.
   */
  void blurImage();

  /**
   * Sharpens an image.
   */
  void sharpenImage();

  /**
   * Creates a grayscale version of this image.
   */
  void createGrayScale();

  /**
   * Creates a sepia version of this image.
   */
  void createSepia();

  /**
   * A method that provides the array of pixels for testing purposes.
   *
   * @return the array of pixels.
   */
  Pixel[][] returnPixels();

  /**
   * Gets the width and height of this image and multiplies them, obtaining the product. This
   * implementation exists solely for the purpose of the histogram.
   *
   * @return the product of the width and the height of the image.
   */
  int productWidthHeight();

  /**
   * Obtains the frequency of appearance for each pixel's red values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the red component
   *         of every pixel and their frequency.
   */
  Map<Integer, Integer> getRedHistogram();

  /**
   * Obtains the frequency of appearance for each pixel's green values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the green component
   *         of every pixel and their frequency.
   */
  Map<Integer, Integer> getGreenHistogram();

  /**
   * Obtains the frequency of appearance for each pixel's green values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the green component
   *         of every pixel and their frequency.
   */
  Map<Integer, Integer> getBlueHistogram();

  /**
   * Obtains the frequency of appearance for each pixel's intensity values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the intensity component
   *         of every pixel and their frequency.
   */
  Map<Integer, Integer> getIntensityHistogram();

  /**
   * Downsizes this image to a smaller version of itself based on the new parameters given.
   *
   * @param newWidth  The new width of the downSized image.
   * @param newHeight The new Height of the downSized image.
   */
  void downSize(int newWidth, int newHeight);

  /**
   * Applies the sepia effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  void clipSepia(Image clippingMask);

  /**
   * Applies the grayscale effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  void clipGrayScale(Image clippingMask);

  /**
   * Applies the blur effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  void clipBlur(Image clippingMask);

  /**
   * Applies the sharpening effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  void clipSharpen(Image clippingMask);

  /**
   * Applies the Luma effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  void clipLuma(Image clippingMask);

  /**
   * Applies the Intensity effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  void clipIntensity(Image clippingMask);

  /**
   * Applies the Value effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipValue(Image clippingMask);

  /**
   * Applies the red component effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipRedComponent(Image clippingMask);

  /**
   * Applies the green component effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipGreenComponent(Image clippingMask);


  /**
   * Applies the blue component effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipBlueComponent(Image clippingMask);

}