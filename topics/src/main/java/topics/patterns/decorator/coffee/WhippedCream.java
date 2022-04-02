package topics.patterns.decorator.coffee;

class WhippedCream extends Decorator {

    private Coffee coffee;

    public WhippedCream(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }

    @Override
    double cost() {
        return .10 + coffee.cost();
    }
}
