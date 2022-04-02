package topics.patterns.encapsulatingobjcreation.motor;

import java.lang.reflect.InvocationTargetException;

/**
 * Motor static factory
 * In the very heart of suburbia, there stood a motor factory; in the very heart of that factory worked a programmer.
 * Implement the static method make of the MotorStaticFactory that produces motors of different types. The method takes
 * three parameters: the type of a motor as a character, model as a string, and power as a long number. It should return
 * a new motor according to the type with initialized fields.
 *
 * Here is the correspondence between the passed type and the class of the motor: 'P' for pneumatic, 'H' for hydraulic,
 * 'E' for electric and 'W' for warp. Ignore the upper/lower case when creating motors, i.e. 'p' must work as well as 'P'.
 * If an invalid character is given, the method should return null.
 */
public class MotorStaticFactory {
    /**
     * It returns an initialized motor according to the specified type by the first character:
     * 'P' or 'p' - pneumatic, 'H' or 'h' - hydraulic, 'E' or 'e' - electric, 'W' or 'w' - warp.
     */
    public static Motor make(char type, String model, long power)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // write your code her
        Class<? extends Motor> motorClass;
        switch (Character.toUpperCase(type)) {
            case 'P':
                motorClass = PneumaticMotor.class;
                break;
            case 'H':
                motorClass = HydraulicMotor.class;
                break;
            case 'E':
                motorClass = Main.ElectricMotor.class;
                break;
            case 'W':
                motorClass = WarpDrive.class;
                break;
            default:
                return null;
        }
        return motorClass.getConstructor(String.class, long.class).newInstance(model, power);
    }
}

