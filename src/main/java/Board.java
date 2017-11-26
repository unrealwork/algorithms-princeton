import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;


public class Board {
    private final int[][] blocks;
    private final int dimension;
    private Board twin;

    /**
     * Construct a board from an n-by-n array of blocks.
     *
     * @param blocks where blocks[i][j] = block in row i, column j;
     */
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalArgumentException();
        }
        this.dimension = blocks.length;
        this.blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
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
                    final Position p = position(value);
                    count += (Math.abs(i - p.getY()) + Math.abs(j - p.getX()));
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
        if (twin == null) {
            final Position firstBlock = randomBlock();
            twin = swapBlocks(firstBlock, randomBlock(firstBlock));
        }
        return twin;
    }

    private Position randomBlock() {
        Position p = null;
        do {
            p = position(StdRandom.uniform(1, dimension * dimension));
        } while (getValue(p) == 0);
        return p;
    }

    private Position randomBlock(Position excludedBlock) {
        Position p = null;
        do {
            p = randomBlock();
        } while (p.equals(excludedBlock));
        return p;
    }

    private int getValue(Position p) {
        return blocks[p.getY()][p.getX()];
    }

    private Position position(final int n) {
        int y = (n - 1) / dimension;
        int x = n - 1 - dimension * y;
        return new Position(x, y);
    }

    /**
     * Does this board equal y?
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        Board board = (Board) that;

        if (dimension != board.dimension) {
            return false;
        }
        return Arrays.deepEquals(blocks, board.blocks);
    }

    /**
     * All neighboring boards.
     *
     * @return Iterable object
     */
    public Iterable<Board> neighbors() {
        int i, j = 0;
        ArrayList<Board> neighbors = new ArrayList<>();
        for (i = 0; i < blocks.length; i++) {
            boolean isFound = false;
            for (j = 0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        if (i > 0) {
            neighbors.add(swapBlocks(i, j, i - 1, j));
        }
        if (i < dimension - 1) {
            neighbors.add(swapBlocks(i, j, i + 1, j));
        }

        if (j > 0) {
            neighbors.add(swapBlocks(i, j, i, j - 1));
        }
        if (j < dimension - 1) {
            neighbors.add(swapBlocks(i, j, i, j + 1));
        }
        return neighbors;
    }

    private Board swapBlocks(Position p1, Position p2) {
        return swapBlocks(p1.getY(), p1.getX(), p2.getY(), p2.getX());
    }

    private Board swapBlocks(int i, int j, int i1, int j1) {
        int[][] result = copyBlocks();
        int tmp = result[i][j];
        result[i][j] = result[i1][j1];
        result[i1][j1] = tmp;
        return new Board(result);
    }

    private int[][] copyBlocks() {
        int[][] copy = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                copy[i][j] = blocks[i][j];
            }
        }
        return copy;
    }

    /**
     * String representation of this board (in the output format specified below)
     *
     * @return string representation.
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d%n", dimension));
        final int size = dimension * dimension - 1;
        final int offset = Integer.toString(size).length() + 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(String
                    .format("%" + offset + "d",
                        blocks[i][j]));
            }
            sb.append(String.format("%n"));
        }
        return sb.toString();
    }

    private static class Position {

        private final int x;
        private final int y;

        private Position(int j, int i) {
            this.y = i;
            this.x = j;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            }
            if (that == null || getClass() != that.getClass()) {
                return false;
            }

            Position position = (Position) that;

            if (x != position.x) {
                return false;
            }
            return y == position.y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}
