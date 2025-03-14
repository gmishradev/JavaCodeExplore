package test.thread;

import java.util.concurrent.CountDownLatch;

class CountDownPrinter {
    int number = 1;

    public void print(int threadId, CountDownLatch latch) {
        while (number <= 100) {
            synchronized (this) {
                // Each thread prints numbers based on its threadId (0, 1, or 2)
                if (number % 3 == threadId) {
                    System.out.println(number);
                    number++;
                    latch.countDown(); // Decrease the count of latch
                    try {
                        if (number <= 100) {
                            latch.await(); // Wait for the other threads to finish their turn
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

public class CountDownPrintNumbers {
    public static void main(String[] args) throws InterruptedException {
        CountDownPrinter printer = new CountDownPrinter();
        CountDownLatch latch = new CountDownLatch(1); // Latch initialized with 1

        // Create three threads, each responsible for printing numbers with a specific remainder modulo 3
        Thread thread1 = new Thread(() -> {
            while (printer.number <= 100) {
                printer.print(1, latch); // Thread 1 prints numbers like 1, 4, 7, 10, ...
            }
        });

        Thread thread2 = new Thread(() -> {
            while (printer.number <= 100) {
                printer.print(2, latch); // Thread 2 prints numbers like 2, 5, 8, 11, ...
            }
        });

        Thread thread3 = new Thread(() -> {
            while (printer.number <= 100) {
                printer.print(0, latch); // Thread 3 prints numbers like 3, 6, 9, 12, ...
            }
        });

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Trigger the first thread to start by counting down the latch
        latch.countDown();

        // Wait for all threads to finish
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
