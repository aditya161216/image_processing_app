package view;

import java.io.IOException;

/**
 * Represents a list of operations that any implementation of view should support.
 */
public interface ViewInterface {

  /**
   * Appends a string message to the appendable.
   *
   * @param message represents the message to be appended to the appendable.
   * @throws IOException if the message cannot be appended and sent out.
   */
  public void renderMessage(String message) throws IOException;
}
