package topics.customthreads;

import java.util.Arrays;

public class ThreadStarter {
    public static void startRunnables(Runnable[] runnables) {
        // implement the method
        Arrays.stream(runnables).forEach(runnable -> new Thread(runnable).start());
    }
}
