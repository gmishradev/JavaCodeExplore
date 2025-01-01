package main.java.com.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintNNumberUsingTwoThreads {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        Semaphore semaphore = new Semaphore(2);
        final int[] a = {1};
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (integer) {
                            if (integer.get() > 100||a[0]>100) break;
                            System.out.println("odd thread ->>> " + a[0]);
                            a[0]++;
                           // System.out.println("thread one ->>>" + integer.get());
                            integer.incrementAndGet();
                            integer.notify();

                            integer.wait();
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (integer) {
                            if (integer.get() > 100||a[0]>100) break;
                            Thread.sleep(100);
                            System.out.println("even thread ->>> " + a[0]);
                            a[0]++;
                           // System.out.println("thread two ->>>> " + integer.get());

                            integer.incrementAndGet();
                            integer.notify();
                            integer.wait();
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }


                }
            }
        });

        t1.start();
        t2.start();
    }
}
