package topics.patterns.templatemethod;

abstract class Meal {

    /**
     * It provides template method of meal routine.
     */
    public void doMeal() {
        // write your code here ...
        prepareIngredients();
        cook();
        eat();
        cleanUp();
    }

    public abstract void prepareIngredients();

    public abstract void cook();

    public void eat() {
        System.out.println("That's good");
    }

    public abstract void cleanUp();
}
