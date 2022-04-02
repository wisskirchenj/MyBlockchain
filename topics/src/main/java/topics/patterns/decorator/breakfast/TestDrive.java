package topics.patterns.decorator.breakfast;

/**
 * Breakfast Decorator
 * They say that breakfast is the most important meal of the day: if that's true, it better be fancy. In this task, we offer
 * you to decorate a slice of bread for your perfect developer's morning. Do not forget about calories!
 */
class TestDrive {
    public static void main(String[] args) {
        Bread bagel = new Bagel();

        bagel = new Butter(bagel);
        bagel = new Butter(bagel);
        bagel = new Ham(bagel);
        bagel = new Cheese(bagel);

        System.out.println(bagel);

        Bread bun = new Bun();
        bun = new Butter(bun);
        bun = new Jam(bun);
        bun = new Jam(bun);

        System.out.println(bun);
    }
}

