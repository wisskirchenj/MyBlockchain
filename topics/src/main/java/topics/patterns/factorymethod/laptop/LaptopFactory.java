package topics.patterns.factorymethod.laptop;

abstract class LaptopFactory {

    abstract Laptop createLaptop(String type);

    Laptop orderLaptop(String type) throws InterruptedException {
        Laptop laptop = createLaptop(type);
        if (laptop == null) {
            System.out.println("Sorry, we are not able to create this kind of laptop\n");
            return null;
        }
        System.out.println("Making a " + laptop.getName());
        laptop.attachKeyboard();
        laptop.attachTrackpad();
        laptop.attachDisplay();
        Thread.sleep(1500L);
        System.out.println("Done a " + laptop.getName() + "\n");
        return laptop;
    }
}
