package topics.encapsulatingobjcreation.clocks;

class ToyClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...tick...");
    }
}
