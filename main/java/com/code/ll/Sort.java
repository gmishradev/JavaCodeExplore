package com.code.ll;

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
        System.out.println("Before Sorting: " + list);
        sort(list);
        System.out.println("After Sorting: " + list);
    }

    static void sort(List<Integer> dataList) {
        // Base condition: If the list has only one element, it's already sorted
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
        // Base condition: If the list is empty or num is larger than or equal to the last element, insert num at the end
        if (dataList.isEmpty() || dataList.get(dataList.size() - 1) <= num) {
            dataList.add(num);
            return;
        }

        // If num is smaller than the last element, remove the last element
        int lastElement = dataList.get(dataList.size() - 1);
        dataList.remove(dataList.size() - 1);

        // Recursively insert num into the sorted list
        insert(dataList, num);

        // Once the insertion is done, push the removed element back to maintain the order
        dataList.add(lastElement);
    }
}
