package main.java.com.thread;

public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Uncaught Exception occurred on thread: " + t.getName());
        System.out.println("Exception message: " + e.getMessage());
    }
}