package govind.test;

import java.util.Date;

public class MyThread  extends  Thread {

    public static void main(String[] args) throws InterruptedException {

        ThreadMain t = new ThreadMain();t.start();
        t.join();
        System.out.println("Main stoped");

        MyRunnableThread myRunnableThread = new MyRunnableThread();
        Thread thread = new Thread(myRunnableThread);
        thread.start();

    }


    public static class MyRunnableThread implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnableThread: " + new Date());
        }
    }
}
