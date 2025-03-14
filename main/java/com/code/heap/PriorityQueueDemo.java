package com.code.heap;// Java Program Implementing PriorityQueue

import java.util.*;
  
public class PriorityQueueDemo 
{  
    public static void main(String args[])
    {
          // Creating PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
          for(int i=0;i<3;i++){
            pq.add(i);
            pq.add(1);
        }
      
       //System.out.println(pq);
        List<Integer> list = new ArrayList<>(pq);
        System.out.println("list ---> " +list);
        pq.addAll(list);
          while(!pq.isEmpty())
          {
              System.out.println("Before Remove " +pq);
              System.out.println(pq.remove());
              System.out.println("After Remove " +pq);
          }



        PriorityQueue<String> pq1 = new PriorityQueue<>();

        pq1.add("Geeks");
        pq1.add("For");
        pq1.add("Geeks");

       // System.out.println(pq1);
    }
}