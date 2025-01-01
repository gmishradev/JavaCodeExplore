package main.java.com.thread;

import java.util.concurrent.ThreadFactory;

public class AppThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        final Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new AppExceptionHandler());
        return thread;
    }
}
