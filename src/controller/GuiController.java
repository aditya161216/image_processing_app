package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;

import model.Image;
import model.ImageOther;
import model.ImagePPM;
import model.ImageReader;
import model.ImageReaderGen;
import model.ImageWriter;
import model.ImageWriterGen;
import view.Gui;
import view.Histogram;
import view.IGuiController;

/**
 * represents a controller for the graphical user interface. Performs all the operations on the.
 * current image based on the inputs given from the user.
 */
public class GuiController implements ActionListener, IGuiController {
  private Image model;
  private Gui view;
  private BufferedImage image;

  /**
   * the default constructor for the controller for the graphical user interface. Sets the model.
   * and view (GUI) to the passed in model and view, and sets the action listeners for all.
   * buttons to this controller.
   *
   * @param m the model which is set to be this controller's model.
   * @param v the view (GUI) which is set to be this controller's view (GUI).
   */
  public GuiController(Image m, Gui v) {
    this.model = m;
    this.view = v;
    view.setListener(this);
  }

  /**
   * performs the various transformations on the current image based on the user's chosen.
   * transformations from the dropdown. Made public for testing purposes.
   *
   * @param transformation the transformation chosen by the user.
   */
  public void chooseTransformation(String transformation) {
    if (transformation.equals("Vertical flip")) {
      this.model.verticalFlip();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Horizontal flip")) {
      this.model.horizontalFlip();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Brighten")) {
      boolean brightnessValid = false;
      while (!brightnessValid) {
        try {
          this.model.changeBrightness(Integer.parseInt(this.view.brightenImageDialog()));
          this.view.displayHistogram(new Histogram(this.model));
          this.image = this.model.createImage();
          this.view.displayBufferedImage(this.image);
          brightnessValid = true;
        } catch (Exception e) {
          this.view.displayErrorMessage(e.getMessage());
        }
      }
    } else if (transformation.equals("Grayscale value")) {
      this.model.visualiseImageValue();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Grayscale red")) {
      this.model.createGrayScaleRed();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Grayscale green")) {
      this.model.createGrayScaleGreen();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Grayscale blue")) {
      this.model.createGrayScaleBlue();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Intensity")) {
      this.model.visualiseImageIntensity();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Luma")) {
      this.model.visualiseImageLuma();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Sepia")) {
      this.model.createSepia();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Grayscale")) {
      this.model.createGrayScale();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Blur")) {
      this.model.blurImage();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Sharpen")) {
      this.model.sharpenImage();
      this.view.displayHistogram(new Histogram(this.model));
      this.image = this.model.createImage();
      this.view.displayBufferedImage(this.image);
    } else if (transformation.equals("Down size")) {
      boolean widthValid = false;
      while (!widthValid) {
        try {
          this.model.downSize(Integer.parseInt(this.view.downSizeDialogWidth()),
                  Integer.parseInt(this.view.downSizeDialogHeight()));
          this.view.displayHistogram(new Histogram(this.model));
          this.image = this.model.createImage();
          this.view.displayBufferedImage(this.image);
          widthValid = true;
        } catch (Exception e) {
          this.view.displayErrorMessage(e.getMessage());
        }
      }
    }
  }


  /**
   * determines what is supposed to be happening based on the user's actions in the GUI.
   *
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file button": {
        boolean correctFile = false;
        while (!correctFile) {
          try {
            this.view.initializeOpenFileButton();
            String[] stringList = this.view.getImageName().split("/");
            String[] stringList2 = stringList[stringList.length - 1].split("\\.");

            if (stringList2[stringList2.length - 1].equals("ppm")) {
              ImageReader reader = new ImageReaderGen();
              reader.readPPM(this.view.getImageName());
              this.model = new ImagePPM(reader);
              this.model.loadImage();
              this.image = this.model.createImage();
              this.view.displayBufferedImage(this.image);
            }
            if (stringList2[stringList2.length - 1].equals("png")
                    || stringList2[stringList2.length - 1].equals("bmp")
                    || stringList2[stringList2.length - 1].equals("jpg")) {
              ImageReader reader = new ImageReaderGen();
              reader.readOther(this.view.getImageName());
              this.model = new ImageOther(reader);
              this.model.loadImage();
              this.image = this.model.createImage();
            }
            correctFile = true;
          } catch (Exception e2) {
            this.view.displayErrorMessage("Cannot open this file format! Please " +
                    "open a file of type ppm, png, bmp or jpg.");
          }
        }
      }
      break;

      case "Transform":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          this.chooseTransformation((String) box.getSelectedItem());
        }
        break;

      case "Save file": {
        boolean correctExtension = false;
        this.view.saveFileButtonOnPress();
        while (!correctExtension) {
          try {
            ImageWriter writer = new ImageWriterGen(this.model);
            String extension = "";
            String[] splitDirectory;
            String[] splitDirectory2;
            try {
              splitDirectory = this.view.getImageName().split("/");
              splitDirectory2 = splitDirectory[splitDirectory.length - 1].split("\\.");
              extension = splitDirectory2[splitDirectory2.length - 1];
            } catch (Exception e2) {
              throw new IllegalArgumentException("Cannot save this file format! Please " +
                      "save a file of type ppm, png, bmp or jpg.");
            }
            if (!(extension.equals("ppm") || extension.equals("png")
                    || extension.equals("bmp") || extension.equals("jpg"))) {
              throw new IllegalArgumentException("Cannot save this file format! Please " +
                      "save a file of type ppm, png, bmp or jpg.");
            }

            if (extension.equals("ppm")) {
              writer.writeImagePPM(view.getSaveImageName());
              correctExtension = true;
            } else {
              writer.writeImageOther(view.getSaveImageName(), extension);
              correctExtension = true;
            }
          } catch (Exception e2) {
            this.view.displayErrorMessage(e2.getMessage());
            this.view.saveFileButtonOnPress();
          }
        }
      }
      break;
      default:
        this.view.display();
    }
  }

  /**
   * gets the buffered image (the image the user is currently working on).
   *
   * @return returns the buffered image of this class.
   */
  public BufferedImage getImage() {
    return this.image;
  }

  /**
   * sets the GUI to be visible upon calling the controller.
   */
  public void start() {
    this.view.display();
  }
}
