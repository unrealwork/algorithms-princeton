import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Solver {

    private final Board initialBoard;
    private boolean isSolved;
    private Iterable<Board> solution;
    private int moves = -1;

    /**
     * Find a solution to the initial board (using the A* algorithm)
     *
     * @param initial board.
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        this.initialBoard = initial;
    }

    /**
     * Solve a slider puzzle (given below)
     *
     * @param args args[0] - input file name.
     */
    public static void main(String[] args) {
        In stream = null;
        if (args.length > 0) {
            stream = new In(args[0]);
        }
        else {
            stream = new In(new Scanner(System.in));
        }
        Solver solver = new Solver(readBoard(stream));
        if (solver.isSolvable()) {
            System.out.format("Minimum number of moves = %d%n", solver.moves());
            Deque<Board> queue = new ArrayDeque<>();
            for (Board b : solver.solution()) {
                System.out.println(b);
            }
        }
        else {
            System.out.println("No solution possible");
        }
    }

    private static Board readBoard(In stream) {
        final int dimension = stream.readInt();
        final int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[i][j] = stream.readInt();
            }
        }
        return new Board(blocks);
    }

    /**
     * Is the initial board solvable?
     *
     * @return true if solvable.
     */
    public boolean isSolvable() {
        if (!isSolved) {
            solution();
        }
        return solution != null;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable.
     */
    public int moves() {
        if (!isSolved) {
            solution();
        }
        return moves;

    }

    public Iterable<Board> solution() {
        if (!isSolved) {
            final Board twin = initialBoard.twin();
            final GameTree initialTree = new GameTree(initialBoard);
            final GameTree twinTree = new GameTree(twin);

            while (!initialTree.isGoal() && !twinTree.isGoal()) {
                initialTree.step();
                twinTree.step();
            }

            Deque<Board> result = null;
            if (initialTree.isGoal()) {
                Node reverseSolution = initialTree.step();
                result = new ArrayDeque<>();
                for (Board b : reverseSolution) {
                    result.addFirst(b);
                }
                moves = result.size() - 1;
            }
            else {
                moves = -1;
            }
            isSolved = true;
            solution = result;
        }
        return solution;
    }

    private static class Node implements Iterable<Board> {

        private final int manhattan;
        private final int size;
        private Node predecessor;
        private Board value;

        public Node(Board value, Node predecessor) {
            this.manhattan = value.manhattan();
            this.value = value;
            this.predecessor = predecessor;
            if (predecessor == null) {
                this.size = 1;
            }
            else {
                this.size = predecessor.size + 1;
            }
        }

        private Node(Board value) {
            this.size = 0;
            this.manhattan = value.manhattan();
            this.value = value;
        }

        public int getSize() {
            return size;
        }

        public Board getValue() {
            return value;
        }

        public Node getPredecessor() {
            return predecessor;
        }

        public void setPredecessor(Node predecessor) {
            this.predecessor = predecessor;
        }

        public boolean contains(final Board e) {
            for (Board t : this) {
                if (t.equals(e)) {
                    return true;
                }
            }
            return false;
        }

        public int priority() {
            return this.manhattan + this.size;
        }

        @Override
        public Iterator<Board> iterator() {

            return new Iterator<Board>() {
                private Node cur = Node.this;

                @Override
                public boolean hasNext() {
                    return cur != null;
                }

                @Override
                public void remove() {
                    throw new IllegalStateException();
                }

                @Override
                public Board next() {
                    if (hasNext()) {
                        final Board v = cur.getValue();
                        cur = cur.getPredecessor();
                        return v;
                    }
                    throw new NoSuchElementException();
                }
            };
        }
    }

    private static class GameTree {

        private final MinPQ<Node> pq;

        private GameTree(Board startBoard) {
            this.pq = startPQ(startBoard);
        }

        private MinPQ<Node> startPQ(Board board) {
            final MinPQ<Node> result = new MinPQ<>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return Integer.compare(o1.priority(),
                            o2.priority());
                    }
                });
            result.insert(new Node(board));
            return result;
        }

        public Node step() {
            if (!isGoal()) {
                final Node best = pq.delMin();
                final Iterable<Board> neighbors = best.getValue().neighbors();
                for (Board board : neighbors) {
                    if (best.getPredecessor() == null || !board
                        .equals(best.getPredecessor().value)) {
                        pq.insert(new Node(board, best));
                    }
                }
            }
            return pq.min();
        }

        public boolean isGoal() {
            return pq.min().value.isGoal();
        }
    }
}
