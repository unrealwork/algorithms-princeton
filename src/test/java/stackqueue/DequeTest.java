package stackqueue;

import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class DequeTest {
    @Test
    public <T> void testIsEmpty(Deque<T> deque, boolean expectedResult) throws Exception {
        assertEquals(deque.isEmpty(), expectedResult);
    }

}