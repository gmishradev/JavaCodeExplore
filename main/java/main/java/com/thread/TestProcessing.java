package main.java.com.thread;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestProcessing {


    static AtomicInteger atomicInteger  = new AtomicInteger();

    public void test() throws InterruptedException, ExecutionException {
        Set<Integer> integerSet = new HashSet<>();
        System.out.println("Runtime.getRuntime().availableProcessors() + : "+ Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 3000000; i++)
        {
            integerSet.add(i);
            if(isPrinted(integerSet, executorService)) {
                integerSet.clear();
            }
        }

        if (executorService.awaitTermination(24, TimeUnit.HOURS)) {
            System.out.println("Still waiting...");
            executorService.shutdownNow();
        }
        System.out.println("Exiting normally...");

    }

    private boolean isPrinted(Set<Integer> integerSet, ExecutorService executorService) throws ExecutionException, InterruptedException {
        if (integerSet.size() == 1000) {
            Set<Integer> copy = new HashSet<>(integerSet);
          Future<Boolean> future  =  executorService.submit(() -> print(copy));
          //  executorService.submit(() -> { print(copy); });
            return future.get();
        }
        return false;
    }

    private boolean print(Set<Integer> integerSet) {
        System.out.println("-------");
        integerSet.forEach(System.out::println);
        System.out.println( " integerSet.size()  :" + integerSet.size());
        atomicInteger.addAndGet(integerSet.size());
        System.out.println("atomicInteger.get()  " + atomicInteger.get());
        System.out.println("--------");

        return true;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new TestProcessing().test();

    }
}
