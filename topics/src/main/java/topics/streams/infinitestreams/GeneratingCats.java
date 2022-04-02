package topics.streams.infinitestreams;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generating cats
 * You need to implement the generateNCats method to generate n identical cats. We are not kidding, we need exactly the
 * same cats. You may ask why this might be useful, but this it a quite common situation when writing unit tests when
 * you need objects with any values of their fields. This problem will be especially useful if you apply your knowledge
 * of method / constructor references here.
 */
public class GeneratingCats {

    public static List<Cat> generateNCats(int n) {
        return Stream.generate(Cat::new).limit(n).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Cat> cats = generateNCats(scanner.nextInt());

        System.out.println(cats.size());
    }
}

