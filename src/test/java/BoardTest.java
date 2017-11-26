import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class BoardTest {

    @Test
    public void testDimension() throws Exception {
    }

    @Test(dataProviderClass = BoardTestProvider.class,
        dataProvider = "provideHamming")
    public void testHamming(final int[][] board, final int expectedDistance) {
        Board b = new Board(board);
        System.out.println(b);
        assertEquals(b.hamming(), expectedDistance);
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
        final int[][] blocks = new int[][]
            {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
            };
        final Board board = new Board(blocks);
        final Board twin = board.twin();
        assertNotEquals(board, twin);
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

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testNullConstruct() {
        new Board(null);
    }
}