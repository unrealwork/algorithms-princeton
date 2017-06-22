package uf.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Class that simulate percolation's model.
 */
public class Percolation {

  private static final int IN = 0;
  private final int out;
  /**
   * 2D array that contains open state for each site.
   */
  private boolean[][] grid;
  private WeightedQuickUnionUF union;
  private WeightedQuickUnionUF controlUnion;
  private int gridSize;
  private int numberOfOpenSites;

  /**
   * Initialize Percolation class with specified grid size.
   *
   * @param gridSize - grid size
   */
  public Percolation(int gridSize) {
    if (gridSize < 1) {
      throw new IllegalArgumentException();
    }
    numberOfOpenSites = 0;
    this.gridSize = gridSize;
    out = gridSize * gridSize + 1;
    grid = new boolean[gridSize][gridSize];
    union = new WeightedQuickUnionUF(out + 1);
    controlUnion = new WeightedQuickUnionUF(out);
  }              // create gridSize-by-gridSize grid, with all sites blocked


  /**
   * Test client (optional).
   *
   * @param args - arguments for client.
   */
  public static void main(final String[] args) {
  }

  /**
   * Open site (row, col) if it is not open already
   *
   * @param row row index.
   * @param col column index.
   */
  public void open(final int row, final int col) {
    checkIndexesRange(row, col);
    if (!isOpen(row, col)) {
      grid[row - 1][col - 1] = true;
      int index = transformIndex(row, col);
      if (row == 1) {
        union.union(IN, index);
        controlUnion.union(IN, index);
      }
      if (row == gridSize) {
        union.union(out, index);
      }
      addNeighbors(union, row, col);
      addNeighbors(controlUnion, row, col);
      numberOfOpenSites++;
    }
  }

  /**
   * Add connection to neigbors of the site.
   *
   * @param union - uninon to add  connection.
   * @param row - row index of the site.
   * @param col - column index of the site.
   */
  private void addNeighbors(final WeightedQuickUnionUF union, final int row, final int col) {
    connectWithCell(union, row, col, row - 1, col);
    connectWithCell(union, row, col, row + 1, col);
    connectWithCell(union, row, col, row, col - 1);
    connectWithCell(union, row, col, row, col + 1);
  }

  private boolean connectWithCell(WeightedQuickUnionUF union, int row, int col, int neighborRow,
      int neighborCol) {
    try {
      checkIndexesRange(neighborRow, neighborCol);
      if (grid[neighborRow - 1][neighborCol - 1]) {
        int first = transformIndex(row, col);
        int second = transformIndex(neighborRow, neighborCol);
        if (!union.connected(first, second)) {
          union.union(first, second);
        }
      }
      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  private void checkIndexesRange(int... indexes) {
    for (int index : indexes) {
      if (index < 1 || index > gridSize) {
        throw new IndexOutOfBoundsException();
      }
    }
  }

  private int transformIndex(int row, int col) {
    return (row - 1) * gridSize + col;
  }

  public boolean isOpen(int row, int col) {
    checkIndexesRange(row, col);
    return grid[row - 1][col - 1];
  }  // is site (row, col) open?


  /**
   * Is site (row, col) full.
   *
   * @param row - row index
   * @param col - column index
   * @return true if site is full
   */
  public boolean isFull(int row, int col) {
    checkIndexesRange(row, col);
    return isOpen(row, col) && controlUnion.connected(IN, transformIndex(row, col));
  }


  /**
   * Number of open sites.
   *
   * @return - the value.
   */
  public int numberOfOpenSites() {
    return numberOfOpenSites;
  }

  /**
   * Does the system percolate?
   *
   * @return true if system percolates, otherwise - false.
   */
  public boolean percolates() {
    return union.connected(IN, out);
  }
}
