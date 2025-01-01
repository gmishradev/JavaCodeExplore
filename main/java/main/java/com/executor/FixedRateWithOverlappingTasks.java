package main.java.com.executor;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedRateWithOverlappingTasks {

    public static void main(String[] args) {
        // Create a ScheduledExecutorService with a single thread
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Define the task to be executed
        Runnable task = () -> {
            try {
                System.out.println("Task started at: " + System.currentTimeMillis());
                System.out.println(Thread.currentThread());
                Thread.sleep(1);
                for( int i = 0; i <1000; i++ ) {
                    System.out.print(Thread.currentThread()+""+i);
                }
                System.out.println("Task ended at: " + System.currentTimeMillis());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                System.err.println("Task interrupted");
            }
        };

        // Schedule the task to run at a fixed rate every 5 seconds
        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

        // Let the scheduler run for some time
        try {
            Thread.sleep(30000); // Let the scheduler run for 30 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Initiate graceful shutdown
        System.out.println("Initiating graceful shutdown...");
        //scheduler.shutdown(); // No new tasks will be accepted

        List<Runnable> tasks = scheduler.shutdownNow();
        System.out.println(tasks.size() + " tasks were accepted" + tasks);

        try {
            // Wait for the previously submitted tasks to complete
            if (!scheduler.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("Timeout reached, forcing shutdown...");
                scheduler.shutdownNow(); // Forcefully stop all tasks
                // Wait a bit for tasks to respond to the interruption
                if (!scheduler.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.err.println("Executor service did not terminate");
                }
            }
        } catch (InterruptedException e) {
            // Re-cancel if the current thread was interrupted while waiting
            scheduler.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        System.out.println("Shutdown completed.");
    }
}
