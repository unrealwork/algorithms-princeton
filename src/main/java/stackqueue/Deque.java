package stackqueue;


import java.util.Iterator;

/**
 * Implement a simple deque.
 *
 * @param <Item> any class.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item value;
        private Node next;
        private Node previous;

        private Node(Item value) {
            this.value = value;
        }
    }

    private int size = 0;
    private Node head;
    private Node tail;

    /**
     * Construct an empty deque.
     */
    public Deque() {
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
     *
     * @param item
     */
    public void addFirst(Item item) {
        Node node = new Node(item);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void addLast(Item item) {
        Node node = new Node(item);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
    }          // add the item to the end

    /**
     * Remove and return the item from the front.
     *
     * @return front element
     */
    public Item removeFirst() {
        Item result = head.value;
        if (head == tail) {
            head = null;
            tail = null;
        }
        head = head.next;
        head.previous = null;
        return result;
    }


    /**
     * Remove and return the item from the end.
     *
     * @return a reference to removed element
     */
    public Item removeLast() {
        Item result = tail.value;
        tail = tail.previous;
        tail.next = null;
        return result;
    }

    /**
     * Return an iterator over items in order from front to end
     *
     * @return iterator
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return !isEmpty() && tail.next != null;
            }

            @Override
            public Item next() {
                return current.value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    /**
     * Test client
     *
     * @param args client's arguments.
     */
    public static void main(String[] args) {

    }
}
