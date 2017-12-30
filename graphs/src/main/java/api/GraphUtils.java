package api;

import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

public class GraphUtils {
    /**
     * Create empty graph with {@code v} vertices.
     *
     * @param v - number of vertices
     * @return {@link Graph} implementation;
     */
    Graph empty(int v) {
        return new AdjacentListsGraph(v);
    }

    /**
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public Graph read(In in) {
        try {
            final int vertices = in.readInt();
            Graph g = empty(vertices);
            if (g.vertices() < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            int edges = in.readInt();
            if (edges < 0) {
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            }
            for (int i = 0; i < edges; i++) {
                g.addEdge(in.readInt(), in.readInt());
            }
            return g;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int w : g.adjacent(v)) {
            degree++;
        }
        return degree;
    }
}
