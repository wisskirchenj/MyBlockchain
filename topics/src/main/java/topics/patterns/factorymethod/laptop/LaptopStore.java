package topics.patterns.factorymethod.laptop;

/**
 * The first part of the task is to imagine you are the boss of a LaptopStore. The real task though is the code: your
 * engineer should be able to create 17'', 15'' or 13'' laptops without any concrete details.
 */
class LaptopStore extends LaptopFactory {
    @Override
    Laptop createLaptop(String type) {
        return switch (type) {
            case "13 inch" -> new Laptop13("13 inch Laptop");
            case "15 inch" -> new Laptop15("15 inch Laptop");
            case "17 inch" -> new Laptop17("17 inch Laptop");
            default -> null;
        };
    }
}

