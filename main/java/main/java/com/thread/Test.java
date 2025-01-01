package main.java.com.thread;

public class Test extends Thread
{
  public static void main(String[] args) throws InterruptedException
  {
    Test t = new Test();

    try
    {
      t.start();
      t.join();
    }
    catch(RuntimeException e)
    {
      System.out.println("** RuntimeException from main");
    }

    System.out.println("Main stoped");
  }

  @Override
  public void run()
  {
    try
    {
      while(true)
      {
        System.out.println("** Started");

        sleep(2000);

        throw new RuntimeException("exception from thread");
      }
    }
    catch (RuntimeException e)
    {
      System.out.println("** RuntimeException from thread");

      throw e;
    } 
    catch (InterruptedException e)
    {

    }
  }
}