package topics.patterns.factorymethod.robot;

import java.util.Scanner;

class RobotDemo {
    private static final int CLEANER_POWER = 100;
    private static final int GUARDIAN_POWER = 200;

    public static void main(String[] args) {

        RobotFactory robotFactory = new RobotFactory();
        Scanner scanner = new Scanner(System.in);

        String nameCleaner = scanner.nextLine();
        Robot robotCleaner = robotFactory.getRobot(RobotType.ROBOT_CLEANER, nameCleaner,
                "Robot will clean my room and dry my socks",
                CLEANER_POWER);

        String nameGuardian = scanner.nextLine();
        Robot robotGuardian = robotFactory.getRobot(RobotType.ROBOT_GUARDIAN, nameGuardian,
                "Knight will secure my daughter while she sleeping",
                GUARDIAN_POWER);

        System.out.println(robotCleaner);
        System.out.println(robotGuardian);

        scanner.close();
    }
}
