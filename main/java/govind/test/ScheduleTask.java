package govind.test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ScheduleTask {



    public static void main(String[] args) {
        {
            System.out.println("Main thread: " + new Date());
        }
        ScheduledExecutorService scheduler1 = Executors.newSingleThreadScheduledExecutor();
                scheduler1.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    ThreadMain.sleep(100L);
                    ExecutorService service = Executors.newFixedThreadPool(2);
                    service.submit(ScheduleTask::fun);
                    service.shutdownNow();
                    System.out.println(service.awaitTermination(10, TimeUnit.MILLISECONDS));
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                System.out.println("scheduleAtFixedRate:    " + new Date());
            }
        }, 0, 3L , SECONDS);

        /*ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000L);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                System.out.println("scheduleWithFixedDelay: " + new Date());
            }
        }, 0, 3L , SECONDS);*/
    }

    private static void fun() {
   for(int i=0;i<10;i++)
   {
       System.out.println(i);
   }
    }
}
