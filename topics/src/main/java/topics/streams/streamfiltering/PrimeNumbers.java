package topics.streams.streamfiltering;

import java.util.Scanner;
import java.util.stream.LongStream;

/**
 * Check if a number is prime
 * You need to implement the isPrime method to check whether the input number is prime or not.
 *
 * It's guaranteed that input value is always greater than 1 (i.e. 2, 3, 4, 5, ....). Use the provided template for your method.
 */
class PrimeNumbers {

    /**
     * Checking if a number is prime
     *
     * @param number to test >= 2
     * @return true if number is prime else false
     */
    private static boolean isPrime(long number) {
        return LongStream.rangeClosed(2, number / 2).noneMatch(l -> number % l == 0);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine().trim();

        int n = Integer.parseInt(line);

        System.out.println(isPrime(n) ? "True" : "False");
    }
}
