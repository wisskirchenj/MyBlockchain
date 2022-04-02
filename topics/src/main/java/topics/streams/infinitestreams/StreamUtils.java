package topics.streams.infinitestreams;

import java.util.stream.Stream;

/**
 * Powers of two.
 * Implement a method that returns a prepared stream of the first n powers of two starting from the 0 power, that is,
 * your output should start with 1.
 */
public class StreamUtils {

    public static Stream<Integer> generateStreamWithPowersOfTwo(int n) {
        return Stream.iterate(1, i -> 2 * i).limit(n);
    }
}
