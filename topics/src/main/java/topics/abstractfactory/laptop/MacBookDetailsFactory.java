package topics.abstractfactory.laptop;

class MacBookDetailsFactory implements LaptopDetailsFactory {
    @Override
    public Display createDisplay() {
        return new MacBookDisplay();
    }

    @Override
    public GraphicCard createGraphicCard() {
        return new MacBookGraphicCard();
    }

    @Override
    public Processor createProcessor() {
        return new MacBookProcessor();
    }

    @Override
    public SSD createSSD() {
        return new MacBookSSD();
    }
}
