package test.thread;

import com.code.ll.LinkedListTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreeThread {
    static volatile int number = 1;
    static volatile List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread() {
            public void run() {
                while (number < 101) {
                    synchronized (this) {
                        if (number % 3 == 0) {
                            System.out.println(currentThread() + " t1---> " + number);
                            list.add(number);
                            number = number + 1;
                        }
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                while (number < 101) {
                    synchronized (this) {
                        if (number % 3 == 1) {
                            System.out.println(currentThread() + " t2---> " + number);
                            list.add(number);
                            number = number + 1;
                        }
                    }
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                while (number < 101) {
                    synchronized (this) {
                        if (number % 3 == 2) {
                            System.out.println(currentThread() + " t3---> " + number);
                            list.add(number);
                            number = number + 1;
                        }
                    }
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(list);
        System.out.println(list.size());
    }


}
