package topics.patterns.decorator.coffee;

class Milk extends Decorator {

    private Coffee coffee;

    Milk(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    double cost() {
        return .13 + coffee.cost();
    }
}
