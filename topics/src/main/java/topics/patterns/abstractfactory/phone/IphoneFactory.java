package topics.patterns.abstractfactory.phone;

class IphoneFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        PhoneDetailsFactory detailsFactory = new IphoneDetailsFactory();

        return new Iphone(detailsFactory);
    }
}
