package main.java.com.executor;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class MainT {
    private static final Set<String> accountsWarehouseInsightPolling = ConcurrentHashMap.newKeySet();
    private static volatile boolean shutdownRequested = false;

    public static void main(String[] args) {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        
        executor.scheduleAtFixedRate(() -> {
            if (shutdownRequested) {
                // Exit early if shutdown has been requested
                System.out.println("Shutdown requested. Exiting scheduled task...");
                return;
            }
            
            System.out.println("Wait for OS load the warehouse data... Begin");
            Long lastLoadTimestamp = System.currentTimeMillis();
            if (lastLoadTimestamp > 0) {
                System.out.println("Wait for OS load the warehouse data... End");
                try {
                    System.out.println("Polling warehouse insights from account={}, and schema={}");
                    accountsWarehouseInsightPolling.add("internal");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                try {
                    System.out.println("Generating warehouse right-size insight for account={}, and schema={}");
                    for (int i = 0; i < 10000; i++) {
                        System.out.println(i);
                    }
                    System.out.println("Generated warehouse right-size insight for account={}, and schema={}");
                } catch (Exception re) {
                    re.printStackTrace();
                }
                
                if (allAccountsPolled(accountsWarehouseInsightPolling)) {
                    requestShutdown(executor);
                }
            }
        }, 0, 0, TimeUnit.SECONDS);

        // Simulate external control to request shutdown
        new Thread(() -> {
            try {
                Thread.sleep(60000); // Wait for some time before requesting shutdown
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            requestShutdown(executor);
        }).start();
    }

    private static boolean allAccountsPolled(Set<String> polledAccounts) {
        Set<String> accounts = ConcurrentHashMap.newKeySet();
        accounts.add("internal");
        return accounts.equals(polledAccounts);
    }

    private static void requestShutdown(ScheduledExecutorService executor) {
        if (shutdownRequested) {
            return; // Shutdown has already been requested
        }
        shutdownRequested = true;

        System.out.println("Requesting shutdown of the executor...");
        // Attempt to stop all actively executing tasks and halt the processing of waiting tasks
        List<Runnable> notExecutedTasks = ((ScheduledThreadPoolExecutor) executor).shutdownNow();
        
        // Check if there were tasks that were not executed
        if (!notExecutedTasks.isEmpty()) {
            System.out.println("The following tasks were not executed:");
            for (Runnable task : notExecutedTasks) {
                System.out.println(task.toString());
            }
        }
        
        // Optionally, wait for the executor to terminate
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Executor did not terminate in the specified time.");
                // Force shutdown or take further actions as needed
            }
        } catch (InterruptedException ie) {
            System.err.println("Interrupted while waiting for executor termination.");
            // Re-interrupt the current thread
            Thread.currentThread().interrupt();
        }
    }
}
