package week1.percolation;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF controlUnion;
    private int n;
    private int numberOfOpenSites;
    private final int IN = 0;
    private final int OUT;


    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        numberOfOpenSites = 0;
        this.n = n;
        OUT = n * n + 1;
        grid = new boolean[n][n];
        union = new WeightedQuickUnionUF(OUT + 1);
        controlUnion = new WeightedQuickUnionUF(OUT);
    }              // create n-by-n grid, with all sites blocked

    public static void main(String[] args) {

    }  // test client (optional)

    public void open(int row, int col) {
        checkIndexesRange(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            int index = transformIndex(row, col);
            if (row == 1) {
                union.union(IN, index);
                controlUnion.union(IN, index);
            }
            if (row == n) {
                union.union(OUT, index);
            }
            addNeighbors(union, row, col);
            addNeighbors(controlUnion, row, col);
            numberOfOpenSites++;
        }
    }  // open site (row, col) if it is not open already

    private void addNeighbors(WeightedQuickUnionUF union, int row, int col) {
        connectWithCell(union, row, col, row - 1, col);
        connectWithCell(union, row, col, row + 1, col);
        connectWithCell(union, row, col, row, col - 1);
        connectWithCell(union, row, col, row, col + 1);
    }

    private void connectWithCell(WeightedQuickUnionUF union, int row, int col, int nRow, int nCol) {
        try {
            checkIndexesRange(nRow, nCol);

            if (grid[nRow - 1][nCol - 1]) {
                int first = transformIndex(row, col);
                int second = transformIndex(nRow, nCol);
                if (!union.connected(first, second)) {
                    union.union(first, second);
                }
            }
        } catch (IndexOutOfBoundsException e) {

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
        return union.connected(IN, OUT);
    }           // does the system percolate?
}