package main.java.com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        executeThenThrowUnchecked();
    }

    public static void executeThenThrowUnchecked() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1, new AppThreadFactory());

        executorService.execute(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });

        executorService.shutdown();
    }
}
