package topics.patterns.facade;

/**
 * Imagine that you live in a SmartHouse that has three subsystems from the box: StereoSystem, Light,
 * and Bathroom. Your SmartHouse can do some pretty cool things when you come home:
 *
 * 1) Turn on your favorite song
 *
 * 2) Turn on the lights with your favorite color
 *
 * 3) Fill the bath with your favorite water level and temperature
 */
class SmartHouseFacade {
    StereoSystem stereoSystem;
    Bathroom bathroom;
    Lights lights;

    public SmartHouseFacade(StereoSystem stereoSystem, Bathroom bathroom, Lights lights) {
        this.stereoSystem = stereoSystem;
        this.bathroom = bathroom;
        this.lights = lights;
    }

    public void cameHome() {
        stereoSystem.on();
        bathroom.fill();
        lights.on();
    }

    public void leaveBathroomGoSleep() {
        bathroom.drain();
        stereoSystem.off();
        lights.off();
    }
}

