package stackqueue;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private static final int DEFAULT_CAPACITY = 2;
  private Item[] items = (Item[]) new Object[DEFAULT_CAPACITY];

  private class RandomizedIterator implements Iterator<Item> {

    private RandomizedQueue<Item> rq;

    private RandomizedIterator(RandomizedQueue<Item> queue) {
      rq = new RandomizedQueue<>();
      for (int i = 0; i < queue.size; i++) {
        rq.enqueue(queue.items[i]);
      }
    }

    @Override
    public boolean hasNext() {
      return !rq.isEmpty();
    }

    @Override
    public Item next() {
      return rq.dequeue();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private int size = 0;

  public RandomizedQueue() {
  }                // construct an empty randomized queue

  /**
   * Is the qeque empty?
   *
   * @return true if deque is empty {}.
   */
  public boolean isEmpty() {
    return size == 0;
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
    if (item == null) {
      throw new IllegalArgumentException();
    }
    size++;
    resize();
    items[size - 1] = item;
  }

  private void resize() {
    Item[] newArray;
    if (size < DEFAULT_CAPACITY) {
      return;
    }
    if (items.length < size) {
      newArray = createArray(size * 2 + 1);
      System.arraycopy(items, 0, newArray, 0, items.length);
    } else {
      if (size * 2 + 1 <= items.length) {
        newArray = createArray(size);
        System.arraycopy(items, 0, newArray, 0, size);
      } else {
        return;
      }
    }
    items = newArray;
  }

  private Item[] createArray(int size) {
    Item[] arr = (Item[]) new Object[size];
    return arr;
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
    size--;

    if (size == 0) {
      return items[randomIndex];
    } else {
      swap(items, size, randomIndex);
      Item item = items[size];
      resize();
      return item;
    }
  }

  private void swap(Item[] arr, int i, int j) {
    Item tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Return (but do not remove) a random item.
   */
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    int randomIndex = StdRandom.uniform(size);
    return items[randomIndex];
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
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    for (int i = 0; i < 10; i++) {
      rq.enqueue(i);
    }
    for (Integer e : rq) {
      System.out.println(e);
    }
  }
}
