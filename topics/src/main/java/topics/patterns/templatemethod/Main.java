package topics.patterns.templatemethod;

import java.util.Scanner;

/**
 * Template Method
 * Now, we'll add a new concrete class Sandwich.
 * Sandwich is different from Steak, so that's why you need to implement it.
 * The first line of the standard input is the concrete meal.
 * You must output the meal procedure.
 */
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String order = scanner.nextLine();
        scanner.close();
        Meal meal;
        if ("Sandwich".equals(order)) {
            meal = new Sandwich();
            meal.doMeal();
        } else if ("Steak".equals(order)) {
            meal = new Steak();
            meal.doMeal();
        } else {
            System.out.println("Error");
        }
    }
}
