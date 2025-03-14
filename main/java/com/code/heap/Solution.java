package com.code.heap;

import java.util.*;

public class Solution {


    Comparator<CharFequency> comparator = new Comparator<CharFequency>() {
        @Override
        public int compare(CharFequency o1, CharFequency o2) {
            return o1.getF() - o2.getF();
        }
    };

    public static class CharFequency {
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

    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        PriorityQueue<Solution.CharFequency> maxheapWithFr= new PriorityQueue<>(comparator);
        for(Character c :  map.keySet())
        {
            CharFequency charFequency = new CharFequency();
            charFequency.setC(c);
            charFequency.setF(map.get(c));
            maxheapWithFr.add(charFequency);
        }

        String result = "";
        while (!maxheapWithFr.isEmpty()){

            CharFequency charFequency = maxheapWithFr.remove();
            for (int j = 0; j < charFequency.getF(); j++) {
                result = result + charFequency.getC();
            }
        }
        return result;
    }


    public String frequencySort(String s) {
        int arr[] = new int[256];
        Arrays.fill(arr, 0);
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)]++;
        }

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int indexAsc = findmaxFchar(arr);
            if (indexAsc < 0) {
                continue;
            }

            char ch = (char) indexAsc;

            for (int j = 0; j < arr[indexAsc]; j++) {
                result = result + ch;
            }
            arr[indexAsc] = 0;
        }
        return result;
    }

    public int findmaxFchar(int arr[]) {
        int index = -1;
        int max = 0;
        for (int i = 0; i < 256; i++) {
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
}