package topics.localdatetime;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * program that adds N minutes to a given date and time and prints out the resulting year, day of the year,
 * hours, minutes and seconds.The input date-time should look like "2017-12-31T22:30:15", the result date must be
 * similar to "2018 139 19:50:15" (year, day of the year, hours, minutes, seconds).
 * If the result has no seconds, the program must not print them.
 * Input data format
 * Two lines: the first one containing date-time, the second one containing a number of minutes to add.
 * Output data format
 * The single line must contain a string with the result.
 */
public class AddMinutes {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        long n = scanner.nextLong();
        dateTime = dateTime.plusMinutes(n);
        System.out.println(dateTime.getYear() + " " + dateTime.getDayOfYear() + " " +
                dateTime.toLocalTime());
    }
}
