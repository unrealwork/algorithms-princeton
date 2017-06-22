package stackqueue;

import edu.princeton.cs.algs4.StdIn;

/**
 * Class that implements permutation main routine.
 */
public class  Permutation {

  /**
   * Permutation client.
   *
   * @param args - client params
   */
  public static void main(final String[] args) {
    Integer k = Integer.parseInt(args[0]);
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      final String line = StdIn.readLine();
      for (String s : line.split("\\s")) {
        queue.enqueue(s);
      }
    }
    for (int i = 0; i < k; i++) {
      System.out.println(queue.dequeue());
    }
  }
}
