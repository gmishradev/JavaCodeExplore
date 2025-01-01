package main.java.com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorService1 {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futureList = submitTasks(service);
        ExecutorService service1 = Executors.newCachedThreadPool();
        List<Future<Integer>> futureListcached = submitTasks(service1);
        ExecutorService executorService2 = Executors.newWorkStealingPool();
        List<Future<Integer>> futureListStealing = submitTasks(executorService2);
    }

    private static List<Future<Integer>> submitTasks(ExecutorService service) {
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<Integer> task = () -> new Random().nextInt();
            Future<Integer> future = service.submit(task);
            futureList.add(future);
        }
        return futureList;
    }
}
