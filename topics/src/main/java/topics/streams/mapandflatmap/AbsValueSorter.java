package topics.streams.mapandflatmap;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Sorting the absolute values
 * Write a code that returns an array of sorted integer absolute numbers of the given array. The elements should be sorted in ascending order.
 */
public class AbsValueSorter {

    /**
     * Returns the sorted array of absolute numbers in ascending order.
     *
     * @param numbers the input array of String integer numbers
     * @return the sorted array of integer absolute numbers
     */
    public static int[] sortedAbsNumbers(String[] numbers) {
        return Arrays.stream(numbers).mapToInt(Integer::parseInt).map(Math::abs).sorted().toArray();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Arrays.stream(sortedAbsNumbers(scanner.nextLine().split("\\s+")))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "))
        );
    }
}
