package test.thread;

class OddThread extends Thread {
    private final Printers printer;

    public OddThread(Printers printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            printer.printOdd(i);
        }
    }
}

class EvenThread extends Thread {
    private final Printers printer;

    public EvenThread(Printers printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            printer.printEven(i);
        }
    }
}

class Printers {
    private boolean isOddTurn = true;

    // Synchronized method for printing odd numbers
    public synchronized void printOdd(int number) {
        // wait
        while (!isOddTurn) {
            try {
                wait();  // Wait for the even thread to print
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(number);
        isOddTurn = false;
        notify();  // Notify even thread to print
    }

    // Synchronized method for printing even numbers
    public synchronized void printEven(int number) {
        while (isOddTurn) {
            try {
                wait();  // Wait for the odd thread to print
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(number);
        isOddTurn = true;
        notify();  // Notify odd thread to print
    }
}

public class OddEvenExample {
    public static void main(String[] args) {
        Printers printer = new Printers();
        OddThread oddThread = new OddThread(printer);
        EvenThread evenThread = new EvenThread(printer);

        oddThread.start();
        evenThread.start();
    }
}
