package topics.streams.takingelements;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Secret codes
 * You've found a sequence of codes on the Internet in the HEX format (hexadecimal numbers). You have an assumption that
 * this sequence has a secret subsequence enclosed between "#0000" and "#FFFF". Apply your knowledge of Stream API to
 * extract all codes between them (excluding both "#0000" and "#FFFF").
 */
public class SecretCodes {

    private static List<String> extractCodes(List<String> codes) {
        // write your code here
        return codes.stream().dropWhile(c -> !"#0000".equals(c)).skip(1)
                .takeWhile(c -> !"#FFFF".equals(c)).collect(Collectors.toList());
    }

    /* Please do not modify the code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> codes = Arrays.stream(scanner.nextLine()
                        .split("\\s+"))
                .collect(Collectors.toList());

        System.out.println(String.join(" ", extractCodes(codes)));
    }
}