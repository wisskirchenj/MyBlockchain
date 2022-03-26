package topics.encapsulatingobjcreation.clocks;

/**
 * There is a hierarchy of clocks with the base interface Clock and the class ClockFactory to produce instances.
 * Implement the method produce of the factory. It should return a clock according to the specified type string:
 * "SAND" is for SandClock;
 * "DIGITAL" is for DigitalClock;
 * "MECH" is for MechanicalClock.
 * The single constructor of the factory takes the boolean parameter produceToyClock. It determines what the factory
 * does when an unsuitable type of clock is passed. If it is true, the factory should produce an instance of ToyClock,
 * otherwise, return null.
 */
public class ClockFactory {

    private boolean produceToyClock;

    public ClockFactory(boolean produceToyClock) {
        this.produceToyClock = produceToyClock;
    }

    /**
     * It produces a clock according to a specified type: SAND, DIGITAL or MECH.
     * If some other type is passed, the method produces ToyClock.
     */
    public Clock produce(String type) {
        // write your code here
        if ("SAND".equals(type)) {
            return new SandClock();
        } else if ("DIGITAL".equals(type)) {
            return new DigitalClock();
        } else if ("MECH".equals(type)) {
            return new MechanicalClock();
        } else if (produceToyClock) {
            return new ToyClock();
        }
        return null;
    }
}

