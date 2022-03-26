package topics.templatemethod;

class Steak extends Meal {

    @Override
    public void prepareIngredients() {
        System.out.println("Ingredients: beef steak, lemon, olive oil, salt, sugar");
    }

    @Override
    public void cook() {
        System.out.println("Fry the steak in the pan");
    }

    @Override
    public void cleanUp() {
        System.out.println("Push dishes in the sink and go coding");
    }
}
