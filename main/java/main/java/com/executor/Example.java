package main.java.com.executor;

public class Example {
    private static int staticCount = 0;
    static {
        synchronized (Example.class) {
            // This block is synchronized on the class-level lock
            staticCount++;
            System.out.println("Static block executed, count = " + staticCount);
        }
    }


    public static synchronized void incrementStaticCount() {
        staticCount++;
        System.out.println("incrementStaticCount block executed, count = " + staticCount);
    }

    public static void main(String[] args) throws InterruptedException {
        // Threads calling static synchronized method
        Thread t1 = new Thread(() -> Example.incrementStaticCount());
        Thread t2 = new Thread(() -> Example.incrementStaticCount());
        Class<?> exampleClass = Example.class;
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(staticCount);

    }
}
