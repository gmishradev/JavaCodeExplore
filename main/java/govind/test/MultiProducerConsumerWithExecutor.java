package govind.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MultiProducerConsumerWithExecutor {

    public static void main(String[] args) {
        // Shared resource: a blocking queue with capacity of 10
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Create an ExecutorService with a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(6);

        // Create and submit producer tasks
        for (int i = 0; i < 3; i++) {
            executor.submit(new Producer1(queue));
        }

        // Create and submit consumer tasks
        for (int i = 0; i < 3; i++) {
            executor.submit(new Consumer1(queue));
        }

        // Shutdown the executor gracefully
        executor.shutdown();

        try {
            // Wait for the executor to finish processing tasks
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

// Shared resource class with a blocking queue
class SharedResource1 {
    private final BlockingQueue<Integer> queue;

    public SharedResource1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }
}

// Producer class
class Producer1 implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                int item = (int) (Math.random() * 100); // Produce a random item
                queue.put(item); // Blocking call if the queue is full
                System.out.println("Produced: " + item);
                Thread.sleep(1000); // Simulate time taken to produce
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted.");
        }
    }
}

// Consumer class
class Consumer1 implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer item = queue.poll(2, TimeUnit.SECONDS); // Blocking call with timeout
                if (item != null) {
                    System.out.println("Consumed: " + item);
                } else {
                    System.out.println("Consumer timed out waiting for item.");
                }
                // Simulate time taken to consume
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted.");
        }
    }
}
