package commands;

import model.Image;

/**
 * Command for a partial sharpen transformation of an image.
 */
public class PartialSharpen extends Partial {
  @Override
  protected void apply(Image image, Image clipping) {
    image.clipSharpen(clipping);
  }

  @Override
  protected String message() {
    return "The image has been partially sharpened";
  }
}
