package topics.patterns.decorator.coffee;

import java.util.Locale;

/**
 * Coffee Decorator
 * Now your task is to make some Coffee. Your Decorators are different fillers; as a pro barista, you are provided with
 * Sugar, Milk, and Whipped cream.
 */
class TestDrive {
    public static void main(String[] args) throws InterruptedException {
        Coffee simpleEspresso = new Espresso();
        System.out.println(simpleEspresso.getDescription() + " $" + formatSum(simpleEspresso.cost()));

        Coffee espressoWithDecor = new Espresso();
        espressoWithDecor = new Milk(espressoWithDecor);
        espressoWithDecor = new Sugar(espressoWithDecor);
        System.out.println(espressoWithDecor.getDescription() + " $"
                + formatSum(espressoWithDecor.cost()));

        Coffee instantWithDecor = new InstantCoffee();
        instantWithDecor = new WhippedCream(instantWithDecor);
        instantWithDecor = new Sugar(instantWithDecor);
        instantWithDecor = new Sugar(instantWithDecor);
        instantWithDecor = new Sugar(instantWithDecor);
        System.out.println(instantWithDecor.getDescription() + " $" + formatSum(instantWithDecor.cost()));

        System.out.println("I'm drinking my " + instantWithDecor.getDescription());
        Thread.sleep(1500);
        System.out.println("-I want to add some Whipped Cream to my coffee. And don't make a new one! Just add Whipped Cream");
        Thread.sleep(1500);
        System.out.println("-Okay! But the final price will change");
        Thread.sleep(1500);
        System.out.println("-I understand");

        instantWithDecor = new WhippedCream(instantWithDecor);
        System.out.println(instantWithDecor.getDescription() + " $" + formatSum(instantWithDecor.cost()));
    }

    private static String formatSum(double sum) {
        return String.format(Locale.ROOT, "%.2f", sum);
    }
}

