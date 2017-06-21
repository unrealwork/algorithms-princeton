package stackqueue;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] items = (Item[]) new Object[0];

  private class RandomizedIterator implements Iterator<Item> {

    private RandomizedQueue<Item> backupQueue;


    public RandomizedIterator(RandomizedQueue<Item> queue) {
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
      throw new NullPointerException();
    }
    size++;
    resize();
    items[size - 1] = item;
  }

  private void resize() {
    Item[] newArray;
    if (items.length < size) {
      newArray = (Item[]) new Object[size * 2];
    } else {
      if (size * 2 < items.length) {
        newArray = (Item[]) new Object[size];
      } else {
        return;
      }
    }
    int i = 0;
    for (Item e : items) {
      i++;
      newArray[i] = e;
    }
    items = newArray;
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
