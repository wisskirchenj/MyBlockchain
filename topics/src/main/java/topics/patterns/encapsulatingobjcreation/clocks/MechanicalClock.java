package topics.patterns.encapsulatingobjcreation.clocks;

class MechanicalClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...clang mechanism...");
    }
}
