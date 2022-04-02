package topics.patterns.abstractfactory.laptop;

class MacBookFactory implements LaptopFactory {
    @Override
    public Laptop createComputer() {
        LaptopDetailsFactory detailsFactory = new MacBookDetailsFactory();

        return new MacBook(detailsFactory);
    }
}
