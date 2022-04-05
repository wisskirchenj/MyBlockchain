package topics.currying;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * In this problem, you need to complete the implementations of two functions.
 *
 * The reduceIntOperator that accepts an initial value (seed) and a combiner function and returns a new function that
 * combines all values from the given integer range into a single integer value (it's a simple form of reduction) using
 * a given operator. The seed is used as the very first element in reducing.
 * Based on reduceIntOperator, implement the productOperator operator that multiplies integer values in the range.
 */
class CustomReducer {

    /**
     * The operator combines all values in the given range into one value
     * using combiner and initial value (seed)
     */
    public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator>
            reduceIntOperator = (i, f) -> (l, r) -> IntStream.rangeClosed(l, r).reduce(i, f);

    /**
     * The operator calculates the sum in the given range (inclusively)
     */
    public static final IntBinaryOperator sumOperator = reduceIntOperator.apply(0, (x, y) -> x + y);

    /**
     * The operator calculates the product in the given range (inclusively)
     */
    public static final IntBinaryOperator productOperator = reduceIntOperator.apply(1, (x, y) -> x * y);

    // Don't change the code below
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split(" ");

        int l = Integer.parseInt(values[0]);
        int r = Integer.parseInt(values[1]);

        int sumReducer = reduceIntOperator.apply(0, Integer::sum).applyAsInt(l, r);
        int sum = sumOperator.applyAsInt(l, r);

        int prodReducer = reduceIntOperator.apply(1, (x, y) -> x * y).applyAsInt(l, r);
        int prod = productOperator.applyAsInt(l, r);

        System.out.printf("%d %d %d %d", sumReducer, sum, prodReducer, prod);
    }
}
