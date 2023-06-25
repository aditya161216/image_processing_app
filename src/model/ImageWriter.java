package model;

import java.io.IOException;

/**
 * Represents the operations that should be supported by an image writer.
 */
public interface ImageWriter {

  /**
   * A method that writes a file to the local device as a PPM Image.
   *
   * @param file represents the name and directory of the file.
   * @throws IOException if the file cannot be written.
   */
  void writeImagePPM(String file) throws IOException;

  /**
   * A method that writes a file to the local device as a PPM Image.
   *
   * @param fileName  represents the name of the file including the path it will be written to.
   * @param extension represents the extension of the file. (png, jpg, bmp ...)
   */
  void writeImageOther(String fileName, String extension);
}
