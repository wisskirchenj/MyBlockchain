package topics.localdate;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * program that prints all dates of the given year with a specified offset applied.
 * It should read a starting date and a value of an offset (in days).
 * In the output, dates should be printed in ascending order with the starting date included.
 * Do not output the dates from the next year.
 */
public class DaysOfYearWithOffset {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDate.parse(scanner.nextLine());
        int offset = scanner.nextInt();
        int year = date.getYear();
        while (date.getYear() == year) {
            System.out.println(date);
            date = date.plusDays(offset);
        }
    }
}
