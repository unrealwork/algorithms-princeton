package api;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths implements Paths {

    private final int start;
    private boolean marked[];
    private int[] edgeTo;
    private Graph graph;

    BreadthFirstPaths(Graph g, int s) {
        this.graph = g;
        this.start = s;
        this.marked = new boolean[graph.vertices()];
        this.edgeTo = new int[graph.vertices()];
        bfs(g, s);
    }

    @Override
    public Graph graph() {
        return graph;
    }

    @Override
    public int start() {
        return start;
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (hasPathTo(v)) {
            Stack<Integer> path = new Stack<>();
            for (int currentVertex = v; currentVertex != start; currentVertex = edgeTo[currentVertex]) {
                path.push(edgeTo[currentVertex]);
            }
            return path;
        }
        return null;
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : g.adjacent(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    queue.add(w);
                    edgeTo[w] = v;
                }
            }
        }
    }
}
