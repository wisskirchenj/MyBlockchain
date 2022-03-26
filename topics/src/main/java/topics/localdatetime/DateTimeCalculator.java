package topics.localdatetime;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * program that changes the given point of time: adds a certain number of days and subtracts a few hours.
 * Input and output date-time like this "2017-12-31T22:30".
 * Input data format
 * The single line containing date-time and two numbers: days to add and hours to subtract. Input elements are separated by spaces.
 * Output data format
 * The output must contain only a date-time in the specified format.
 */
public class DateTimeCalculator {
    public static void main(String[] args) {
        // put your code here
        String[] tokens = new Scanner(System.in).nextLine().split("\\s+");
        System.out.println(LocalDateTime.parse(tokens[0]).plusDays(Integer.parseInt(tokens[1]))
                .minusHours(Integer.parseInt(tokens[2])));
    }
}
