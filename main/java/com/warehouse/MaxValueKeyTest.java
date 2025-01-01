package com.warehouse;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxValueKeyTest {

    static void  testMaxValueKey() {
        // Test case 1: Standard case with unique maximum value
        Map<String, Integer> sizeMap1 = new HashMap<>();
        sizeMap1.put("a", 10);
        sizeMap1.put("b", 20);
        sizeMap1.put("c", 15);
        assertEquals("b", Collections.max(sizeMap1.entrySet(), Map.Entry.comparingByValue()).getKey());

        // Test case 2: Maximum value occurs multiple times
        Map<String, Integer> sizeMap2 = new HashMap<>();
        sizeMap2.put("a", 10);
        sizeMap2.put("b", 20);
        sizeMap2.put("c", 20);
        assertEquals("b", Collections.max(sizeMap2.entrySet(), Map.Entry.comparingByValue()).getKey());

        // Test case 4: Map with single entry
        Map<String, Integer> sizeMap4 = new HashMap<>();
        sizeMap4.put("a", 10);
        assertEquals("a", Collections.max(sizeMap4.entrySet(), Map.Entry.comparingByValue()).getKey());

        // Test case 5: Map with negative values
        Map<String, Integer> sizeMap5 = new HashMap<>();
        sizeMap5.put("a", -5);
        sizeMap5.put("b", -10);
        sizeMap5.put("c", -3);
        assertEquals("c", Collections.max(sizeMap5.entrySet(), Map.Entry.comparingByValue()).getKey());
    }

    public static void main(String[] args) {
        testMaxValueKey();
    }
}
