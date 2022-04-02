package topics.streams.reductionmethods;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Sum of divisors
 * For a given range from a to b both inclusive, count sum of numbers that are divisible by n or m. Use streams to solve the problem.
 *
 * For example, for range from 10 to 20 and n = 2, m = 3, there are 6 numbers that are divisible by n or m:
 * 10, 12, 14, 15, 16, 18, 20. The sum of these numbers equals 105.
 */
public class FilteredSum {
    /**
     * Calculates the sum of numbers in the range from a to b both inclusive
     * that are only divisible by n or m.
     *
     * @param a > 0
     * @param b > 0
     * @param n > 0
     * @param m > 0
     *
     * @return sum of numbers from the range that are only divisible by n or m
     */
    public static int sum(int a, int b, int n, int m) {
        return IntStream.rangeClosed(a, b).filter(i -> i % m == 0 || i % n == 0).sum();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println(sum(a, b, n, m));
    }
}