package topics.patterns.facade;

/**
 * Your computer is some kind of a facade for you as a user, or, as developers put it, a client. Let's create a simple ComputerFacade
 * with Processor, Monitor and Keyboard subsystems. When you turn on this computer, the Processor will start first, then the Monitor,
 * and finally, the Keyboard. But be careful because your keyboard has a backlight feature, and the order of turning the elements off
 * should be correct!
 */
class ComputerFacade {
    private final Processor processor;
    private final Monitor monitor;
    private final Keyboard keyboard;

    public ComputerFacade(Processor processor, Monitor monitor, Keyboard keyboard) {
        this.processor = processor;
        this.monitor = monitor;
        this.keyboard = keyboard;
    }

    public void turnOnComputer() {
        processor.on();
        monitor.on();
        keyboard.on();
    }

    public void turnOffComputer() {
        keyboard.off();
        monitor.off();
        processor.off();
    }
}

