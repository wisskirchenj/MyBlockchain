package topics.streams.reductionmethods;

import java.util.Scanner;
import java.util.stream.LongStream;

/**
 * Calculating factorials
 * Many java developers wrote methods to calculate a factorial value using a recursive or iterative algorithm. It's time to do it with streams.
 *
 * The factorial of n is calculated by the product of integer number from 1 to n (inclusive). The factorial of 0 is equal to 1.
 */
public class Factorials {
    /**
     * Calculates the factorial of the given number n
     *
     * @param n >= 0
     *
     * @return factorial value
     */
    public static long factorial(long n) {
        return LongStream.rangeClosed(1, n).reduce((k, l) -> k * l).orElse(1);
    }

    // Don't change the code below
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long n = Integer.parseInt(scanner.nextLine().trim());

        System.out.println(factorial(n));
    }
}
