package topics.patterns.abstractfactory.phone;

class Iphone extends Phone {
    Iphone(PhoneDetailsFactory detailsFactory) {
        camera = detailsFactory.createCamera();
        display = detailsFactory.createDisplay();
        processor = detailsFactory.createProcessor();
        security = detailsFactory.createSecurity();
    }

    @Override
    public String getDescription() {
        return "This is THE iPhoneXs\n" + super.toString();
    }
}
