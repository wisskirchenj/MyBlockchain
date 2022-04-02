package topics.patterns.abstractfactory.laptop;

class MacBook extends Laptop {

    MacBook(LaptopDetailsFactory detailsFactory) {
        display = detailsFactory.createDisplay();
        graphicCard = detailsFactory.createGraphicCard();
        processor = detailsFactory.createProcessor();
        ssd = detailsFactory.createSSD();
    }

    @Override
    public String getDescription() {
        return "This is a MacBook Pro 13\"\n" + super.toString();
    }
}
