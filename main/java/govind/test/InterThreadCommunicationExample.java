package govind.test;

public class InterThreadCommunicationExample {
    
    public static void main(String[] args) {
        // Shared resource
        SharedResource resource = new SharedResource();

        // Create and start the producer thread
        Thread producer = new Thread(new Producer(resource));
        producer.start();

        // Create and start the consumer thread
        Thread consumer = new Thread(new Consumer(resource));
        consumer.start();
    }
}

// Shared resource class
class SharedResource {
    private boolean available = false;
    private int data;

    // Producer thread will call this method to produce data
    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait(); // Wait until the consumer consumes the data
        }
        data = value;
        available = true;
        System.out.println("Produced: " + data);
        notify(); // Notify the consumer that data is available
    }

    // Consumer thread will call this method to consume data
    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait(); // Wait until the producer produces data
        }
        System.out.println("Consumed: " + data);
        available = false;
        notify(); // Notify the producer that the data has been consumed
    }
}

// Producer class
class Producer implements Runnable {
    private final SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                resource.produce(i);
                Thread.sleep(1000); // Simulate time taken to produce
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted.");
        }
    }
}

// Consumer class
class Consumer implements Runnable {
    private final SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                resource.consume();
                Thread.sleep(1500); // Simulate time taken to consume
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted.");
        }
    }
}
