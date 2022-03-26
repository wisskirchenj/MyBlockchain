package topics.abstractfactory.laptop;

class DellXPS extends Laptop {

    DellXPS(LaptopDetailsFactory detailsFactory) {
        display = detailsFactory.createDisplay();
        graphicCard = detailsFactory.createGraphicCard();
        processor = detailsFactory.createProcessor();
        ssd = detailsFactory.createSSD();
    }

    @Override
    public String getDescription() {
        return "This is a Dell XPS 9370\n" + super.toString();
    }
}
