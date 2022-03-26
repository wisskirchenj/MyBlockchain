package topics.readingfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Program that finds the maximal number in this whitespace-separated integers containing file.
 */
public class MaxInFile {
    public static void main(String[] args) throws IOException {
        Arrays.stream(new String(Files.readAllBytes(Path.of("topics/src/main/resources/dataset_91007.txt")))
                .split("\\s+")).map(Integer::parseInt).max(Integer::compareTo).ifPresent(System.out::println);
    }
}
