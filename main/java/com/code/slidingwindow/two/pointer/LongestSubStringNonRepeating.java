package com.code.slidingwindow.two.pointer;

import java.util.*;


/*
3. Longest Substring Without Repeating Characters
 */
public class LongestSubStringNonRepeating {

    public int lengthOfLongestSubstring(String s) {
        // longest substring

        //BF
        // - find all the subArray without repeating characters
        // maintain the max to track their size;

        // - find all the subArray without repeating characters
         /*   int n = s.length();
            String sub ="";
            Set<Character> charSet= new HashSet();
            int max = 0;
         for(int i=0;i<n;i++)
         {
            charSet = new HashSet();
            for(int j=i; j<n; j++)
            {
                char c =s.charAt(j);
                if( charSet.contains(c))
                {
                    max = Math.max(max, j-i);
                    break;
                }
                charSet.add(c);
            }
         }
        return max;  */


        // 2 pointer and sliding window

        int sum = 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        int leftPointer = 0;
        int rightPointer = 0;
        int len = s.length();

        while (rightPointer < len) {
            char c = s.charAt(rightPointer);

            // key has seen , means repeating character
            if (map.containsKey(c)) {
                if (map.get(c) >= leftPointer) // key is still part of window
                {
                    leftPointer = map.get(c) + 1;
                }

            }
            // key is not seen
            map.put(c, rightPointer); // add or update the key index
            maxLen = Math.max(maxLen, (rightPointer - leftPointer + 1));
            //r-l+1 is window length or substring len.
            rightPointer++;
        }
        return maxLen;
    }

    public int findLongestNonRSubString(String s) {
        int len = s.length();
        // use set to store unique string
        Set<Character> set1 = new HashSet<>();
        int max =0;
        int leftPointer = 0;
        int rightPointer = 0;
        while (rightPointer < len) {
            char c = s.charAt(rightPointer);
            // key already is seen
            if (set1.contains(c)) {
                max = Math.max(max, rightPointer-leftPointer);
                while(s.charAt(leftPointer) != c)
                {
                    set1.remove(s.charAt(leftPointer));
                    leftPointer++;
                }
                set1.remove(s.charAt(leftPointer));
                if(leftPointer<len) leftPointer++;
            }
            set1.add(c);
            rightPointer++;
        }
        max = Math.max(max, rightPointer-leftPointer);

        return max;
    }

    public static void main(String[] args) {
        LongestSubStringNonRepeating lsn = new LongestSubStringNonRepeating();
        System.out.println(lsn.lengthOfLongestSubstring("abcabbbadefrgdap"));
        System.out.println(lsn.findLongestNonRSubString("abcabbbadefrgdap"));

        AbstractMap.SimpleEntry<String, Integer> pair = new AbstractMap.SimpleEntry<>("apple", 10);
        System.out.println(pair.getKey() + " = " + pair.getValue());

       // Map.entry("", Optional.ofNullable(""));
    }

}