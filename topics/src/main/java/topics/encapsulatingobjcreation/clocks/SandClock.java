package topics.encapsulatingobjcreation.clocks;

class SandClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...sand noise...");
    }
}
