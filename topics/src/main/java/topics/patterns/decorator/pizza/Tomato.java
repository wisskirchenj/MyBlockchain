package topics.patterns.decorator.pizza;

class Tomato extends Decorator {

    private Pizza pizza;

    Tomato(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Tomato";
    }

    @Override
    double cost() {
        return .09 + pizza.cost();
    }
}
