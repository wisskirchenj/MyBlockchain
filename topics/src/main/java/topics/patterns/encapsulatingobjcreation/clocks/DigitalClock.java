package topics.patterns.encapsulatingobjcreation.clocks;

class DigitalClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...pim...");
    }
}
