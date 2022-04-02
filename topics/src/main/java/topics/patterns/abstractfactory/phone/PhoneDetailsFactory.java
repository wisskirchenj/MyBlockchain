package topics.patterns.abstractfactory.phone;

interface PhoneDetailsFactory {
    Camera createCamera();

    Display createDisplay();

    Processor createProcessor();

    Security createSecurity();
}
