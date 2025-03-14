package test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class NumberPrinter {
    int number = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printReentrantLock(int threadId) {
        lock.lock();
        try {
            while (number <= 100) {
                // Thread checks if it's its turn to print
                if (number % 3 == threadId) {
                    System.out.println(number);
                    number++;
                    condition.signalAll(); // Wake up other threads
                    try {
                        if (number <= 100) {
                            condition.await(); // Wait for the next thread
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        condition.await(); // Wait if it's not this thread's turn
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock(); // Always unlock after we're done
        }
    }
}

public class NumberPrintingApp {
    public static void main(String[] args) throws InterruptedException {
        NumberPrinter numberPrinter = new NumberPrinter();

        // Thread 1 prints numbers like 1, 4, 7, 10, ...
        Thread thread1 = new Thread(() -> {
            while (numberPrinter.number <= 100) {
                numberPrinter.printReentrantLock(1);
            }
        });

        // Thread 2 prints numbers like 2, 5, 8, 11, ...
        Thread thread2 = new Thread(() -> {
            while (numberPrinter.number <= 100) {
                numberPrinter.printReentrantLock(2);
            }
        });

        // Thread 3 prints numbers like 3, 6, 9, 12, ...
        Thread thread3 = new Thread(() -> {
            while (numberPrinter.number <= 100) {
                numberPrinter.printReentrantLock(0);
            }
        });

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
