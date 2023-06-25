package view;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Arrays;
import java.util.Map;

import javax.swing.JPanel;

import model.Image;

/**
 * A class representing the operations required by a histogram that caters to a line chart
 * specifically for our image class.
 */
public class Histogram extends JPanel {
  private Image image;
  private Map<Integer, Integer> redHashMap;
  private Map<Integer, Integer> greenHashMap;
  private Map<Integer, Integer> blueHashMap;
  private Map<Integer, Integer> intensityHashMap;
  private int min;
  private int max;

  /**
   * Public constructor for a histogram that obtains all the frequencies of pixels from the
   * given image, and normalizes the frequencies.
   *
   * @param image represents an image that all the data will be obtained from.
   */
  public Histogram(Image image) {
    this.image = image;
    this.redHashMap = this.image.getRedHistogram();
    this.greenHashMap = this.image.getGreenHistogram();
    this.blueHashMap = this.image.getBlueHistogram();
    this.intensityHashMap = this.image.getIntensityHistogram();
    computeMinValues();
    computeMaxValues();
    normalizeAllHashMaps();
  }

  private void normalizeAllHashMaps() {
    this.image.changeBrightness(0);
    normalizeHashMap(redHashMap);
    normalizeHashMap(greenHashMap);
    normalizeHashMap(blueHashMap);
    normalizeHashMap(intensityHashMap);
  }

  /**
   * Overriding paintComponent to include all the painting required for the line graphs.
   * Includes setting the axes, drawing the lines and setting their colors.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawLine(10, 100, 10, 10);
    g.drawLine(10, 100, 522, 100);
    drawLine(g, this.redHashMap, Color.red);
    drawLine(g, this.greenHashMap, Color.green);
    drawLine(g, this.blueHashMap, Color.blue);
    drawLine(g, this.intensityHashMap, Color.yellow);
  }

  private void normalizeHashMap(Map<Integer, Integer> map) {
    Integer[] histogramArray = normalizeData(map, 100);
    for (int key = 0; key < 256; key++) {
      map.replace(key, histogramArray[key]);
    }
  }

  private Integer[] normalizeData(Map<Integer, Integer> histogram, int maxBound) {
    Integer[] histogramArray = histogram.values().toArray(new Integer[0]);
    for (int i = 0; i < histogramArray.length; i++) {
      double currentValue = histogramArray[i] - this.min;
      double maxMin = this.max - this.min;
      double x = currentValue / maxMin;
      double x2 = x * maxBound;
      histogramArray[i] = (int) x2;
    }
    return histogramArray;
  }

  private void computeMinValues() {
    int redGreen = Math.min(findMinValue(redHashMap), findMinValue(greenHashMap));
    int blueIntensity = Math.min(findMinValue(blueHashMap), findMinValue(intensityHashMap));
    this.min = Math.min(redGreen, blueIntensity);
  }

  private int findMinValue(Map<Integer, Integer> map) {
    Integer[] frequencies = map.values().toArray(new Integer[0]);
    Arrays.sort(frequencies);
    return frequencies[0];
  }

  private void computeMaxValues() {
    int redGreen = Math.max(findMaxValue(redHashMap), findMaxValue(greenHashMap));
    int blueIntensity = Math.max(findMaxValue(blueHashMap), findMaxValue(intensityHashMap));
    this.max = Math.max(redGreen, blueIntensity);
  }

  private int findMaxValue(Map<Integer, Integer> map) {
    Integer[] frequencies = map.values().toArray(new Integer[0]);
    Arrays.sort(frequencies);
    return frequencies[frequencies.length - 1];
  }

  private void drawLine(Graphics g, Map<Integer, Integer> hash, Color color) {
    for (int i = 0; i < 255; i++) {
      int currentPointX = (i * 2) + 10;
      int currentPointY = 100 - hash.get(i);
      int nextPointY = 100 - hash.get(i + 1);
      g.setColor(color);
      g.drawLine(currentPointX, currentPointY, currentPointX + 2, nextPointY);
    }
  }
}