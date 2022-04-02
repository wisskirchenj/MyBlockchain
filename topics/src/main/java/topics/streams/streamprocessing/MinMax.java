package topics.streams.streamprocessing;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Implement a method for finding min and max elements of a stream in accordance with a given comparator.
 *
 * The found elements pass to minMaxConsumer in the following way:
 *
 * minMaxConsumer.accept(min, max);
 * If the stream doesn't contain any elements, invoke:
 *
 * minMaxConsumer.accept(null, null);
 */
class MinMax {

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<? extends T> listOfStream = stream.toList();
        T min = listOfStream.stream().min(order).orElse(null);
        T max = listOfStream.stream().max(order).orElse(null);
        minMaxConsumer.accept(min, max);
    }
}
