package topics.encapsulatingobjcreation.clocks;

class DigitalClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...pim...");
    }
}
