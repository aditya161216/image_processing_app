package model;

/**
 * Represents a ppm Image.
 */
public class ImagePPM extends AbstractImage {


  /**
   * Constructor for the image class.
   *
   * @param reader represents an ImageReader, that contains all the read data from a ppm file.
   */
  public ImagePPM(ImageReader reader) {
    super(reader);
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
  public ImagePPM(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue, pixels);
    loadImage();
  }

  /**
   * gets the rgb values of each pixel and adds it to the 2D array of pixels of this image.
   */
  public void loadImage() {
    String[] array = this.imageReader.provideRGBAsString();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String[] redGreenBlue = array[i * width + j].split(",");
        Pixel currentPixel = new Pixel(Integer.parseInt(redGreenBlue[0])
                , Integer.parseInt(redGreenBlue[1]), Integer.parseInt(redGreenBlue[2]));
        pixels[i][j] = currentPixel;
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
    AbstractImage newImage = new ImagePPM(this.imageReader);
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