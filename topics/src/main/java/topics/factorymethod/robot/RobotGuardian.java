package topics.factorymethod.robot;

/**
 * Concrete Product - Robot Guardian
 */
class RobotGuardian extends Robot {
    private final String name;
    private final String description;

    public RobotGuardian(String name, String description, int power) {
        super(power);
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "guardian-" + super.toString();
    }
}
