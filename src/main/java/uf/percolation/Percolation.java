package uf.percolation;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Class that simulate percolation's model.
 */
public class Percolation {
    /**
     * 2D array that contains open state for each site.
     */
    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF controlUnion;
    private int n;
    private int numberOfOpenSites;
    private static final int IN = 0;
    private final int out;


    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        numberOfOpenSites = 0;
        this.n = n;
        out = n * n + 1;
        grid = new boolean[n][n];
        union = new WeightedQuickUnionUF(out + 1);
        controlUnion = new WeightedQuickUnionUF(out);
    }              // create n-by-n grid, with all sites blocked


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
            if (row == n) {
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
     * @param row   - row index of the site.
     * @param col   - column index of the site.
     */
    private void addNeighbors(final WeightedQuickUnionUF union, final int row, final int col) {
        connectWithCell(union, row, col, row - 1, col);
        connectWithCell(union, row, col, row + 1, col);
        connectWithCell(union, row, col, row, col - 1);
        connectWithCell(union, row, col, row, col + 1);
    }

    private boolean connectWithCell(WeightedQuickUnionUF union, int row, int col, int nRow, int nCol) {
        try {
            checkIndexesRange(nRow, nCol);
            if (grid[nRow - 1][nCol - 1]) {
                int first = transformIndex(row, col);
                int second = transformIndex(nRow, nCol);
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
            if (index < 1 || index > n) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private int transformIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    public boolean isOpen(int row, int col) {
        checkIndexesRange(row, col);
        return grid[row - 1][col - 1];
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        checkIndexesRange(row, col);
        return isOpen(row, col) && controlUnion.connected(IN, transformIndex(row, col));
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }       // number of open sites

    public boolean percolates() {
        return union.connected(IN, out);
    }           // does the system percolate?
}
