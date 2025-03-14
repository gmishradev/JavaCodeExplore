package com.code.slidingwindow.two.pointer;

import java.util.HashMap;

public class SmallestWindowSubstring {
    public static String findSmallestWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        HashMap<Character, Integer> patternMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, minLength = Integer.MAX_VALUE, minStart = 0;
        int requiredChars = patternMap.size();  // Unique characters count in pattern
        int formedChars = 0;
        HashMap<Character, Integer> windowMap = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // Check if this character is in the pattern and is fully matched
            if (patternMap.containsKey(c) && windowMap.get(c).intValue() == patternMap.get(c).intValue()) {
                formedChars++;
            }

            // Try to contract the window
            while (left <= right && formedChars == requiredChars) {
                // Update the minimum window
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);

                if (patternMap.containsKey(leftChar) && windowMap.get(leftChar) < patternMap.get(leftChar)) {
                    formedChars--;
                }

                left++;  // Shrink from the left
            }

            right++;  // Expand to the right
        }

        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minStart, minStart + minLength);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("Smallest Window: " + findSmallestWindow(s, t));
    }
}
