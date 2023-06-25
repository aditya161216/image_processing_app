package commands;

import java.util.HashMap;
import java.util.Scanner;

import model.ImageOther;
import model.ImagePPM;
import model.Image;
import model.ImageReader;
import model.ImageReaderGen;
import view.ImageView;

/**
 * represents a class for the load command, which is a complex command. Loads the image and
 * initialises a new instance of Image.
 */
public class Load extends AbstractCommand {

  /**
   * executes the specified command for the user, which is loading the image in this case.
   *
   * @param sc     the scanner of the current controller.
   * @param view   the current view of the image.
   * @param images the hashmap of images currently stored.
   */
  @Override
  public void execute(Scanner sc, ImageView view, HashMap<String, Image> images) {
    this.clearContentString();
    // obtain the data - the path name and the name in the HashMap.
    this.addString(sc, view);
    String path = this.relevantStrings.get(0);
    this.addString(sc, view);
    String imageName = this.relevantStrings.get(1);

    ImageReader reader = new ImageReaderGen();

    String[] extensionArray = path.split("\\.");

    if (extensionArray[1].equals("ppm")) {
      try {
        reader.readPPM(path);
        Image localImage = new ImagePPM(reader);
        images.put(imageName, localImage);
        this.sendMessage(view, "The image has been successfully loaded\n");
      } catch (Exception e) {
        this.sendMessage(view, e.getMessage() + "\n");
      }

    } else {
      try {
        reader.readOther(path);
        Image localImage = new ImageOther(reader);
        images.put(imageName, localImage);
        this.sendMessage(view, "The image has been successfully loaded\n");
      } catch (Exception e) {
        this.sendMessage(view, e.getMessage() + "\n");
      }
    }
  }
}
