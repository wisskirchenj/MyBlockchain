package topics.patterns.templatemethod;

class Sandwich extends Meal {
    // write your code here ...
    @Override
    public void prepareIngredients() {
        System.out.println("Ingredients: bacon, white bread, egg, cheese, mayonnaise, tomato");
    }

    @Override
    public void cook() {
        System.out.println("Paste ingredients between bread slices. Toast sandwich");
    }

    @Override
    public void cleanUp() {
        System.out.println("Lick fingers and go to sleep");
    }
}
