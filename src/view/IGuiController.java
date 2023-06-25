package view;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * interface that represents a controller for the graphical user interface. Performs all the.
 * operations on the current image based on the inputs given from the user.
 */
public interface IGuiController {
  /**
   * determines what is supposed to be happening based on the user's actions in the GUI.
   *
   * @param e the event to be processed.
   */
  void actionPerformed(ActionEvent e);

  /**
   * gets the buffered image (the image the user is currently working on).
   *
   * @return returns the buffered image of this class.
   */
  BufferedImage getImage();

  /**
   * sets the GUI to be visible upon calling the controller.
   */
  void start();
}
