package topics.patterns.abstractfactory.laptop;

interface LaptopDetailsFactory {
    Display createDisplay();

    GraphicCard createGraphicCard();

    Processor createProcessor();

    SSD createSSD();
}
