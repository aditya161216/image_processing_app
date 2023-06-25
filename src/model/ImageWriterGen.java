package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a general Image Writer class that can write new files.
 */
public class ImageWriterGen implements ImageWriter {
  private Image image;

  /**
   * Constructor for an Image Writer.
   *
   * @param image represents the image to be written to as a file.
   */
  public ImageWriterGen(Image image) {
    this.image = image;
  }

  /**
   * Writes a file as a type other than ppm.
   *
   * @param fileName  represents the name of the file including the path it will be written to.
   * @param extension represents the extension of the file. (png, jpg, bmp ...)
   */
  public void writeImageOther(String fileName, String extension) {
    File file = new File(fileName);
    try {
      ImageIO.write(this.image.createImage(), extension, file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * A method that writes a ppm file.
   *
   * @param file represents the name and directory of the file.
   * @throws IOException if the file cannot be written.
   */
  public void writeImagePPM(String file) throws IOException {
    FileOutputStream f = new FileOutputStream(file);
    f.write(new String(this.image.formatTextPPM()).getBytes());
    f.close();
  }
}
