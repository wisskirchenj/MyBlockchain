package topics.streams.mapandflatmap;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Maximum of the absolute values
 * Find the maximum absolute value in the array of numbers.
 */
public class MaxAbsValue {
    /**
     * Returns the largest absolute value in the array of numbers.
     *
     * @param numbers the input array of String integer numbers
     * @return the maximum integer absolute value in the array
     */
    public static int maxAbsValue(String[] numbers) {
        return Arrays.stream(numbers).map(Integer::parseInt).mapToInt(Math::abs).max().orElseThrow();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(maxAbsValue(scanner.nextLine().split("\\s+")));
    }
}