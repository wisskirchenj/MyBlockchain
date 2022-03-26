package topics.localdatetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * program that reads a date-time pair and calculates how many hours have passed since the beginning of the year (1st January, 00:00).
 * Input data format
 * The first line contains a date-time pair in the format year-month-dayThour:minute:second.
 * Output data format
 * The line containing an integer number.
 */
public class HoursSinceStartOfYear {
    public static void main(String[] args) {
        // put your code here
        LocalDateTime dateTime = LocalDateTime.parse(new Scanner(System.in).nextLine());
        System.out.println(Math.abs(Duration.between(dateTime,
                LocalDateTime.of(dateTime.getYear(), 1, 1 , 0 , 0)).toHours()));
    }
}
