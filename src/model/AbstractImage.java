package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class representing an image of any type. All images represent pixels in the same
 * manner, and the operations remain the same across all types of images.
 */
public abstract class AbstractImage implements Image {
  protected int width;
  protected int height;
  protected Pixel[][] pixels;
  protected int maxValue;
  protected ImageReader imageReader;
  protected StringBuilder imageText;

  private enum Channel { Red, Green, Blue, Intensity }

  private enum Operations {
    GrayScale, RedComponent, GreenComponent, BlueComponent, Sepia, Luma, Intensity, Value,
    Blur, Sharpen
  }

  /**
   * Constructor for an abstract image class.
   *
   * @param reader represents an ImageReader, that contains all the read data from an image file.
   */
  public AbstractImage(ImageReader reader) {
    if (reader == null) {
      throw new IllegalArgumentException("Reader cannot be null.");
    }
    this.imageReader = reader;
    width = reader.getImageWidth();
    height = reader.getImageHeight();
    pixels = new Pixel[height][width];
    maxValue = reader.getMaxValue();
    this.imageText = new StringBuilder();
  }

  /**
   * Secondary Image constructor for testing purposes.
   *
   * @param width    the width of an image.
   * @param height   the height of an image.
   * @param maxValue the maximum value a pixel can have in an image.
   * @param pixels   an array of pixels contained in the image.
   */
  public AbstractImage(int width, int height, int maxValue, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  /**
   * Gets the width.
   *
   * @return the width of this image as an integer.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height.
   *
   * @return the height of this image as an integer.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Create a gray scale image, which is based off the red component in the rgb value of every
   * pixel in this image.
   */
  public void createGrayScaleRed() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[i][j].convertGrayScaleRed();
      }
    }
  }

  /**
   * Create a gray scale image, which is based off the green component in the rgb value of every
   * pixel in this image.
   */
  public void createGrayScaleGreen() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[i][j].convertGrayScaleGreen();
      }
    }
  }

  /**
   * Create a gray scale image, which is based off the blue component in the rgb value of every
   * pixel in this image.
   */
  public void createGrayScaleBlue() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[i][j].convertGrayScaleBlue();
      }
    }
  }

  /**
   * Create a gray scale image, which is based off the value of every pixel in this image.
   */
  public void visualiseImageValue() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(pixels[i][j].calculateValue());
      }
    }
  }

  /**
   * Create a gray scale image, which is based off the intensity of every pixel in this image.
   */
  public void visualiseImageIntensity() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(pixels[i][j].calculateIntensity());
      }
    }
  }

  /**
   * Create a gray scale image, which is based off the luma of every pixel in this image.
   */
  public void visualiseImageLuma() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(pixels[i][j].calculateLuma());
      }
    }
  }

  /**
   * Create a vertically flipped image of this image.
   */
  public void verticalFlip() {
    Pixel[][] pixelArray = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelArray[i][j] = pixels[height - i - 1][j];
      }
    }
    this.pixels = pixelArray;
  }

  /**
   * Create a horizontally flipped image of this image.
   */
  public void horizontalFlip() {
    Pixel[][] pixelArray = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelArray[i][j] = pixels[i][width - j - 1];
      }
    }
    this.pixels = pixelArray;
  }


  /**
   * changes the brightness of the image by the specified brightness value.
   *
   * @param brightnessValue the value to change the brightness of the image by (can be negative to.
   *                        darken or positive to brighten).
   */
  public void changeBrightness(int brightnessValue) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[i][j].changeBrightness(brightnessValue);
      }
    }
  }

  /**
   * Create a sepia image, which is based off the value of all the channels in the rgb
   * value of every pixel in this image.
   */
  public void createSepia() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[i][j].convertSepia();
      }
    }
  }


  /**
   * Create a gray scale image, which is based off the value of all the channels in the
   * rgb value of every pixel in this image.
   */
  public void createGrayScale() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[i][j].convertGrayScale();
      }
    }
  }


  /**
   * creates a string builder in the ppm file format containing all the original image's pixels.
   * and data.
   *
   * @return the string builder containing the ppm file data.
   */
  public StringBuilder formatTextPPM() {
    this.imageText.append("P3\n" + "# Created by GIMP version 2.10.20 PNM plug-in\n"
            + width + " " + height + "\n255\n");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String[] rgb = pixels[i][j].toString().split("\n");
        imageText.append(rgb[0] + "\n" + rgb[1] + "\n" + rgb[2] + "\n");
      }
    }
    return imageText;
  }

  /**
   * Translates the given image class to a buffered Image so the image can be written as a file
   * with an extension other than ppm.
   *
   * @return the given image as a buffered image.
   */
  public BufferedImage createImage() {
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // changed width and height here !!!
    for (int i = 0, pos = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++, pos++) {
        Pixel currentPixel = this.pixels[i][j];
        Color c = currentPixel.convertToColor();
        // changed j i order
        output.setRGB(j, i, c.getRGB());
      }
    }
    return output;
  }

  private int getChannelAtPixel(int row, int col, Channel channel) {
    int x;

    if ((row < 0 || row > this.height - 1) || (col < 0 || col > this.width - 1)) {
      return 0;
    }

    if (channel.equals(Channel.Red)) {
      x = this.pixels[row][col].getRed();
    } else if (channel.equals(Channel.Blue)) {
      x = this.pixels[row][col].getBlue();
    } else if (channel.equals(Channel.Intensity)) {
      x = this.pixels[row][col].calculateIntensity();
    } else {
      x = this.pixels[row][col].getGreen();
    }
    return x;
  }

  /**
   * Blurs an image.
   */
  public void blurImage() {
    model.Kernel k = new model.Kernel();
    k.setKernelBlur();
    applyKernel(k);
  }

  /**
   * Sharpens an image.
   */
  public void sharpenImage() {
    model.Kernel k = new model.Kernel();
    k.setKernelSharpen();
    applyKernel(k);
  }

  private void applyKernel(Kernel k) {
    Pixel[][] newPixels = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        newPixels[row][col] = applyKernelToOnePixel(row, col, k);
      }
    }
    this.pixels = newPixels;
  }

  /**
   * A method that provides the array of pixels for testing purposes.
   *
   * @return the array of pixels.
   */
  public Pixel[][] returnPixels() {
    return this.pixels;
  }

  // all code for the implementation of the histogram

  /**
   * Gets the width and height of this image and multiplies them, obtaining the product. This
   * implementation exists solely for the purpose of the histogram.
   *
   * @return the product of the width and the height of the image.
   */
  public int productWidthHeight() {
    return this.width * this.height;
  }

  /**
   * Obtains the frequency of appearance for each pixel's red values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the red component
   *         of every pixel and their frequency.
   */
  public Map<Integer, Integer> getRedHistogram() {
    return getHistogramValueFrequency(Channel.Red);
  }

  /**
   * Obtains the frequency of appearance for each pixel's green values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the green component
   *         of every pixel and their frequency.
   */
  public Map<Integer, Integer> getGreenHistogram() {
    return getHistogramValueFrequency(Channel.Green);
  }

  /**
   * Obtains the frequency of appearance for each pixel's green values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the green component
   *         of every pixel and their frequency.
   */
  public Map<Integer, Integer> getBlueHistogram() {
    return getHistogramValueFrequency(Channel.Blue);
  }

  /**
   * Obtains the frequency of appearance for each pixel's intensity values.
   *
   * @return A hashmap of type Integer,Integer containing all the values of the intensity component
   *         of every pixel and their frequency.
   */
  public Map<Integer, Integer> getIntensityHistogram() {
    return getHistogramValueFrequency(Channel.Intensity);
  }

  private Map<Integer, Integer> getHistogramValueFrequency(Channel channel) {
    Map<Integer, Integer> histogram = new HashMap<Integer, Integer>();
    emptyHistogram(histogram);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Integer alpha = getChannelAtPixel(row, col, channel);
        Integer oldValue = histogram.get(alpha);
        histogram.replace(alpha, oldValue + 1);
      }
    }
    return histogram;
  }

  private void emptyHistogram(Map<Integer, Integer> histogram) {
    for (int i = 0; i < 256; i++) {
      histogram.put(i, 0);
    }
  }

  // all operations required to support downscaling an image

  /**
   * Downsizes this image to a smaller version of itself based on the new parameters given.
   *
   * @param newWidth  The new width of the downSized image.
   * @param newHeight The new Height of the downSized image.
   */
  public void downSize(int newWidth, int newHeight) {
    if (newWidth > this.width || newHeight > this.height) {
      throw new IllegalArgumentException("Please provide a smaller width and height " +
              "than the current" +
              "width and height");
    }

    Pixel[][] newPixels = new Pixel[newHeight + 1][newWidth + 1];

    for (int row = 0; row < newHeight + 1; row++) {
      for (int col = 0; col < newWidth + 1; col++) {

        Double[] newCoordinates = newCoordinatesInLarger(row, col, newWidth, newHeight);

        int newRed = computeNewPixel(newCoordinates[0], newCoordinates[1], Channel.Red);
        int newGreen = computeNewPixel(newCoordinates[0], newCoordinates[1], Channel.Green);
        int newBlue = computeNewPixel(newCoordinates[0], newCoordinates[1], Channel.Blue);

        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);

        newPixels[row][col] = newPixel;
      }
    }

    this.pixels = newPixels;
    this.height = newHeight + 1;
    this.width = newWidth + 1;
  }

  private int computeNewPixel(double x, double y, Channel channel) {
    DecimalFormat format = new DecimalFormat("0.00");

    int upperRoundedX;
    if (format.format(Math.ceil(x)).equals(format.format(x))) {
      upperRoundedX = (int) Math.ceil(x) + 1;
    } else {
      upperRoundedX = (int) Math.ceil(x);
    }

    int lowerRoundedX = (int) Math.floor(x);

    int upperRoundedY;
    if (format.format(Math.ceil(y)).equals(format.format(y))) {
      upperRoundedY = (int) Math.ceil(y) + 1;
    } else {
      upperRoundedY = (int) Math.ceil(y);
    }

    int lowerRoundedY = (int) Math.floor(y);

    double m = (getChannelAtPixel(upperRoundedX, lowerRoundedY, channel) * (x - lowerRoundedX))
            + (getChannelAtPixel(lowerRoundedX, lowerRoundedY, channel) * (upperRoundedX - x));

    double n = (getChannelAtPixel(upperRoundedX, upperRoundedY, channel) * (x - lowerRoundedX))
            + (getChannelAtPixel(lowerRoundedX, upperRoundedY, channel) * (upperRoundedX - x));

    double p = (n * (y - lowerRoundedY)) + (m * (upperRoundedY - y));

    return (int) p;
  }

  private Double[] newCoordinatesInLarger(int x, int y, int newWidth, int newHeight) {
    double smallerWidthToXRatio = (double) (x) / (double) (newHeight);
    double smallerHeightToYRatio = (double) (y) / (double) (newWidth);

    // obtaining the new co-ordinates using mathematical manipulation.
    double newXCoordinate = smallerWidthToXRatio * (double) this.height;
    double newYCoordinate = smallerHeightToYRatio * (double) this.width;

    // adding these new Co-ordinate values to the double array and returning it.
    Double[] newCoordinate = new Double[2];
    newCoordinate[0] = newXCoordinate;
    newCoordinate[1] = newYCoordinate;

    // returning the new Array of doubles.
    return newCoordinate;
  }

  // all operations required to support partial image manipulation

  /**
   * Applies the sepia effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipSepia(Image clippingMask) {
    applyClipped(clippingMask, Operations.Sepia);
  }

  /**
   * Applies the grayscale effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipGrayScale(Image clippingMask) {
    applyClipped(clippingMask, Operations.GrayScale);
  }

  /**
   * Applies the blur effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipBlur(Image clippingMask) {
    applyClipped(clippingMask, Operations.Blur);
  }

  /**
   * Applies the sharpening effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipSharpen(Image clippingMask) {
    applyClipped(clippingMask, Operations.Sharpen);
  }

  /**
   * Applies the Luma effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipLuma(Image clippingMask) {
    applyClipped(clippingMask, Operations.Luma);
  }

  /**
   * Applies the Intensity effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipIntensity(Image clippingMask) {
    applyClipped(clippingMask, Operations.Intensity);
  }

  /**
   * Applies the Value effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipValue(Image clippingMask) {
    applyClipped(clippingMask, Operations.Value);
  }

  /**
   * Applies the red component effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipRedComponent(Image clippingMask) {
    applyClipped(clippingMask, Operations.RedComponent);
  }

  /**
   * Applies the green component effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipGreenComponent(Image clippingMask) {
    applyClipped(clippingMask, Operations.GreenComponent);
  }

  /**
   * Applies the blue component effect to this image based on a black and white clipping mask.
   * The black areas in the mask are mapped onto the current image and the corresponding
   * pixels in this image are transformed.
   *
   * @param clippingMask a black and white image of the same size as this image.
   */
  public void clipBlueComponent(Image clippingMask) {
    applyClipped(clippingMask, Operations.BlueComponent);
  }

  private void applyClipped(Image clippingMask, Operations operation) {

    if (this.height != clippingMask.getHeight()) {
      throw new IllegalArgumentException("The clipping mask does not have the same dimensions" +
              "as this image");
    }

    if (this.width != clippingMask.getWidth()) {
      throw new IllegalArgumentException("The clipping mask does not have the same dimensions" +
              "as this image");
    }

    Pixel[][] clippingPixels = clippingMask.returnPixels();
    Pixel black = new Pixel(0);

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {

        if (clippingPixels[row][col].equals(black)) {
          Pixel currentPixel = this.pixels[row][col];

          this.pixels[row][col] = apply(operation, currentPixel, row, col);
        }
      }
    }
  }

  private Pixel apply(Operations operation, Pixel currentPixel, int row, int col) {
    Pixel alphaPixel = new Pixel(0);
    if (operation.equals(Operations.Intensity)) {
      return new Pixel(currentPixel.calculateIntensity());
    }

    if (operation.equals(Operations.Value)) {
      return new Pixel(currentPixel.calculateValue());
    }

    if (operation.equals(Operations.Luma)) {
      return new Pixel(currentPixel.calculateLuma());
    }

    if (operation.equals(Operations.RedComponent)) {
      return currentPixel.convertGrayScaleRed();
    }

    if (operation.equals(Operations.GreenComponent)) {
      return currentPixel.convertGrayScaleGreen();
    }

    if (operation.equals(Operations.BlueComponent)) {
      return currentPixel.convertGrayScaleBlue();
    }

    if (operation.equals(Operations.GrayScale)) {
      return currentPixel.convertGrayScale();
    }

    if (operation.equals(Operations.Sepia)) {
      return currentPixel.convertSepia();
    }

    if (operation.equals(Operations.Blur)) {
      Kernel k = new Kernel();
      k.setKernelBlur();
      return applyKernelToOnePixel(row, col, k);
    }

    if (operation.equals(Operations.Sharpen)) {
      Kernel k = new Kernel();
      k.setKernelSharpen();
      return applyKernelToOnePixel(row, col, k);
    }

    return alphaPixel;
  }

  private Pixel applyKernelToOnePixel(int row, int col, Kernel k) {
    double sumRed = 0.0;
    double sumBlue = 0.0;
    double sumGreen = 0.0;
    int correspondingRow = row - ((k.getKernelSize()) / 2);
    int correspondingCol = col - ((k.getKernelSize()) / 2);

    for (int kernelRow = 0; kernelRow < k.getKernelSize(); kernelRow++) {
      for (int kernelCol = 0; kernelCol < k.getKernelSize(); kernelCol++) {

        sumRed += getChannelAtPixel(correspondingRow, correspondingCol, Channel.Red)
                * k.getKernelData()[kernelRow][kernelCol];
        sumGreen += getChannelAtPixel(correspondingRow, correspondingCol, Channel.Green)
                * k.getKernelData()[kernelRow][kernelCol];
        sumBlue += getChannelAtPixel(correspondingRow, correspondingCol, Channel.Blue)
                * k.getKernelData()[kernelRow][kernelCol];
        correspondingCol++;
      }
      correspondingCol = col - (k.getKernelSize() / 2);
      correspondingRow++;
    }
    if (sumRed > 255) {
      sumRed = 255;
    }
    if (sumGreen > 255) {
      sumGreen = 255;
    }
    if (sumBlue > 255) {
      sumBlue = 255;
    }
    if (sumRed < 0) {
      sumRed = 0;
    }
    if (sumGreen < 0) {
      sumGreen = 0;
    }
    if (sumBlue < 0) {
      sumBlue = 0;
    }
    return new Pixel((int) sumRed, (int) sumGreen, (int) sumBlue);
  }
}