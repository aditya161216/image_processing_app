package commands;

import model.Image;

/**
 * Represents a command to convert an image to horizontally flipped version of itself.
 */
public class HorizontalFlip extends SimpleCommand {

  @Override
  protected void executeCommand(Image model) {
    model.horizontalFlip();
  }


  @Override
  protected String message() {
    return "The Image has been successfully flipped horizontally.";
  }
}
