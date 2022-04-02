package topics.patterns.facade;

class Bathroom {
    private final String description = "The tub";
    private String favoriteTemperature;
    private String favoriteLevel;

    public void fill() {
        System.out.println(description + " is being filled");
        System.out.println("Temperature: " + favoriteTemperature);
        System.out.println("Water level: " + favoriteLevel);
    }

    public void drain() {
        System.out.println(description + " is being drained");
    }

    public void setFavoriteTemperature(String favoriteTemperature) {
        this.favoriteTemperature = favoriteTemperature;
    }

    public void setFavoriteLevel(String favoriteLevel) {
        this.favoriteLevel = favoriteLevel;
    }
}
