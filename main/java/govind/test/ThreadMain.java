package govind.test;

import java.util.concurrent.TimeUnit;

public class ThreadMain extends Thread {

    public static void main(String[] args) throws InterruptedException {
        ThreadMain t = new ThreadMain();
        t.start();
        try {
            TimeUnit.MILLISECONDS.sleep(65000);
        } catch (InterruptedException interruptedException) {
            System.out.println(interruptedException.getMessage());
        }
        t.interrupt();
        if(t.isInterrupted())
        {
            throw new  InterruptedException ("test1");
        }
    }
    // create two

    // create deadlock using two thread
}
