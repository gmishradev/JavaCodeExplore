package com.code.recusion;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(1);
        list.add(10);
        list.add(15);
        list.add(100);
        list.add(17);
        System.out.println(list);
        sort(list);
        System.out.println(list);
    }

    static void sort(List<Integer> dataList) {
        // base condition
        if (dataList.size() == 1) {
            return;
        }
        // Remove the last element from the list
        int num = dataList.get(dataList.size() - 1);
        dataList.remove(dataList.size() - 1);
        // Recursively sort the remaining list
        sort(dataList);

        // Insert the removed element in the correct position
        insert(dataList, num);
    }

    static void insert(List<Integer> dataList, int num) {
        // base always add return
        if (dataList.isEmpty() || dataList.get(dataList.size() - 1) <= num) {
            dataList.add(num);
            return;
        }
        // hypothesic
        int num1 = dataList.get(dataList.size() - 1);
        // make smaller input,  remove last element
        dataList.remove(dataList.size() - 1);
        // insert to correct location
        insert(dataList, num);
        // push back after
        dataList.add(num1);
    }
}
