package topics.patterns.facade.cinema;

/**
 * When we come to the cinema, the first logical step is to get popcorn, so we need to turn on the PopcornPopper. After,
 * we want the facade to turn off the Lights and turn on the Projector. Your task is to write the watchMovie() and endMovie()
 * methods that should write the output according to our needs.
 */
class CinemaFacade {
    private PopcornPopper popcorn;
    private Lights lights;
    private Projector projector;

    public CinemaFacade(PopcornPopper popcorn, Lights lights, Projector projector) {
        this.popcorn = popcorn;
        this.lights = lights;
        this.projector = projector;
    }

    public void watchMovie() {
        System.out.println("Get ready to watch a movie...");
        popcorn.on();
        popcorn.pop();
        lights.off();
        projector.on();
    }

    public void endMovie() {
        popcorn.off();
        lights.on();
        projector.off();
    }
}

