package topics.patterns.decorator.pizza;

abstract class Pizza {
    String description;

    String getDescription() {
        return description;
    }

    abstract double cost();
}
