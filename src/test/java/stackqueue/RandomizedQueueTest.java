package stackqueue;

import static org.testng.Assert.assertEquals;

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

  @Test
  public void test0() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    rq.enqueue(159);
    rq.enqueue(55);
    assertEquals(rq.isEmpty(), false);
    rq.enqueue(97);
    rq.enqueue(188);
    rq.enqueue(192);
    rq.enqueue(380);
    rq.enqueue(133);
    rq.dequeue();
  }

  @Test
  public void test2() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    rq.size();
    rq.enqueue(585);
    assertEquals(rq.isEmpty(), false);
    rq.enqueue(787);
    rq.enqueue(328);
    rq.enqueue(928);
    rq.enqueue(510);
    rq.dequeue();
    rq.dequeue();

  }

  @Test
  public void test3() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    rq.isEmpty();
    rq.enqueue(2);
    rq.enqueue(0);
    rq.enqueue(2);
    rq.dequeue();
  }

  @Test
  public void test4() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    for (int i = 0; i < 1000; i++) {
      rq.enqueue(i);
    }
    for (int i = 0; i < 1000; i++) {
      rq.dequeue();
    }
    assertEquals(rq.isEmpty(), true);
  }

  @Test
  public void test5() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    for (int i = 0; i < 10; i++) {
      rq.enqueue(i);
    }
    for (Integer e : rq) {
      System.out.println(e);
    }
  }
}