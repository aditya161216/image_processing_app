package view;

import java.io.IOException;

/**
 * Represents the view of an image. Used for rendering messages to the console.
 */
public class ImageView implements ViewInterface {
  public Appendable out;

  /**
   * Constructor for ImageView. Initialises the appendable to System.out.
   */
  public ImageView() {
    this.out = System.out;
  }

  /**
   * Constructor for testing purposes.
   *
   * @param out represents an appendable to which all messages will be logged.
   */
  public ImageView(Appendable out) {
    this.out = out;
  }

  /**
   * Appends a string message to the appendable.
   *
   * @param message represents the message to be appended to the appendable.
   * @throws IOException if the message cannot be appended and sent out.
   */
  public void renderMessage(String message) throws IOException {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
