package commands;

import model.Image;

/**
 * Command for a partial blur transformation of an image.
 */
public class PartialBlur extends Partial {
  @Override
  protected void apply(Image image, Image clipping) {
    image.clipSepia(clipping);
  }

  @Override
  protected String message() {
    return "The image has been blurred partially";
  }
}


