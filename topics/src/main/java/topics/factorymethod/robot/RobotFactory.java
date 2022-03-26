package topics.factorymethod.robot;

/**
 * Let's expand our RobotFactory! We've added a new Robot type — the Guardian.
 * Provide the RobotGuardian class and implement a factory method in RobotFactory methods to create Robotinstances.
 */
class RobotFactory {

    /** Factory method */
    public Robot getRobot(RobotType type, String name, String description, int power) {
        switch (type) {
            case ROBOT_CLEANER:
                return new RobotCleaner(name, description, power);
            case ROBOT_GUARDIAN:
                return new RobotGuardian(name, description, power);
            default:
                return null;
        }
    }
}

