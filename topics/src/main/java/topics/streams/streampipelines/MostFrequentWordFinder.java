package topics.streams.streampipelines;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a program that reads a text (in the UTF-8) from the standard input. The program must count the frequency of
 * words in the text and print the 10 most frequent words.
 *
 * A word is a sequence of characters consisting only of digits and letters. For example, the string
 * "Functions bring happiness!" has three words: "Functions", "bring", "happiness".
 *
 * The counting words should be case-insensitive, i.e. "Functions", "functions" and "FUNCTIONS" are the same word.
 * Output words in the lower case.
 *
 * If the text has less than 10 unique words, output as many as there are.
 * If some words in the text have the same frequency, sort them according to the lexicographical order.
 */
public class MostFrequentWordFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = Arrays.stream(scanner.nextLine().split("\\W+"))
                .map(String::toLowerCase).toList();
        Map<Integer, List<String>> map = list.stream()
                .collect(Collectors.groupingBy(e -> Collections.frequency(list, e)));
        map.entrySet().stream()
                .sorted((me, mf) -> mf.getKey().compareTo(me.getKey()))
                .flatMap(me -> me.getValue().stream().sorted())
                .distinct()
                .limit(10)
                .forEach(System.out::println);
    }
}
