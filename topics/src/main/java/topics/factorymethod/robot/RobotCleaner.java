package topics.factorymethod.robot;

/**
 * Concrete Product - Robot Cleaner
 */
class RobotCleaner extends Robot {
    private String name;
    private String description;

    public RobotCleaner(String name, String description, int power) {
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
        return "cleaner-" + super.toString();
    }
}
