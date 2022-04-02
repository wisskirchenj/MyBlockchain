package topics.patterns.decorator.pizza;

class Vegan extends Pizza {

    Vegan() {
        description = "Vegan";
    }

    @Override
    double cost() {
        return 4.99;
    }
}
