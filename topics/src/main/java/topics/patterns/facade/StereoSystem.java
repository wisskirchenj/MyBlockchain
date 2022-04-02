package topics.patterns.facade;

class StereoSystem {
    private final String description = "StereoSystem";
    private String favoriteSong;

    public void on() {
        System.out.println(description + " on");
        turnOnFavoriteSong();
    }

    public void off() {
        System.out.println(description + " off");
    }

    private void turnOnFavoriteSong() {
        System.out.print("Favorite song is playing! ");
        System.out.println(favoriteSong);
    }

    public void setFavoriteSong(String favoriteSong) {
        this.favoriteSong = favoriteSong;
    }
}
