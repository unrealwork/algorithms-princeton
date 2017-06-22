package stackqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implement a simple deque.
 *
 * @param <T> any class.
 */
public class Deque<T> implements Iterable<T> {

  private int size = 0;
  private Node head;
  private Node tail;

  /**
   * Construct an empty deque.
   */
  public Deque() {
  }

  /**
   * Test client
   *
   * @param args client's arguments.
   */
  public static void main(String[] args) {

  }

  /**
   * Is the deque empty?
   *
   * @return true if deque is empty.
   */
  public boolean isEmpty() {
    return head == null;
  }


  /**
   * Return the number of items on the deque.
   *
   * @return the value
   */
  public int size() {
    return size;
  }                       //

  /**
   * Add the item to thhe front.
   */
  public void addFirst(T item) {
    if (item == null) {
      throw new NullPointerException();
    }
    size++;
    Node node = new Node(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.next = head;
      head.previous = node;
      head = node;
    }
  }

  /**
   * Add the item to the end.
   *
   * @param item - instance of T.
   */
  public void addLast(T item) {
    if (item == null) {
      throw new NullPointerException();
    }
    size++;
    Node node = new Node(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.previous = tail;
      tail.next = node;
      tail = node;
    }
  }

  /**
   * Remove and return the item from the front.
   *
   * @return front element
   */
  public T removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;
    T result = head.value;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      head.previous = null;
    }
    return result;
  }


  /**
   * Remove and return the item from the end.
   *
   * @return a reference to removed element
   */
  public T removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;
    T result;
    if (head == tail) {
      result = head.value;
      head = null;
      tail = null;
    } else {
      result = tail.value;
      tail = tail.previous;
      tail.next = null;
    }
    return result;

  }

  /**
   * Return an iterator over items in order from front to end.
   *
   * @return iterator
   */
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node current = head;

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        if (current == null) {
          throw new NoSuchElementException();
        }
        final T value = current.value;
        current = current.next;
        return value;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  private class Node {

    private T value;
    private Node next;
    private Node previous;

    private Node(T value) {
      this.value = value;
    }
  }
}
