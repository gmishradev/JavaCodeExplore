package main.java.com;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTest implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ThreadTest t = new ThreadTest();
        /*Thread t1= new Thread(t);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(1000);*/
        //t1.interrupt();

        /*if(t1.isInterrupted())
        {
            throw new  InterruptedException ("test1");
        }*/

        tableDataCollector(t);
        System.out.println("Govind ----");


    }

    private static void tableDataCollector(ThreadTest t) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(t);
    }

  /*  @Override //4.7.2.0
    public void run() {
        *//* do an initial polling to all data sources *//*
        try {
            while (!Thread.interrupted()) {
                pollAllMetastoreTypes();
                LOG.info("sleeping for {} minutes",pollInterval);
                TimeUnit.MINUTES.sleep(pollInterval);
                LOG.info("awake after sleeping for {} minutes",pollInterval);

            }
        } catch (Exception | LinkageError exception) {
            final String msg = "Unexpected exception: " + exception;
            LOG.error(msg, exception);
            MetaStatus.instance().error(msg, exception);
        } finally {
            // Close connections to metastore.
            hiveMetastoreConnector.closeMetastoreConnections();
        }
    }*/

   /* @Override // 4.7.3.0
    public void run() {
        while (!Thread.interrupted()) {
           // pollAllMetastoreTypes();
           // LOG.info("sleeping for {} minutes", pollInterval);
            TimeUnit.MINUTES.sleep(10);
          //  LOG.info("awake after sleeping for {} minutes", pollInterval);
        }
    } catch (Exception exception) {
        if (exception instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        LOG.error("Exception message: {}", exception.getMessage(), exception);
        MetaStatus.instance().error("Exception ", exception);
    } catch (Throwable throwable) {
        LOG.error("Unexpected exception message : {} ", throwable.getMessage(), throwable);
        MetaStatus.instance().error("Unexpected exception", throwable);
    } finally {
        metastoreConnector.closeMetastoreConnections();
    }*/

    @Override
    public void run() {

            while (!Thread.interrupted()) {
                try {
                    new Main().fun();
                      TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println("loop");
                    // LOG.info("awake after sleeping for {} minutes", pollInterval);
                } catch (Exception exception) {
                    System.out.println(exception);
                    if (exception instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                        //System.out.println("interp");
                    }
                    //  LOG.error("Exception message: {}", exception.getMessage(), exception);
                    // MetaStatus.instance().error("Exception ", exception);
                } finally {
                    //metastoreConnector.closeMetastoreConnections();
                }
            }
    }


}
