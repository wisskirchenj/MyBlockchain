package topics.patterns.decorator.pizza;

class Ham extends Decorator {
    private Pizza pizza;

    Ham(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Ham";
    }

    @Override
    double cost() {
        return 1.0 + pizza.cost();
    }
}
