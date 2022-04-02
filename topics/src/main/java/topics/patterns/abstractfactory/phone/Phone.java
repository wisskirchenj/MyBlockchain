package topics.patterns.abstractfactory.phone;

abstract class Phone {
    Camera camera;
    Display display;
    Processor processor;
    Security security;

    public abstract String getDescription();

    public String toString() {
        return "Camera: " + camera.toString() + "\n"
                + "Display: " + display.toString() + "\n"
                + "Processor: " + processor.toString() + "\n"
                + "Security: " + security.toString();
    }
}
