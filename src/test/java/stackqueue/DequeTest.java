package stackqueue;

import edu.princeton.cs.algs4.In;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class DequeTest {

  @Test
  public void testSize() throws Exception {
    Deque<String> deque = new Deque<>();
    assertEquals(deque.size(), 0);
    deque.addFirst("a");
    deque.addLast("b");
    deque.removeFirst();
    assertEquals(deque.size(), 1);
  }

  @Test
  public void testAddFirst() throws Exception {
    Deque<String> deque = new Deque<>();
    deque.addFirst("a");
    deque.addFirst("item");
    assertEquals(deque.removeFirst(), "item");
  }

  @Test
  public void testAddLast() throws Exception {
    Deque<String> deque = new Deque<>();
    deque.addLast("a");
    deque.addLast("item");
    assertEquals(deque.removeFirst(), "a");
  }

  @Test
  public void testIterator() throws Exception {
    Deque<String> deque = new Deque<>();
    String[] strings = {"a", "c"};
    deque.addLast(strings[0]);
    deque.addLast(strings[1]);
    for (String e : deque) {

    }
  }

  @Test
  public void testMain() throws Exception {
  }

  @Test
  public <T> void testIsEmpty() throws Exception {
    Deque<String> deque = new Deque<>();
    assertEquals(deque.isEmpty(), true);
    deque.addFirst("a");
    assertEquals(deque.isEmpty(), false);
  }

  @Test
  public void test0() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(0);
    deque.isEmpty();
    deque.addFirst(2);
    deque.addFirst(3);
    deque.addFirst(4);
    deque.addFirst(5);
    deque.addFirst(6);
    deque.removeLast();
  }

  @Test
  public void test1() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(0);
    deque.removeFirst();
  }

  @Test
  public void test2() {
    Deque<Integer> deque = new Deque<>();
    deque.isEmpty();
    deque.addFirst(1);
    deque.removeFirst();
  }

  @Test
  public void test3() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(0);
    deque.removeLast();
  }
}