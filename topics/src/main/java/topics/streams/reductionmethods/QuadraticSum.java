package topics.streams.reductionmethods;

import java.util.stream.LongStream;

/**
 * Range quadratic sum
 * Implement the provided method rangeQuadraticSum that takes range borders (fromIncl is inclusive, toExcl is exclusive)
 * and calculates the sum of the squares of the elements which belong to the range.
 */
public class QuadraticSum {
    public static long rangeQuadraticSum(int fromIncl, int toExcl) {
        return LongStream.range(fromIncl, toExcl).map(l -> l * l).sum();
    }
}
