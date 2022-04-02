package topics.patterns.decorator.breakfast;

class Cheese extends Decorator {

    private Bread bread;

    Cheese(Bread bread) {
        this.bread = bread;
    }

    @Override
    String getDescription() {
        return bread.getDescription() + ", Cheese";
    }

    @Override
    int getKcal() {
        return bread.getKcal() + 40;
    }
}
