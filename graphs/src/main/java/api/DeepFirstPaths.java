package api;

import java.util.Stack;

public class DeepFirstPaths implements Paths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    private Graph graph;

    public DeepFirstPaths(Graph graph, int s) {
        this.s = s;
        final int verticesCount = graph.vertices();
        this.marked = new boolean[verticesCount];
        this.edgeTo = new int[verticesCount];
        this.graph = graph;
        dfs(graph, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adjacent(v)) {
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }

    @Override
    public Graph graph() {
        return graph;
    }

    @Override
    public int start() {
        return s;
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (hasPathTo(v)) {
            Stack<Integer> path = new Stack<>();
            for (int currentVertex = v; currentVertex != s; currentVertex = edgeTo[currentVertex]) {
                path.push(edgeTo[currentVertex]);
            }
            return path;
        }
        return null;
    }
}
