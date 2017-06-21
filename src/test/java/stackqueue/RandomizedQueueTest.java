package stackqueue;

import java.util.NoSuchElementException;
import org.testng.annotations.Test;


public class RandomizedQueueTest {

  @Test(expectedExceptions = NoSuchElementException.class)
  public void testEmptyDequeueException() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.dequeue();
  }

  @Test(expectedExceptions = NoSuchElementException.class)
  public void testEmptySampleeException() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.sample();
  }

  @Test
  public void testSize() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    rq.size();
    rq.enqueue(3);
  }
}