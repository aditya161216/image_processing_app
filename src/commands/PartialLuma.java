package commands;

import model.Image;

/**
 * Command for a partial luma component transformation of an image.
 */
public class PartialLuma extends Partial {
  @Override
  protected void apply(Image image, Image clipping) {
    image.clipLuma(clipping);
  }

  @Override
  protected String message() {
    return "The image has been partially transformed - luma";
  }
}
