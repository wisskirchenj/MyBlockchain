package topics.abstractfactory.phone;

class SamsungFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        PhoneDetailsFactory detailsFactory = new SamsungDetailsFactory();

        return new Samsung(detailsFactory);
    }
}
