package topics.patterns.decorator.breakfast;

class Jam extends Decorator {

    private Bread bread;

    Jam(Bread bread) {
        this.bread = bread;
    }

    @Override
    String getDescription() {
        return bread.getDescription() + ", Jam";
    }

    @Override
    int getKcal() {
        return bread.getKcal() + 120;
    }
}
