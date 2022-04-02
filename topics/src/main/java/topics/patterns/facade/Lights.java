package topics.patterns.facade;

class Lights {
    private String description = "Lights";
    private String favoriteColorTemperature;

    public void on() {
        System.out.println(description + " on");
        System.out.println("Color temperature is: " + favoriteColorTemperature);
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void setFavoriteColorTemperature(String favoriteColorTemperature) {
        this.favoriteColorTemperature = favoriteColorTemperature;
    }
}
