package api;

public interface Paths {
    /**
     * Graph where we find paths.
     *
     * @return {@link Graph} instance;
     */
    Graph graph();

    /**
     * Start vertex.
     *
     * @return {@link int} number.
     */
    int start();

    /**
     * Does the {v} has path to start vertex?
     *
     * @param v - vertex.
     * @return v - vertex.
     */
    boolean hasPathTo(int v);


    /**
     * Return path from start vertex to passed vertex {v}
     *
     * @param v - vertex
     * @return Iterable of vertices.
     */
    Iterable<Integer> pathTo(int v);
}
