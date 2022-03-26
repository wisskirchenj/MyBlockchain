package topics.encapsulatingobjcreation.motor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        final Scanner scanner = new Scanner(System.in);
        final char type = scanner.next().charAt(0);
        final String model = scanner.next();
        final long power = scanner.nextLong();
        final Motor motor = MotorStaticFactory.make(type, model, power);
        if (motor == null) {
            System.out.println("null");
        } else {
            System.out.println(motor.getClass().getName() + " " + motor.model + " " + motor.power);
        }
    }

    static class ElectricMotor extends Motor {

        public ElectricMotor(String model, long power) {
            super(model, power);
        }
    }
}
