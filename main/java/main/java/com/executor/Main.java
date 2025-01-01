package main.java.com.executor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Set<String> accountsWarehouseInsightPolling = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Wait for OS load the warehouse data... Begin");
            //Wait till all the warehouse data gets loaded. First load all the warehouse data, and then load the
            // query_history. Once we can see query_history data, then all warehouse data gets loaded.
            Long lastLoadTimestamp = System.currentTimeMillis();
            if (lastLoadTimestamp > 0) {
                System.out.println("Wait for OS load the warehouse data... End");
                try {
                    System.out.println("Polling warehouse insights from account={}, and schema={} ");
                    /*  historyDmPProcessor.pollWarehouseInsights(internalId, schema); */
                    accountsWarehouseInsightPolling.add("internal");
                } catch (Throwable th) {
                    System.out.println("Exception occurred while polling data for {} from account : {}");
                }
                try {
                    System.out.println("Generating warehouse right-size insight for account={}, and schema={}");
                    // Warehouse feature
                    {
                        for (int i = 0; i < 10000; i++) {
                            System.out.println(i);
                        }
                    }
                    System.out.println("Generated warehouse right-size insight for account={}, and schema={}");
                } catch (Exception re) {
                    System.out.println("Exception occurred while polling data for Warehouse feature...");
                }
                if (allAccountsPolled(accountsWarehouseInsightPolling)) {
                    executor.shutdownNow();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private static boolean allAccountsPolled(Set<String> polledAccounts) {
        List<String> accounts = new ArrayList<>();
        accounts.add("internal");
        List<String> pollingCompleteAccounts = new ArrayList<>(polledAccounts);
        return accounts.equals(pollingCompleteAccounts);
    }
}
