package topics.executors;

import java.util.stream.IntStream;

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        boolean prime = IntStream.range(2, (int) Math.floor(Math.sqrt(number)) + 1)
                .noneMatch(n -> number % n == 0);
        prime |= number == 2;
        prime &= number != 1;
        if (prime) {
            System.out.println(number);
        }
    }
}
