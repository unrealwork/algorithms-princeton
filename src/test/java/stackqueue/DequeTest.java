package stackqueue;

import org.testng.annotations.Test;

import java.util.ArrayList;

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


}