package main.java.com.thread;

public class ThreadException {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside run method");
                throw new RuntimeException("exception");//can only throw uncaught exception
            }
        };
        Thread t = new Thread(runnable);
     /* Thread.UncaughtExceptionHandler uncaughtExceptionHandler =   new  Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread th, Throwable ex) {
                System.out.println("Uncaught exception: " + ex);
            }
        };*/

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("uncaught exception "+ t.getName() +e.getMessage());
            }
        };

        t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        try {
            t.start();
            t.join();
        }
        catch (RuntimeException | InterruptedException ex)
        {
            System.out.println( "runtime exception caught " +ex);
        }
        System.out.println("done");
    }


}
