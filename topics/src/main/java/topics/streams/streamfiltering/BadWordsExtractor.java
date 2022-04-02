package topics.streams.streamfiltering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Bad words detecting
 * You need to implement a method that returns a prepared stream for detecting bad words. The method has two parameters:
 *
 * a text in which all words are divided by single whitespaces;
 * a list of bad words.
 * The returned stream should contain dictionary ordered (i.e., sorted lexicographically) elements that do not repeat.
 */
public class BadWordsExtractor {

    private static Stream<String> createBadWordsDetectingStream(String text,
                                                                List<String> badWords) {
        return Arrays.stream(text.split("\\s+")).filter(badWords::contains).distinct().sorted();
    }

    /* Do not change the code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(";");

        // the first part is a text
        String text = parts[0];

        // the second part is a bad words dictionary
        List<String> dict = parts.length > 1 ?
                Arrays.asList(parts[1].split(" ")) :
                Collections.singletonList("");

        System.out.println(createBadWordsDetectingStream(text, dict).collect(Collectors.toList()));
    }

}
