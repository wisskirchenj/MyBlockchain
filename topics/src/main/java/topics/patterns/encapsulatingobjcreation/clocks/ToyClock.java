package topics.patterns.encapsulatingobjcreation.clocks;

class ToyClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...tick...");
    }
}
