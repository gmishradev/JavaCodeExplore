package com.code.heap;

import java.util.*;

public class MedianFinder {
    // over all data is asecending order
// 1 ,3,5,6,8


// max heap - 5, 1,3.   // min heap 6,8

    PriorityQueue<Integer> leftMaxHeap; // top/peek will have biggest
    PriorityQueue<Integer> rightminHeap;  // top/peek will have small


    Comparator<CharFequency> comparator =  new Comparator<CharFequency>() {
        @Override
        public int compare(CharFequency o1, CharFequency o2) {
            return o1.getF() - o2.getF();
        }
    };
    public  static class   CharFequency {
        int f;
        char c;

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }

        public char getC() {
            return c;
        }

        public void setC(char c) {
            this.c = c;
        }
    }
    public MedianFinder() {
        leftMaxHeap = new PriorityQueue(Collections.reverseOrder());// max heap
        rightminHeap = new PriorityQueue(); // min heap
    }

    public void addNum(int num) {

        if (leftMaxHeap.isEmpty() || num < leftMaxHeap.peek()) {
            leftMaxHeap.add(num);
        } else {
            rightminHeap.add(num);
        }
        // always maintain leftMaxheap size and rightMinheap size diffrernce should 1;
        // left max heap size greater

        if (leftMaxHeap.size() - rightminHeap.size() > 1) {
            rightminHeap.add(leftMaxHeap.remove());
        } else if (leftMaxHeap.size() < rightminHeap.size()) {
            leftMaxHeap.add(rightminHeap.remove());
        }


    }

    public double findMedian() {
        int size1 = leftMaxHeap.size();
        int size2 = rightminHeap.size();
        if (size1 == size2) {
            return (leftMaxHeap.peek() * 1.00d + rightminHeap.peek()) / 2;
        }
        return leftMaxHeap.peek();
    }

    public static void main(String[] args) {
        //["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
        //[[], [1], [2], [], [3], []]
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);

    }
}