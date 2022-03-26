package topics.abstractfactory.laptop;

/**
 * Laptop factory
 * In this task, you will be the owner of the LaptopFactory. You've got two customers who want to buy Windows
 * and macOS laptops. You should provide these laptops by asking your factory to create them and writing some
 * more code in your TestDrive class.
 */
interface LaptopFactory {
    Laptop createComputer();
}

