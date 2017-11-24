package puzzles;

import static java.lang.Math.abs;

import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Board {

  private final int[][] blocks;
  private int dimension;

  /**
   * Construct a board from an n-by-n array of blocks.
   *
   * @param blocks where blocks[i][j] = block in row i, column j;
   */
  public Board(int[][] blocks) {
    this.dimension = blocks.length;
    this.blocks = blocks;
  }


  /**
   * Board dimension n
   */
  public int dimension() {
    return dimension;
  }

  /**
   * Number of blocks out of place
   *
   * @return number
   */
  public int hamming() {
    int count = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (blocks[i][j] != (dimension * i + j + 1)) {
          count++;
        }
      }
    }
    return count - 1;
  }

  /**
   * Sum of Manhattan distances between blocks and goal
   */
  public int manhattan() {
    int count = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        final int value = blocks[i][j];
        if (value != 0) {
          int expectedI = (value - 1) / dimension;
          int expetedJ = value - 1 - dimension * expectedI;
          count += (abs(i - expectedI) + abs(j - expetedJ));
        }
      }
    }
    return count;
  }

  public boolean isGoal() {
    return hamming() == 0;
  }

  /**
   * A board that is obtained by exchanging any pair of blocks.
   *
   * @return twin board.
   */
  public Board twin() {
    throw new NotImplementedException();
  }

  /**
   * Does this board equal y?
   */
  public boolean equals(Object y) {
    return false;
  }


  /**
   * All neighboring boards.
   *
   * @return Iterable object
   */
  public Iterable<Board> neighbors() {
    return new ArrayList<>();
  }

  /**
   * String representation of this board (in the output format specified below)
   *
   * @return string representation.
   */
  public String toString() {
    return "";
  }
}
