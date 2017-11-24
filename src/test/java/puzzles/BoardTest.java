package puzzles;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BoardTest {

  @Test
  public void testDimension() throws Exception {
  }

  @Test(dataProviderClass = BoardTestProvider.class,
      dataProvider = "provideHamming")
  public void testHamming(final int[][] board, final int expectedDistance) {
    assertEquals(new Board(board).hamming(), expectedDistance);
  }

  @Test(dataProviderClass = BoardTestProvider.class,
      dataProvider = "provideManhattan")
  public void testManhattan(final int[][] board, final int expectedDistance) {
    assertEquals(new Board(board).manhattan(), expectedDistance);
  }

  @Test
  public void testIsGoal() throws Exception {
  }

  @Test
  public void testTwin() throws Exception {
  }

  @Test
  public void testEquals() throws Exception {
  }

  @Test
  public void testNeighbors() throws Exception {
  }

  @Test
  public void testToString() throws Exception {
  }

}