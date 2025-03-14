package test.thread;

import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Printer class that contains the print logic
class Printer {
     int number = 1;

    // Method for synchronized printing using wait() and notify()
    public synchronized void printSync(int threadId) {
        while (number <= 100) {
            if (number % 3 == threadId) {
                System.out.println(number);
                number++;
                notifyAll();
                try {
                    if (number <= 100) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method using ReentrantLock for better synchronization
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public void printReentrantLock1(int threadId) {
        lock.lock();
        try {
            while (number <= 100) {
                if (number % 3 == threadId) {
                    System.out.println(number);
                    number++;
                    Thread.yield(); // Yielding to allow other threads to run
                }
            }
        } finally {
            lock.unlock();
        }
    }

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

    // Method using ExecutorService and Runnable tasks
    public void printUsingExecutor(int threadId) {
        while (number <= 100) {
            synchronized (this) {
                if (number % 3 == threadId) {
                    System.out.println(number);
                    number++;
                    notifyAll();
                    try {
                        if (number <= 100) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Method using CyclicBarrier
    public synchronized void printCyclicBarrier(int threadId, CyclicBarrier barrier) {
        try {
            barrier.await();  // All threads wait at this barrier point
            while (number <= 100) {
                if (number % 3 == threadId) {
                    System.out.println(number);
                    number++;
                    notifyAll();
                    if (number <= 100) {
                        wait();
                    }
                } else {
                    wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method using CountDownLatch
    public synchronized void printCountDownLatch(int threadId, CountDownLatch latch) {
        while (number <= 100) {
            if (number % 3 == threadId) {
                System.out.println(number);
                number++;
                latch.countDown(); // Signal the next thread
                try {
                    if (number <= 100) {
                        latch.await(); // Wait for the next thread to finish
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    latch.await(); // Wait for the previous thread
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// Main class with the different variations
public class PrintNumbers {

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();

        // 1. Using synchronized and wait/notify
        System.out.println("Using synchronized with wait/notify:");
        Printer finalPrinter = printer;
        Thread thread1 = new Thread(() -> {
            while (finalPrinter.number <= 100) finalPrinter.printSync(1); // Thread 1
        });
        Thread thread2 = new Thread(() -> {
            while (finalPrinter.number <= 100) finalPrinter.printSync(2); // Thread 2
        });
        Thread thread3 = new Thread(() -> {
            while (finalPrinter.number <= 100) finalPrinter.printSync(0); // Thread 3
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        
        // Reset the printer for next method
        printer = new Printer();
        
        // 2. Using ExecutorService (Runnable tasks)
        System.out.println("\nUsing ExecutorService:");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Printer finalPrinter1 = printer;
        executor.submit(() -> { while (finalPrinter1.number <= 100) finalPrinter1.printUsingExecutor(1); });
        executor.submit(() -> { while (finalPrinter1.number <= 100) finalPrinter1.printUsingExecutor(2); });
        executor.submit(() -> { while (finalPrinter1.number <= 100) finalPrinter1.printUsingExecutor(0); });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        
        // Reset the printer for next method
        printer = new Printer();

        // 3. Using ReentrantLock
        System.out.println("\nUsing ReentrantLock:");
        Printer finalPrinter2 = printer;
        thread1 = new Thread(() -> {
            while (finalPrinter2.number <= 100) finalPrinter2.printReentrantLock(1); // Thread 1
        });
        thread2 = new Thread(() -> {
            while (finalPrinter2.number <= 100) finalPrinter2.printReentrantLock(2); // Thread 2
        });
        thread3 = new Thread(() -> {
            while (finalPrinter2.number <= 100) finalPrinter2.printReentrantLock(0); // Thread 3
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        // Reset the printer for next method
        printer = new Printer();

        // 4. Using CyclicBarrier
        System.out.println("\nUsing CyclicBarrier:");
        CyclicBarrier barrier = new CyclicBarrier(3); // All threads wait for this barrier
        Printer finalPrinter3 = printer;
        thread1 = new Thread(() -> {
            while (finalPrinter3.number <= 100) finalPrinter3.printCyclicBarrier(1, barrier); // Thread 1
        });
        thread2 = new Thread(() -> {
            while (finalPrinter3.number <= 100) finalPrinter3.printCyclicBarrier(2, barrier); // Thread 2
        });
        thread3 = new Thread(() -> {
            while (finalPrinter3.number <= 100) finalPrinter3.printCyclicBarrier(0, barrier); // Thread 3
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        // Reset the printer for next method
        printer = new Printer();

        // 5. Using CountDownLatch
        System.out.println("\nUsing CountDownLatch:");
        CountDownLatch latch = new CountDownLatch(1); // Latch to synchronize threads
        Printer finalPrinter4 = printer;
        thread1 = new Thread(() -> {
            while (finalPrinter4.number <= 100) finalPrinter4.printCountDownLatch(1, latch); // Thread 1
        });
        thread2 = new Thread(() -> {
            while (finalPrinter4.number <= 100) finalPrinter4.printCountDownLatch(2, latch); // Thread 2
        });
        thread3 = new Thread(() -> {
            while (finalPrinter4.number <= 100) finalPrinter4.printCountDownLatch(0, latch); // Thread 3
        });
        thread1.start();
        thread2.start();
        thread3.start();
        latch.countDown(); // Allow the first thread to start
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
