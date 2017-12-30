package api;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class AdjacentListsGraph implements Graph {
    private static final String NEWLINE = System.lineSeparator();
    private final int vertices;
    private int edges;
    private Set<Integer>[] adj;

    public AdjacentListsGraph(final int v) {
        this.vertices = v;
        adj = new Set[this.vertices];
        Arrays.fill(adj, new TreeSet<Integer>());
        this.edges = 0;
    }

    @Override
    public void addEdge(int v, int n) {
        validateVertex(v);
        validateVertex(n);
        adj[v].add(n);
        adj[n].add(v);
        edges++;
    }

    @Override
    public Iterable<Integer> adjacent(int v) {
        return null;
    }

    @Override
    public int vertices() {
        return vertices;
    }

    @Override
    public int edges() {
        return 0;
    }

    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * throw an IllegalArgumentException unless {@code 0 <= v < V}
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertices - 1));
    }
}
