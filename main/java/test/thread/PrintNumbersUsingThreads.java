package test.thread;

import java.util.concurrent.CyclicBarrier;

public class PrintNumbersUsingThreads {

    private static final int MAX = 100;
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) {
        // Create a CyclicBarrier that waits for 3 threads to reach the barrier
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, new Runnable() {
            @Override
            public void run() {
                // This will run after every thread reaches the barrier
            }
        });

        // Create and start threads
        Thread thread1 = new Thread(new NumberPrinter(1, 3, barrier));
        Thread thread2 = new Thread(new NumberPrinter(2, 3, barrier));
        Thread thread3 = new Thread(new NumberPrinter(3, 3, barrier));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class NumberPrinter implements Runnable {
        private int start;
        private int step;
        private CyclicBarrier barrier;

        public NumberPrinter(int start, int step, CyclicBarrier barrier) {
            this.start = start;
            this.step = step;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for (int i = start; i <= MAX; i += step) {
                try {
                    // Print the number
                    System.out.println(i);

                    // Wait for other threads at the barrier before continuing
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
