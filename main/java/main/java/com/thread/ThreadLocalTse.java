package main.java.com.thread;

import java.text.SimpleDateFormat;

public class ThreadLocalTse {

    public static void main(String[] args) {
        ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return null;
            }
        };

        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread th, Throwable ex) {
                System.out.println("Uncaught exception: " + ex);
            }
        };
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Sleeping ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                System.out.println("Throwing exception ...");
                throw new RuntimeException();
            }
        };
        t.setUncaughtExceptionHandler(h);
        t.start();
    }
}
