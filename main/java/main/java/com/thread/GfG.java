package main.java.com.thread;

import java.util.*;
import java.lang.*;
  
class GfG
{
    public static void main(String[] args)
    {
        try {
            fun();

        }
        catch (RuntimeException exception)
        {
            System.out.println(" exception " + exception);
        }

    }

    private static void fun() {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] >= 5)
            {
                System.out.println("exit...");

                // Terminate JVM
                System.exit(1);
            }
            else
                System.out.println("arr["+i+"] = " +
                        arr[i]);
        }
        System.out.println("End of Program");
    }
}