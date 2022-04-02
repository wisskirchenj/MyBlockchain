package topics.streams.streampipelines;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Using a stream many times.
 * Sometimes after mapping and filtering a stream, you need to use it more than one time. For example, find the maximum
 * and count all the elements of a resulted sequence. But it is impossible to do using standard stream API.
 *
 * In this practice, you need to implement a method that solves this problem. The method should save the stream and
 * create a supplier, that can return this stream over and over again.
 */
public class StreamReuse<T> {

    private List<T> streamList = null;

    public Supplier<Stream<T>> saveStream(Stream<T> stream) {
        if (streamList == null) {
            streamList = stream.collect(Collectors.toList());
        }
        return () -> streamList.stream();
    }
}
