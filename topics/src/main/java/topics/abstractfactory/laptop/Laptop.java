package topics.abstractfactory.laptop;

abstract class Laptop {
    Display display;
    GraphicCard graphicCard;
    Processor processor;
    SSD ssd;

    abstract String getDescription();

    @Override
    public String toString() {
        return "Display: " + display.toString() + "\n"
                + "GraphicCard: " + graphicCard.toString() + "\n"
                + "Processor: " + processor.toString() + "\n"
                + "SSD: " + ssd.toString();
    }
}
