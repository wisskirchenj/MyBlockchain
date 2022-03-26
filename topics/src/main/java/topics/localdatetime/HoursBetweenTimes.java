package topics.localdatetime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * program that calculates how many whole hours are between the two date-time pairs of the same year.
 * Input data format
 * Two lines containing a date-time in a year-month-dayThour:minute format (without seconds and nanoseconds).
 */
public class HoursBetweenTimes {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDateTime datetime1 = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime datetime2 = LocalDateTime.parse(scanner.nextLine());
        System.out.println(ChronoUnit.HOURS.between(datetime1, datetime2));
    }
}