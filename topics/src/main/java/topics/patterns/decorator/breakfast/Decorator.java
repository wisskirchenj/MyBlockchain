package topics.patterns.decorator.breakfast;

abstract class Decorator extends Bread {
    abstract String getDescription();

    abstract int getKcal();
}
