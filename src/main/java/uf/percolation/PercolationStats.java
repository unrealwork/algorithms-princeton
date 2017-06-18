package uf.percolation;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double probabilities[];
    private int trials;
    private final static double MAGIC_NUMBER = 1.96d;


    /**
     * Perform trials independent experiments on an n-by-n grid.
     *
     * @param n      grid size.
     * @param trials - the number of trials.
     */
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
            probabilities[i] = percolation.numberOfOpenSites() / (n * n * 1.0);
        }
    }


    /**
     * Sample mean of percolation threshold.
     *
     * @return the value of mean.
     */
    public double mean() {
        return StdStats.mean(probabilities);
    }

    /**
     * Sample standard deviation of percolation threshold.
     *
     * @return the value of standard deviation.
     */
    public double stddev() {
        return StdStats.stddev(probabilities);
    }

    /**
     * Low  endpoint of 95% confidence interval.
     *
     * @return value of the endpoint.
     */
    public double confidenceLo() {
        return mean() - MAGIC_NUMBER * stddev() / Math.sqrt(trials);
    }

    /**
     * High endpoint of 95% confidence interval.
     *
     * @return value of the endpoint.
     */
    public double confidenceHi() {
        return mean() + MAGIC_NUMBER * stddev() / Math.sqrt(trials);
    }

    /**
     * Test client.
     *
     * @param args - the arguments of the client.
     *             First - grid size, second - the number of trials.
     * @throws IllegalArgumentException if arguments are not
     *                                  corresponding to description.
     */
    public static void main(final String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        if (n < 1) {
            throw new IndexOutOfBoundsException();
        }
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println(String.format("mean                    = %f%n"
                        + "stddev                  = %f%n"
                        + "95%% confidence interval = [%f, %f]",
                stats.mean(), stats.stddev(),
                stats.confidenceLo(), stats.confidenceHi())
        );
    }
}
