package commands;

import java.util.HashMap;
import java.util.Scanner;

import model.Image;
import view.ImageView;

/**
 * An abstract command class for all partial commands.
 */
public abstract class Partial extends AbstractCommand {

  @Override
  public void execute(Scanner sc, ImageView view, HashMap<String, Image> images) {
    // clear the content of the arrayList
    clearContentString();
    // the name of this image, the name of the clipping mask and the name to be called
    addString(sc, view);
    String nameInHashMap = this.relevantStrings.get(0);
    addString(sc, view);
    String nameInHashMapClipping = this.relevantStrings.get(1);

    if (!images.containsKey(nameInHashMapClipping)) {
      throw new IllegalArgumentException("Please load the clipping mask first");
    }


    // checking if the Image exists in the HashMap.
    if (images.containsKey(nameInHashMap)) {
      // create a copy
      Image copy = images.get(nameInHashMap).createCopy();
      Image clipping = images.get(nameInHashMapClipping);
      // the name the Image will now be called in the HashMap.

      // try executing the command, putting the new image in the HashMap and rendering a success
      // message. If any of these fail, send a message to the user indicating so.
      try {
        apply(copy, clipping);
        addString(sc, view);
        String nameToBeCalled = this.relevantStrings.get(2);
        images.put(nameToBeCalled, copy);
        this.sendMessage(view, message() + "\n");
      } catch (Exception e) {
        this.sendMessage(view, e.getMessage());
      }
    } else {
      this.sendMessage(view, "That Image doesn't exist, please load / use an existing" +
              "Image.");
    }
  }

  protected abstract void apply(Image image, Image clipping);

  protected abstract String message();

}
