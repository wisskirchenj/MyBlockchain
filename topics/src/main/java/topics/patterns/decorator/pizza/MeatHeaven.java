package topics.patterns.decorator.pizza;

class MeatHeaven extends Pizza {

    MeatHeaven() {
        description = "MeatHeaven";
    }

    @Override
    double cost() {
        return 4.0;
    }
}
