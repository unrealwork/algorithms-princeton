package utils;


import stackqueue.Deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestUtils {
    public static Deque<Integer> readDeque(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            Deque<Integer> deque = new Deque<>();
            Scanner scanner = new Scanner(reader);
            int size = scanner.nextInt();
            for (int i = 0; i < size; i++) {
                deque.addLast(scanner.nextInt());

            }
            scanner.close();
            return deque;
        } catch (IOException e) {
            throw new IllegalStateException("File not found");
        }
    }


}
