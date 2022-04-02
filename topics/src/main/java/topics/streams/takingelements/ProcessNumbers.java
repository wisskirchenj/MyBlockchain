package topics.streams.takingelements;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sorting and skipping numbers
 * In this exercise, you need to implement the processNumbers method that takes a collection of numbers and should sort
 * it and skip all numbers that are less than 10. The method must return a sorted list as the result.
 */
class ProcessNumbers {

    public static List<Integer> processNumbers(Collection<Integer> numbers) {
        return numbers.stream().sorted()
                .dropWhile(i -> i < 10).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Collection<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(HashSet::new));

        String result = processNumbers(numbers).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
