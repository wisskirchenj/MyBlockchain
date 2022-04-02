package topics.streams.groupingcollectors;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Palindrome or not
 * Write a collector that partitions all words in a stream into two groups: palindromes (true) and usual words (false).
 * The collector should return Map<Boolean, List<String>> . All input words will be in the same case (lower).
 *
 * Let's remind, a palindrome is a word or phrase which reads the same backward or forward, such as noon or level.
 * In our case, a palindrome is always a word.
 */
public class PalindromeExtractor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split(" ");

        Map<Boolean, List<String>> palindromeOrNo = Arrays.stream(words)
                .collect(Collectors.partitioningBy(w -> w.contentEquals(new StringBuilder(w).reverse())));

        palindromeOrNo = new LinkedHashMap<>(palindromeOrNo);

        System.out.println(palindromeOrNo);
    }
}
