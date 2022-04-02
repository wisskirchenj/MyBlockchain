package topics.patterns.abstractfactory.phone;

class SamsungDetailsFactory implements PhoneDetailsFactory {
    @Override
    public Camera createCamera() {
        return new SamsungCamera();
    }

    @Override
    public Display createDisplay() {
        return new SamsungDisplay();
    }

    @Override
    public Processor createProcessor() {
        return new SamsungProcessor();
    }

    @Override
    public Security createSecurity() {
        return new SamsungSecurity();
    }
}
