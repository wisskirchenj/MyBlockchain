package topics.singleton;

public class SimpleCounter {
    private static final SimpleCounter INSTANCE = new SimpleCounter();

    public int counter;

    private SimpleCounter() {
    }

    public static SimpleCounter getInstance() {
        return INSTANCE;
    }
}
