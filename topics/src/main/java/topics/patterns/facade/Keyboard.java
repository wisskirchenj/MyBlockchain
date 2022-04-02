package topics.patterns.facade;

class Keyboard {
    /* Your subsystem description */

    public void on() {
        System.out.println("Keyboard on");
        turnOnBacklight();
    }

    public void off() {
        System.out.println("Keyboard off");
        turnOffBacklight();
    }

    private void turnOnBacklight() {
        System.out.println("Backlight is turned on");
    }

    private void turnOffBacklight() {
        System.out.println("Backlight is turned off");
    }
}
