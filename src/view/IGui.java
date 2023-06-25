package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * represents an interface for the graphical user interface of an image processing program.
 * Supports.
 * functions such as opening an image (ppm, png, bmp or jpg format), editing a file using commands.
 * such as brighten, converting to sepia or grayscale, etc, displaying a histogram of the various.
 * components of the current image and saving the current image.
 */
public interface IGui {
  /**
   * sets the action listener for every button in the GUI.
   *
   * @param listener the action listener of every button in the GUI.
   */
  void setListener(ActionListener listener);

  /**
   * displays the GUI; i.e. makes its components visible.
   */
  void display();

  /**
   * initializes the components and interface of the GUI.
   */
  void initialize();

  /**
   * initializes the button for opening a file in the GUI, and initializes other components of the.
   * GUI upon opening a valid file.
   */
  void initializeOpenFileButton();

  /**
   * displays an image in the image panel in the current GUI.
   *
   * @param imageName the path of the image to be displayed.
   */
  void displayImage(String imageName);

  /**
   * displays a buffered image in the image panel in the GUI.
   *
   * @param image the buffered image to be displayed.
   */
  void displayBufferedImage(BufferedImage image);

  /**
   * initializes the histogram in the GUI when an image is first displayed.
   *
   * @param filepath the path of the file whose histogram needs to be displayed.
   */
  void initializeHistogram(String filepath);

  /**
   * is used to update the histogram when the current image is changed or edited.
   *
   * @param h the histogram of the new/edited image.
   */
  void displayHistogram(Histogram h);

  /**
   * adds the button for saving a file to the GUI.
   */
  void initializeSaveFileButton();

  /**
   * opens a dialog box to save a file upon pressing the 'save' button in the GUI.
   */
  void saveFileButtonOnPress();

  /**
   * adds the dropdown of commands/transformations for editing an image to the GUI.
   */
  void initializeActionsDropdown();

  /**
   * displays a dialog box for the user to input how much they want to brighten/darken the image by.
   * (value needs to be between -255 and 255, included), and returns the user input as a string.
   *
   * @return the user input (value they want to brighten/darken the image by) as a string.
   */
  String brightenImageDialog();

  /**
   * displays a dialog box for the user to enter how much they want to downsize the image by.
   * (width of the new image).
   *
   * @return returns the user input (width of the new image) as a string.
   */
  String downSizeDialogWidth();

  /**
   * displays a dialog box for the user to enter how much they want to downsize the image by.
   * (height of the new image).
   *
   * @return returns the user input (height of the new image) as a string.
   */
  String downSizeDialogHeight();

  /**
   * displays an error message whenever the user provides an invalid input.
   *
   * @param error the error message to be displayed.
   */
  void displayErrorMessage(String error);

  /**
   * returns the filepath of the current image.
   *
   * @return the filepath of the current image.
   */
  String getImageName();

  /**
   * returns the name the user wants to save the image as.
   *
   * @return the name the user wants to save the image as.
   */
  String getSaveImageName();

}
