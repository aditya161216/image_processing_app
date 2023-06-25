package model;

/**
 * A class representing a kernel. A square kernel
 */
public class Kernel {
  private int size;
  private double[][] kernelData;

  /**
   * sets the kernel to the specific kernel required to blur an image.
   */
  public void setKernelBlur() {
    this.size = 3;
    kernelData = new double[this.size][this.size];
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (row % 2 == 0 && col % 2 == 0) {
          kernelData[row][col] = 0.0625;
        } else if (row == 1 && col == 1) {
          kernelData[row][col] = 0.25;
        } else {
          kernelData[row][col] = 0.125;
        }
      }
    }
  }

  /**
   * sets the kernel to the specific kernel required to sharpen an image.
   */
  public void setKernelSharpen() {
    this.size = 5;
    kernelData = new double[this.size][this.size];

    kernelData[0] = new double[]{-0.125, -0.125, -0.125, -0.125, -0.125};
    kernelData[1] = new double[]{-0.125, 0.25, 0.25, 0.25, -0.125};
    kernelData[2] = new double[]{-0.125, 0.25, 1.0, 0.25, -0.125};
    kernelData[3] = new double[]{-0.125, 0.25, 0.25, 0.25, -0.125};
    kernelData[4] = new double[]{-0.125, -0.125, -0.125, -0.125, -0.125};
  }


  /**
   * gets the size of the kernel.
   *
   * @return the length of the kernel as an integer.
   */
  public int getKernelSize() {
    return this.size;
  }

  /**
   * Gets the doubles that make up the kernel.
   *
   * @return the data of the kernel as a 2-dimensional array of doubles.
   */
  public double[][] getKernelData() {
    return this.kernelData;
  }
}
