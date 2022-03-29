package topics.command.stock;

public class Main {
    public static void main(String[] args) {

        Broker broker = new Broker();
        Stock stock = new Stock();

        broker.setCommand(new BuyCommand(stock));
        broker.executeCommand();

        broker.setCommand(new SellCommand(stock));
        broker.executeCommand();
    }
}


