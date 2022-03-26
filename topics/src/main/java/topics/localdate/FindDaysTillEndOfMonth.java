package topics.localdate;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * program that prints the n-th day from the end of a month.
 * The program must read the year, the month, and the remaining number of days till the end of the month from standard
 * input and then output the date.
 */
public class FindDaysTillEndOfMonth {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int days = scanner.nextInt();
        System.out.println(LocalDate.of(year, month, 1).plusMonths(1).minusDays(days));
    }
}
