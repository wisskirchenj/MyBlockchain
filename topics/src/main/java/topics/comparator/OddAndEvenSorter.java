package topics.comparator;

import java.util.Comparator;
import java.util.List;

/**
 * Odd and even
 * Write a method that takes a List of Integer numbers and returns a List containing the same Integer numbers sorted according
 * to the following rules:
 * In the sorted List, odd numbers should be at the beginning in ascending order and even numbers should be at the end in
 * descending order. You don't need to read or write anything from or to the console, just implement the method.
 */
public class OddAndEvenSorter {
    public static List<Integer> sortOddEven(List<Integer> numbers) {
        Comparator<Integer> oddLessThanEven = Comparator.comparingInt(m -> Math.abs(m % 2));
        oddLessThanEven = oddLessThanEven.reversed();
        numbers.sort(oddLessThanEven.thenComparing((m, n)-> m % 2 == 0 ? n.compareTo(m) : m.compareTo(n) ));

        return numbers;
    }
}