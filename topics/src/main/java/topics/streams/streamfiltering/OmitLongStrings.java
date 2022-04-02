package topics.streams.streamfiltering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Omitting long strings
 * Implement the omitLongStrings method that takes a list of strings and returns a stream that consists of the elements
 * from a given list that are less than 4 characters long.
 */
public class OmitLongStrings {

    private static Stream<String> omitLongStrings(List<String> strings) {
        return strings.stream().filter(str -> str.length() < 4);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        List<String> list = new ArrayList<>(Arrays.asList(str.split(" ")));
        omitLongStrings(list).forEach(System.out::println);
    }
}
