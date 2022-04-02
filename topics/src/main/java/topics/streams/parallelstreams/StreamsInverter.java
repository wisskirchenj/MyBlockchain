package topics.streams.parallelstreams;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Inverting streams
 * There is a new challenge. Someone has not correctly chosen the types of streams in a program (parallel and sequential).
 *
 * You need to implement a method that inverses the state of every stream from the given list
 * (parallel → sequential and vice versa).
 * The method should return a new list of streams which are inverted.
 */
public class StreamsInverter {

    private static List<LongStream> invertedStreams(List<LongStream> streams) {
        return streams.stream()
                .map(stream -> stream.isParallel() ? stream.sequential() : stream.parallel())
                .collect(Collectors.toList());
    }

    /* Do not modify the code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Boolean> parallelFlags = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Boolean::parseBoolean)
                .collect(Collectors.toList());

        // :)
        List<LongStream> streams = Stream
                .iterate(0,
                        i -> i < parallelFlags.size(),
                        i -> i + 1)
                .map(i -> {
                    var stream = LongStream.of();
                    if (parallelFlags.get(i)) {
                        stream = stream.parallel();
                    }
                    return stream;
                }).collect(Collectors.toList());

        List<String> invertedParallelFlagsAsStrings =
                invertedStreams(streams).stream()
                        .map(LongStream::isParallel)
                        .map(Object::toString)
                        .collect(Collectors.toList());

        System.out.println(String.join(" ", invertedParallelFlagsAsStrings));
    }
}
