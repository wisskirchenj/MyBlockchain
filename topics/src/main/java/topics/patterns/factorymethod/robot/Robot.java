package topics.patterns.factorymethod.robot;

/**
 * Product - Robot
 */
abstract class Robot {
    private final int power;

    Robot(int power) {
        this.power = power;
    }

    public abstract String getName();

    public abstract String getDescription();

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "robot: {\n\t" +
                "name : " + getName() + "\n\t" +
                "description : " + getDescription() + "\n\t" +
                "power : " + getPower() + "\n}";
    }
}
