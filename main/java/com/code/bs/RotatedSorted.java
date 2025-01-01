package com.code.bs;

import java.util.HashMap;
import java.util.Map;

public class RotatedSorted {
    //1,2,3,4,5,
    public static void main(String[] args) {
        Map<String , String> map = new HashMap<String,String>();
        System.out.println(map.keySet());
        System.out.println(map.keySet().size());


        int a[] = new int[5];

        for (int i = 0; i < 5; i++) {
            a[i] = i + 1;
        }

        int k = 1;
        for (int i = 0; i < k; i++) {
            int prev = a[i];
            int temp = a[i];
            for (int j = 0; j < 5; j++) {
                prev = a[(j + 1) % 5];
                a[(j + 1) % 5] = temp;
                temp = prev;
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(a[i] + " ");
        }

    }
}
