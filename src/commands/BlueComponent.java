package commands;

import model.Image;

/**
 * Represents a command to convert an image to greyscale based on its blue component.
 */
public class BlueComponent extends SimpleCommand {

  @Override
  protected void executeCommand(Image model) {
    model.createGrayScaleBlue();
  }

  @Override
  protected String message() {
    return "The Image has been successfully converted to its blue grayscale version.";
  }
}
