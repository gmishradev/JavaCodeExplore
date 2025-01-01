package com.bloom.flter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sample data
        List<Integer> table1 = new ArrayList<>();
        table1.add(1);
        table1.add(2);
        table1.add(3);
        table1.add(4);
        table1.add(5);

        List<Integer> table2 = new ArrayList<>();
        table2.add(2);
        table2.add(4);
        table2.add(6);
        table2.add(8);
        table2.add(10);

        // Create Bloom Filter
        BloomFilter bloomFilter = new BloomFilter(100, 3);

        // Add all IDs from table2 to the Bloom Filter
        for (Integer id : table2) {
            bloomFilter.add(id);
        }

        // Remove stale IDs from table1
        List<Integer> staleIds = new ArrayList<>();
        for (Integer id : table1) {
            if (!bloomFilter.contains(id)) {
                staleIds.add(id);
            }
        }
        table1.removeAll(staleIds);

        // Print updated table1
        System.out.println("Updated table1: " + table1);
    }
}
