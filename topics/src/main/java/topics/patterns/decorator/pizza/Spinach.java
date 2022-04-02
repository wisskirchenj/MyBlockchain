package topics.patterns.decorator.pizza;

class Spinach extends Decorator {

    private Pizza pizza;

    Spinach(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Spinach";
    }

    @Override
    double cost() {
        return .09 + pizza.cost();
    }
}
