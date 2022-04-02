package topics.streams.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The product of squares.
 * Write a collector that evaluates the product of squares of integer numbers in a Stream<Integer>.
 */
public class CollectorProduct {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] values = scanner.nextLine().split(" ");

        List<Integer> numbers = new ArrayList<>();
        for (String val : values) {
            numbers.add(Integer.parseInt(val));
        }

        long val = numbers.stream().collect(
                Collectors.reducing(1L, Long::valueOf, (i, j) -> i * j * j)
        );

        System.out.println(val);
    }
}
