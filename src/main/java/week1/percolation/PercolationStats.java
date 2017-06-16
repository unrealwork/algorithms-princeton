package week1.percolation;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double probabilities[];
    private int trials;

    public PercolationStats(int n, int trials) {
        this.trials = trials;
        probabilities = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int position = StdRandom.uniform(n * n - 1);
                int row = (position + 1) / n + 1;
                int col = (position + 1) % n + 1;
                percolation.open(row, col);
            }
            probabilities[i] = percolation.numberOfOpenSites() / (n * n *1.0);
        }
    }   // perform trials independent experiments on an n-by-n grid

    public double mean() {
        return StdStats.mean(probabilities);
    }                          // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(probabilities);
    }                       // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return (mean() - 1.96d * Math.sqrt(stddev())) / Math.sqrt(trials);
    }                  // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return (mean() - 1.96d * Math.sqrt(stddev())) / Math.sqrt(trials);
    }                  // high endpoint of 95% confidence interval
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        if (n < 1 ) {
            throw new IndexOutOfBoundsException();
        }
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println(String.format("mean                    = %f%n" +
                        "stddev                  = %f%n" +
                        "95%% confidence interval = [%f, %f]",
                stats.mean(), stats.stddev(), stats.confidenceLo(), stats.confidenceHi()));
    }        // test client (described below)
}
