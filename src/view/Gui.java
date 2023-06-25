package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import model.Image;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import model.ImageOther;
import model.ImagePPM;
import model.ImageReader;
import model.ImageReaderGen;

/**
 * represents a class for the graphical user interface of an image processing program. Supports.
 * functions such as opening an image (ppm, png, bmp or jpg format), editing a file using commands.
 * such as brighten, converting to sepia or grayscale, etc, displaying a histogram of the various.
 * components of the current image and saving the current image.
 */
public class Gui extends JFrame implements IGui {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel fileOpenDisplay;
  private JPanel dialogBoxesPanel;
  private JPanel fileopenPanel;
  private JLabel fileSaveDisplay;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JPanel imagePanel;
  private JPanel histogramPanel;
  private JLabel comboboxDisplay;
  private JComboBox<String> combobox;
  private String imageName;
  private String saveImageName;

  /**
   * represents the default constructor for the gui of the GUI class, and initializes all the.
   * panels utilized in the GUI.
   */
  public Gui() {
    combobox = new JComboBox<String>();
    combobox.setActionCommand("Transform");
    initialize();
    imagePanel = new JPanel();
    dialogBoxesPanel = new JPanel();
    fileopenPanel = new JPanel();
    histogramPanel = new JPanel();
  }

  /**
   * sets the action listener for every button in the GUI.
   *
   * @param listener the action listener of every button in the GUI.
   */
  public void setListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    combobox.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
    this.mainScrollPane = mainScrollPane;
    this.fileOpenDisplay = fileOpenDisplay;
    this.fileSaveDisplay = fileSaveDisplay;
  }

  /**
   * displays the GUI; i.e. makes its components visible.
   */
  public void display() {
    setVisible(true);
  }


  /**
   * initializes the components and interface of the GUI.
   */
  public void initialize() {
    setTitle("Image processing program");
    setSize(1000, 1000);
    setLocationRelativeTo(null);
    setResizable(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane, BorderLayout.CENTER);

    // sets the file open button
    fileOpenButton = new JButton("Open file");
    fileOpenButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    fileOpenButton.setActionCommand("Open file button");
    mainPanel.add(fileOpenButton);

    // sets the file save button
    fileSaveButton = new JButton("Save");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.setAlignmentY(Component.CENTER_ALIGNMENT);

    // sets the dropdown of commands
    comboboxDisplay = new JLabel();
    String[] options = {"<none>", "Vertical flip", "Horizontal flip", "Brighten", "Grayscale value",
      "Grayscale red", "Grayscale green", "Grayscale blue", "Intensity", "Luma", "Sepia",
      "Grayscale", "Blur", "Sharpen", "Down size"};

    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }

    // can be seen
    setVisible(true);
  }

  /**
   * initializes the button for opening a file in the GUI, and initializes other components of the.
   * GUI upon opening a valid file.
   */
  public void initializeOpenFileButton() {
    // panel around the file open button
    fileopenPanel.setLayout(new FlowLayout());
    mainPanel.add(fileopenPanel);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel();
    fileopenPanel.add(fileOpenDisplay);
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "PPM, PNG, BMP & JPG Images", "ppm", "png", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay = new JLabel();
      fileopenPanel.add(fileOpenDisplay);
      this.initializeSaveFileButton();
      this.initializeActionsDropdown();
      this.initializeHistogram(f.getAbsolutePath());
      this.displayImage(f.getAbsolutePath());
      this.imageName = f.getAbsolutePath();
      revalidate();
    }
  }

  /**
   * displays an image in the image panel in the current GUI.
   *
   * @param imageName the path of the image to be displayed.
   */
  public void displayImage(String imageName) {
    imagePanel.removeAll();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current image"));
    mainPanel.add(imagePanel);

    String image = imageName;
    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(image));
    imagePanel.add(imageLabel);
  }

  /**
   * displays a buffered image in the image panel in the GUI.
   *
   * @param image the buffered image to be displayed.
   */
  public void displayBufferedImage(BufferedImage image) {
    imagePanel.removeAll();
    BufferedImage currentImage = image;
    JLabel editedImageLabel = new JLabel();
    editedImageLabel.setIcon(new ImageIcon(currentImage));
    imagePanel.add(editedImageLabel);
    mainPanel.add(imagePanel);
    revalidate();
  }

  /**
   * initializes the histogram in the GUI when an image is first displayed.
   *
   * @param filepath the path of the file whose histogram needs to be displayed.
   */
  public void initializeHistogram(String filepath) {
    mainPanel.remove(histogramPanel);
    String[] stringList = filepath.split("/");
    String[] stringList2 = stringList[stringList.length - 1].split("\\.");
    Image image;
    if (stringList2[stringList2.length - 1].equalsIgnoreCase("ppm")) {
      ImageReader reader = new ImageReaderGen();
      reader.readPPM(filepath);
      image = new ImagePPM(reader);
    } else {
      ImageReader reader = new ImageReaderGen();
      reader.readOther(filepath);
      image = new ImageOther(reader);
    }
    this.histogramPanel = new Histogram(image);
    this.histogramPanel.setPreferredSize(new Dimension(120, 120));
    histogramPanel.removeAll();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Graph of Values " +
            "(0-255) vs frequencies (0-100)"));
    histogramPanel.add(new JLabel("                                                         " +
            "                                " +
            "                      (Intensity is the yellow line, rest are represented " +
            "by their respective colors)"));
    mainPanel.add(histogramPanel);
    revalidate();
    repaint();
  }

  /**
   * is used to update the histogram when the current image is changed or edited.
   *
   * @param h the histogram of the new/edited image.
   */
  public void displayHistogram(Histogram h) {
    mainPanel.remove(histogramPanel);
    this.histogramPanel = h;
    this.histogramPanel.setPreferredSize(new Dimension(120, 120));
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Graph of Values " +
            "(0-255) vs frequencies (0-100)"));
    histogramPanel.add(new JLabel("                                                         " +
            "                                " +
            "                      (Intensity is the yellow line, rest are represented " +
            "by their respective colors)"));
    mainPanel.add(histogramPanel);
    revalidate();
    repaint();
  }

  /**
   * adds the button for saving a file to the GUI.
   */
  public void initializeSaveFileButton() {
    fileopenPanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel();
    fileopenPanel.add(fileSaveDisplay);
  }

  /**
   * opens a dialog box to save a file upon pressing the 'save' button in the GUI.
   */
  public void saveFileButtonOnPress() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      this.saveImageName = f.getAbsolutePath();
      revalidate();
    }
  }

  /**
   * adds the dropdown of commands/transformations for editing an image to the GUI.
   */
  public void initializeActionsDropdown() {
    comboboxDisplay.removeAll();
    mainPanel.add(comboboxDisplay);
    mainPanel.add(combobox);
  }

  /**
   * displays a dialog box for the user to input how much they want to brighten/darken the image by.
   * (value needs to be between -255 and 255, included), and returns the user input as a string.
   *
   * @return the user input (value they want to brighten/darken the image by) as a string.
   */
  public String brightenImageDialog() {
    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    JLabel inputDisplay;
    inputDisplay = new JLabel("Default");
    inputDialogPanel.add(inputDisplay);
    inputDisplay.setText(JOptionPane.showInputDialog("Enter how much you want to brighten " +
            "your image by (-255 to 255): "));
    String brightenFactor = inputDisplay.getText();
    if (Integer.parseInt(brightenFactor) > 255 || Integer.parseInt(brightenFactor) < -255) {
      throw new IllegalArgumentException("Please enter a value between -255 and 255");
    }
    dialogBoxesPanel.removeAll();
    return brightenFactor;
  }

  /**
   * displays a dialog box for the user to enter how much they want to downsize the image by.
   * (width of the new image).
   *
   * @return returns the user input (width of the new image) as a string.
   */
  public String downSizeDialogWidth() {
    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    JLabel inputDisplay;
    inputDisplay = new JLabel("Default");
    inputDialogPanel.add(inputDisplay);
    inputDisplay.setText(JOptionPane.showInputDialog("Enter the width of the new image: "));
    String width = inputDisplay.getText();
    dialogBoxesPanel.removeAll();
    return width;
  }

  /**
   * displays a dialog box for the user to enter how much they want to downsize the image by.
   * (height of the new image).
   *
   * @return returns the user input (height of the new image) as a string.
   */
  public String downSizeDialogHeight() {
    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    JLabel inputDisplay;
    inputDisplay = new JLabel("Default");
    inputDialogPanel.add(inputDisplay);
    inputDisplay.setText(JOptionPane.showInputDialog("Enter the height of the new image: "));
    String height = inputDisplay.getText();
    dialogBoxesPanel.removeAll();
    return height;
  }

  /**
   * displays an error message whenever the user provides an invalid input.
   *
   * @param error the error message to be displayed.
   */
  public void displayErrorMessage(String error) {
    JOptionPane.showMessageDialog(Gui.this, error,
            "Error!", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * returns the filepath of the current image.
   *
   * @return the filepath of the current image.
   */
  public String getImageName() {
    return this.imageName;
  }

  /**
   * returns the name the user wants to save the image as.
   *
   * @return the name the user wants to save the image as.
   */
  public String getSaveImageName() {
    return this.saveImageName;
  }
}
