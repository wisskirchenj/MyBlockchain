package topics.patterns.functioncomposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;

/**
 * Write the disjunctAll method that accepts a list of IntPredicate objects and returns a single IntPredicate.
 * The result predicate is a disjunction of all input predicates.
 * Disjunction means that if any of the predicates would return true, the composed predicate should return true as well.
 * If the input list is empty, the resulted predicate must return false for any integer value (always false predicate).
 */
public class CombiningPredicates {

    /**
     * The method represents a disjunct operator for a list of predicates.
     * For an empty list it returns the always false predicate.
     */
    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        return predicates.stream().reduce(IntPredicate::or).orElse(i -> false);
    }

    // Don't change the code below
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] strings = scanner.nextLine().split(" ");

        List<Boolean> values = Arrays.stream(strings)
                .map(Boolean::parseBoolean).toList();

        List<IntPredicate> predicates = new ArrayList<>();
        values.forEach(v -> predicates.add(x -> v));

        System.out.println(disjunctAll(predicates).test(0));
    }
}
