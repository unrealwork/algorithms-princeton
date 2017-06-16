package week1.percolation;

import edu.princeton.cs.algorithms.WeightedQuickUnionUF;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private int n;

    public Percolation(int n) {
        this.n = n;
        grid = new boolean[n][n];
        union = new WeightedQuickUnionUF(n * n);
    }              // create n-by-n grid, with all sites blocked

    public static void main(String[] args) {
        throw new NotImplementedException();
    }  // test client (optional)

    public void open(int row, int col) {
        checkIndexiesRange(row, col);
        grid[row - 1][col - 1] = true;

    }  // open site (row, col) if it is not open already

    private void checkIndexiesRange(Integer... indexies) {
        for (Integer index : indexies) {
            if (index < 1 || index > n) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private int[] neighbors(int row, int col) {
        {
            throw new NotImplementedException();
        }
    }

    public boolean isOpen(int row, int col) {
        checkIndexiesRange(row, col);
        return grid[row][col];
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        throw new NotImplementedException();
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        throw new NotImplementedException();
    }       // number of open sites

    public boolean percolates() {
        throw new NotImplementedException();
    }           // does the system percolate?
}