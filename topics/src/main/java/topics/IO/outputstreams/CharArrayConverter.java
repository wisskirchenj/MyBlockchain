package topics.IO.outputstreams;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Implement the convert method that converts String[] to char[].
 */
public class CharArrayConverter {
    public static char[] convert(String[] words) throws IOException {
        // implement the method
        CharArrayWriter writer = new CharArrayWriter();
        Arrays.stream(words).forEach(writer::append);
        return writer.toCharArray();
    }
}
