package main.java.com.executor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            for (int i = 0; i < 100; i++) {
                count++;
            }
        } finally {
            lock.unlock(); // Ensure the lock is released
        }
    }


    public void incrementWithTimeout() {
        boolean isLocked = false;
        try {
            // Attempt to acquire the lock with a timeout of 2 seconds
            isLocked = lock.tryLock(2, TimeUnit.SECONDS);
            if (isLocked) {
                // Critical section
                count++;
                System.out.println("Count incremented to: " + count);
            } else {
                // Lock was not available within the timeout
                System.out.println("Could not acquire lock within timeout");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
            System.out.println("Thread was interrupted");
        } finally {
            if (isLocked) {
                // Ensure the lock is released if it was acquired
                lock.unlock();
            }
        }
    }

    public void tryLockExample() {
        try {
            if (lock.tryLock()) { // Attempt to acquire the lock without blocking
                try {
                    // Critical section
                    for (int i = 0; i < 100; i++) {
                        count++;
                       Thread.sleep(10 *1000);
                    }
                    System.out.println(count);
                } finally {
                    lock.unlock(); // Ensure the lock is released
                }
            } else {
                // Lock was not available
                System.out.println("Could not acquire lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LockExample example = new LockExample();

        // Example of using Lock with threads

        Set<String> set = new HashSet<String>();
        set.add("Hello");
       String s1 =  new String("Hello");
       String s2 = new String("Hello");
        set.add(s1);
        set.add(s2);
        System.out.println(set.size());

        Thread t1 = new Thread(() -> example.increment());
        Thread t2 = new Thread(() -> example.increment());

        t1.start();
        t2.start();
        System.out.println("---------------");
         t1 = new Thread(() -> example.tryLockExample());
        t2 = new Thread(() -> example.tryLockExample());
        System.out.println("---------------");
        t1.start();
        t2.start();
    }
}
