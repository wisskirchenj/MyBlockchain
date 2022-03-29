package topics.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class AsyncMessageSenderImpl implements AsyncMessageSender {
    private ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());
    private final int repeatFactor;

    public AsyncMessageSenderImpl(int repeatFactor) {
        this.repeatFactor = repeatFactor;
    }

    @Override
    public void sendMessages(Message[] messages) {
        for (Message msg : messages) {
            IntStream.range(0, repeatFactor).forEach(i -> executor.submit(() ->
                    System.out.printf("(%s>%s): %s%n", msg.from, msg.to, msg.text)));
        }
    }

    @Override
    public void stop() {
        executor.shutdown();
        try {
            while (!executor.awaitTermination(50, TimeUnit.MILLISECONDS)) {
                // do nothing
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
