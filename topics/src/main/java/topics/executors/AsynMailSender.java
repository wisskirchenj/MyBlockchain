package topics.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynMailSender {
    // Do not change it
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int messageCounts = scanner.nextInt();
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < messageCounts; ++i) {
            messages.add(scanner.next());
        }

        MailSender sender = new MockMailSender();
        asyncSend(sender, messages);
    }

    static void asyncSend(MailSender sender, List<String> messages) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        messages.forEach(message -> executorService.submit(() -> sender.send(message)));
        executorService.shutdown();
    }
}

