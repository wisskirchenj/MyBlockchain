package topics.factorymethod.laptop;

class TestDrive {
    public static void main(String[] args) throws InterruptedException {
        LaptopStore laptopStore = new LaptopStore();

        Laptop laptop13 = laptopStore.orderLaptop("13 inch");
        Laptop laptop15 = laptopStore.orderLaptop("15 inch");
        Laptop laptop17 = laptopStore.orderLaptop("17 inch");

        System.out.println("Available laptops in the store:");
        System.out.println(laptop13);
        System.out.println(laptop15);
        System.out.println(laptop17);
    }
}
