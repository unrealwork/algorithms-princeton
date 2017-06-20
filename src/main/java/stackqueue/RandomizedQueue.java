package stackqueue;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private class Node {

    private Item value;
    private Node next;

    private Node(Item value) {
      this.value = value;
    }
  }

  private class RandomizedIterator implements Iterator<Item> {

    private RandomizedQueue<Item> backupQueue;


    public RandomizedIterator(RandomizedQueue<Item> queue) {
      backupQueue = new RandomizedQueue<>();
      Node pointer = queue.head;
      while (pointer != null) {
        backupQueue.enqueue(pointer.value);
        pointer = pointer.next;
      }
    }

    @Override
    public boolean hasNext() {
      return !backupQueue.isEmpty();
    }

    @Override
    public Item next() {
      return backupQueue.dequeue();
    }

    @Override
    public void remove() {

    }
  }

  private int size = 0;
  private Node head;

  public RandomizedQueue() {
  }                // construct an empty randomized queue

  /**
   * Is the qeque empty?
   *
   * @return true if deque is empty {}.
   */
  public boolean isEmpty() {
    return head == null;
  }


  /**
   * Return the number of items on the queue.
   */
  public int size() {
    return size;
  }

  /**
   * Add the item.
   *
   * @param item - item to add.
   */
  public void enqueue(Item item) {
    Node node = new Node(item);
    if (isEmpty()) {
      head = node;
    } else {
      node.next = head;
      head = node;
    }
    size++;
  }

  /**
   * Remove and return a random item.
   *
   * @return random item.
   */
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    int randomIndex = StdRandom.uniform(size);
    int i = 0;
    Node pointer = head;
    size--;
    if (randomIndex == 0) {
      head = head.next;
      return pointer.value;
    } else {
      while (i + 1 < randomIndex) {
        pointer = pointer.next;
        i++;
      }
      Item item = pointer.next.value;
      pointer.next = pointer.next.next;
      return item;
    }
  }

  /**
   * Return (but do not remove) a random item.
   */
  public Item sample() {
    int randomIndex = StdRandom.uniform(size);
    int i = 0;
    Node pointer = head;
    while (i < randomIndex) {
      pointer = pointer.next;
      i++;
    }
    return pointer.value;
  }                     //

  public Iterator<Item> iterator() {
    return new RandomizedIterator(this);
  }     // return an independent iterator over items in random order


  /**
   * Test client.
   *
   * @param args client params.
   */
  public static void main(String[] args) {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("a");
    queue.enqueue("b");
    queue.enqueue("c");
    queue.enqueue("d");
    queue.enqueue("e");

    for (String s : queue) {
      System.out.println(s);
    }
  }
}
