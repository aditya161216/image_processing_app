package controller;

import java.util.HashMap;
import java.util.Scanner;

import commands.BlueComponent;
import commands.Blur;
import commands.Brighten;
import commands.Command;
import commands.GrayScale;
import commands.GreenComponent;
import commands.HorizontalFlip;
import commands.IntensityComponent;
import commands.Load;
import commands.LumaComponent;
import commands.PartialBlueComponent;
import commands.PartialBlur;
import commands.PartialGrayScale;
import commands.PartialGreenComponent;
import commands.PartialIntensity;
import commands.PartialLuma;
import commands.PartialRedComponent;
import commands.PartialSepia;
import commands.PartialSharpen;
import commands.PartialValue;
import commands.RedComponent;
import commands.Save;
import commands.Sepia;
import commands.Sharpen;
import commands.ValueComponent;
import commands.VerticalFlip;
import model.Image;
import view.ImageView;

/**
 * Represents the controller that reads inputs from the user and uses the inputs to execute
 * commands.
 */
public class ImageController implements ControllerInterface {

  private HashMap<String, Image> images;
  private ImageView view;
  private Readable input;

  private HashMap<String, Command> knownCommands;

  /**
   * Constructor for the controller.
   *
   * @param view  represents the view that will be used to render messages to the user.
   * @param input represents the input that will be used to decide which commands to execute.
   */
  public ImageController(ImageView view, Readable input) {
    if (view == null || input == null) {
      throw new IllegalArgumentException("Fields cannot be null.");
    }
    this.view = view;
    this.images = new HashMap<>();
    this.input = input;
    this.knownCommands = new HashMap<String, Command>();
    addCommands();
  }

  /**
   * A secondary constructor for testing purposes that allows the user to manipulate the hashmap
   * of images that stores the data that the controller will work on.
   *
   * @param map   the stores hashmap of images that the controller will use to execute operations on
   *              and store new images.
   * @param view  represents the view that will be used to render messages to the user.
   * @param input represents the input that will be used to decide which commands to execute.
   */
  public ImageController(HashMap<String, Image> map, ImageView view, Readable input) {
    if (view == null || input == null) {
      throw new IllegalArgumentException("Fields cannot be null.");
    }
    this.images = map;
    this.view = view;
    this.input = input;
    this.knownCommands = new HashMap<String, Command>();
    addCommands();
  }


  private void addCommands() {
    // load and save
    this.knownCommands.put("load", new Load());
    this.knownCommands.put("save", new Save());

    // brighten
    this.knownCommands.put("brighten", new Brighten());

    // components

    // brightness
    this.knownCommands.put("intensity-component", new IntensityComponent());
    this.knownCommands.put("luma-component", new LumaComponent());
    this.knownCommands.put("value-component", new ValueComponent());

    // channels
    this.knownCommands.put("red-component", new RedComponent());
    this.knownCommands.put("green-component", new GreenComponent());
    this.knownCommands.put("blue-component", new BlueComponent());

    // flips
    this.knownCommands.put("vertical-flip", new VerticalFlip());
    this.knownCommands.put("horizontal-flip", new HorizontalFlip());

    //sharpen and blur
    this.knownCommands.put("sharpen", new Sharpen());
    this.knownCommands.put("blur", new Blur());

    // sepia and grayscale
    this.knownCommands.put("sepia", new Sepia());
    this.knownCommands.put("grayscale", new GrayScale());


    // partial commands
    this.knownCommands.put("partialSepia", new PartialSepia());
    this.knownCommands.put("partialGrayscale", new PartialGrayScale());
    this.knownCommands.put("partialBlur", new PartialBlur());
    this.knownCommands.put("partialSharpen", new PartialSharpen());

    this.knownCommands.put("partialRedComponent", new PartialRedComponent());
    this.knownCommands.put("partialGreenComponent", new PartialGreenComponent());
    this.knownCommands.put("partialBlueComponent", new PartialBlueComponent());

    this.knownCommands.put("partialIntensity", new PartialIntensity());
    this.knownCommands.put("partialValue", new PartialValue());
    this.knownCommands.put("partialLuma", new PartialLuma());

  }


  /**
   * Runs the controller. The controller will now execute commands based on inputs when running.
   */
  public void runProgram() {
    Scanner sc = new Scanner(this.input);

    while (sc.hasNext()) {
      String userInput = "";

      try {
        userInput = sc.next();
      } catch (Exception e) {
        System.out.println("The input could not be read");
      }

      // if the game is quit, quit it.
      if (userInput.equalsIgnoreCase("q")
              || userInput.equalsIgnoreCase("quit")) {
        break;
      }

      Command command = knownCommands.getOrDefault(userInput, null);
      if (command == null) {
        this.sendMessage("Invalid command, please enter again: ");
      } else {
        executeCommand(command, sc);
      }
    }
    sendMessage("Program has been quit.");
  }

  /**
   * Sends a message to view. If the message was unable to be sent relays this information
   * to the user.
   *
   * @param message the message to be sent.
   */
  public void sendMessage(String message) {
    try {
      this.view.renderMessage(message);
    } catch (Exception e) {
      System.out.println("A message was unable to be rendered");
    }
  }

  private void executeCommand(Command command, Scanner sc) {
    try {
      command.execute(sc, this.view, this.images);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}