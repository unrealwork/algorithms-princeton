import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private int n;
    private int numberOfOpenSites;


    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        numberOfOpenSites = 0;
        this.n = n;
        grid = new boolean[n][n];
        union = new WeightedQuickUnionUF(n * n);
    }              // create n-by-n grid, with all sites blocked

    public static void main(String[] args) {

    }  // test client (optional)

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            connectWithCell(row, col, row - 1, col);
            connectWithCell(row, col, row + 1, col);
            connectWithCell(row, col, row, col - 1);
            connectWithCell(row, col, row, col + 1);
            numberOfOpenSites++;
        }
    }  // open site (row, col) if it is not open already

    private void connectWithCell(int row, int col, int nRow, int nCol) {
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
        return (row - 1) * n + col - 1;
    }

    public boolean isOpen(int row, int col) {
        checkIndexesRange(row, col);
        return grid[row - 1][col - 1];
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        for (int i = 0; i < n; i++) {
            if (grid[row - 1][col - 1]) {
                int index = transformIndex(row, col);
                if (union.connected(i, index)) {
                    return true;
                }
            }
        }
        return false;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }       // number of open sites

    public boolean percolates() {
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) {
                return true;
            }
        }
        return false;
    }           // does the system percolate?
}