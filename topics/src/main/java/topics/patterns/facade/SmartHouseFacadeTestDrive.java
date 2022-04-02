package topics.patterns.facade;

class SmartHouseFacadeTestDrive {
    public static void main(String[] args) {
        StereoSystem stereoSystem = new StereoSystem();
        Bathroom bathroom = new Bathroom();
        Lights lights = new Lights();

        SmartHouseFacade smartHouseFacade = new SmartHouseFacade(stereoSystem, bathroom, lights);

        lights.setFavoriteColorTemperature("Calming blue");
        stereoSystem.setFavoriteSong("Queen - Killer Queen");
        bathroom.setFavoriteTemperature("35℃");
        bathroom.setFavoriteLevel("60%");

        smartHouseFacade.cameHome();
        smartHouseFacade.leaveBathroomGoSleep();
    }
}
