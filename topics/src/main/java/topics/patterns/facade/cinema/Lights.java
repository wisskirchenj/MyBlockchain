package topics.patterns.facade.cinema;

class Lights {
    String description = "Lights";

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void dim(int level) {
        System.out.println(description + " dimming to " + level + "%");
    }

    public String toString() {
        return description;
    }
}
